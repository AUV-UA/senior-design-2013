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
#define TXPIN 1
#define RXPIN 0
#define CNTRL 3

#define BAUD_RATE 19200
#define PACKET_LENGTH 14
#define TIMEOUT_THRESHOLD 1000

// Motor control
#define MAX_VALUE 254
#define STOP_SPEED 0
#define CONTROL_FLAG 0xFF

unsigned long current_time;
unsigned long previous_time;
SoftwareSerial serial_controller = SoftwareSerial(RXPIN, TXPIN);

/*
 * Set the speed between -127 to 127 which is
 * is shifted to 0 to 254.
 */
void setMotorSpeed(int speed, byte device_id) {
    uint8_t output;

    // Set the value between 0 to 254
    output = (speed > 0) ? max(speed + 127, 0) : min(speed + 127, MAX_VALUE);

    // Send Mini SSC packet.
    serial_controller.write(CONTROL_FLAG);
    serial_controller.write(device_id);
    serial_controller.write(output);
}

void setup() {
    Serial.begin(BAUD_RATE);
    serial_controller.begin(BAUD_RATE);

    // Setup pins
    pinMode(TXPIN, OUTPUT);
    pinMode(RXPIN, INPUT);
    pinMode(CNTRL, INPUT);

    // Setup timer
    current_time = previous_time = millis();
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
        
        if (get_checksum(packet) > 0)
            return;

        for (int i = 0; i < PACKET_LENGTH - 2; i += 2) {
            setMotorSpeed((int) packet[i+1], packet[i]);
        }

        previous_time = millis();
    }

    if (abs(current_time - previous_time) > TIMEOUT_THRESHOLD) {
        for (int i = 0; i < PACKET_LENGTH -2; i +=2)
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
