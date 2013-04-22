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
#define PACKET_LENGTH 14
#define TIMEOUT_THRESHOLD 1000

// Motor control
#define MAX_VALUE 254
#define STOP_SPEED 100
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
void setMotorSpeed(int speed, byte device_id) {
    byte packet[5] = {0};
    
    speed = speed < 0 ? 0 : (speed > 200 ? 200 : speed);
    speed = (speed - 100) / 2;
    
    packet[0] = CONTROL_FLAG;
    packet[1] = device_id;
    packet[2] = (speed < 0) ? 0x06 : 0x05;
    packet[3] = 0x00;
    packet[4] = abs(speed);
    
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
    delay(10);
}

void loop() {
    // Controller doesn't send back info yet.
    if (serial_controller.available()) {
    }
    
    if (Serial.available() >= 14) {
        byte packet[14] = {0};
        for (int i = 0; i < 14; i++) {
          packet[i] = Serial.read(); 
        }
        
        while(Serial.available())
          Serial.read();
        
        //if (get_checksum(packet) > 0)
        //    return;

        for (int i = 0; i < PACKET_LENGTH - 2; i += 2) {
            setMotorSpeed((int) packet[i+1], packet[i]);
        }

        previous_time = millis();
    }

    if ((current_time - previous_time) > TIMEOUT_THRESHOLD) {
        for (int i = 0; i < 6; i++)
           setMotorSpeed(STOP_SPEED, byte(i));
    }

    current_time = millis();
}

int get_checksum(byte packet[]) {
    word checksum = 0;

    for (int i = 0; i < PACKET_LENGTH - 2; i += 2) {
        checksum += word(packet[i], packet[i+1]);
    }

    checksum -= word(packet[PACKET_LENGTH-2], packet[PACKET_LENGTH-1]);
    return int(checksum);
}
