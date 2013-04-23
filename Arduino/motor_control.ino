/*
 * motor_control - Bridge between motor control and
 * desired motor signals.
 *
 * Author(s): Evan Briones
 *
 * Copyright (C) 2013 AUVUA Club
 *
 */

#include <SoftwareSerial.h>

// Serial
#define TXPIN 2
#define RXPIN 3

#define BAUD_RATE 19200
#define PACKET_LENGTH 20
#define TIMEOUT_THRESHOLD 1000

// Motor control
#define STOP_SPEED 0
#define CONTROL_FLAG 0xAA
#define MOTOR_FWD 0x05
#define MOTOR_REV 0x06

long current_time;
long previous_time;
SoftwareSerial serial_controller = SoftwareSerial(RXPIN, TXPIN);

/*
 * Set the speed between 0 and 200 which is
 * percentage reverse or forward (-100 and 100) 
 * centered around 100 (0 percent). 
 */
void setMotorSpeed(byte dir, byte speed, byte device_id) {
    byte packet[5] = {0};
    
    speed = speed < 0 ? 0 : (speed > 100 ? 100 : speed);
    speed = speed / 2;
    
    packet[0] = CONTROL_FLAG;
    packet[1] = device_id;
    packet[2] = (dir == 1) ? MOTOR_REV : MOTOR_FWD;
    packet[3] = 0x00;
    packet[4] = speed;
    
    serial_controller.write(packet, 5);
}

void setup() {
    Serial.begin(BAUD_RATE);
    serial_controller.begin(BAUD_RATE);

    // Setup pins
    pinMode(TXPIN, OUTPUT);
    pinMode(RXPIN, INPUT);

    // Setup timer
    current_time = previous_time = millis();
    
    // Start motor controllers
    serial_controller.write(0x83);
    delay(1);
}

void loop() {
    
    // Controller doesn't send back info yet.
    if (serial_controller.available()) {
    }
    
    if(Serial.peek() != 127) {
      Serial.read();
    } else if (Serial.available() > PACKET_LENGTH) {
        Serial.read();
        
        byte packet[PACKET_LENGTH] = {0};
        for (int i = 0; i < PACKET_LENGTH; i++) {
          packet[i] = Serial.read();
        }
        
        //while(Serial.available())
        //  Serial.read();
        
        //if (get_checksum(packet) > 0)
        //    return;
        for (int i = 0; i < PACKET_LENGTH - 2; i += 3) {
            setMotorSpeed(packet[i+1], packet[i+2], packet[i]);
        }

        serial_controller.write(0x83);

        previous_time = millis();
    }

    if ((current_time - previous_time) > TIMEOUT_THRESHOLD) {
        for (int i = 0; i < 6; i++)
           setMotorSpeed(0, STOP_SPEED, byte(i));
    }

    current_time = millis();
    
    //serial_controller.write(0x83);
}

int get_checksum(byte packet[]) {
    word checksum = 0;

    for (int i = 0; i < PACKET_LENGTH - 2; i += 2) {
        checksum += word(packet[i], packet[i+1]);
    }

    checksum -= word(packet[PACKET_LENGTH-2], packet[PACKET_LENGTH-1]);
    return int(checksum);
}
