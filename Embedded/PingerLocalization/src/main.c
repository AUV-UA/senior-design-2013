/*
 * main.c - Signal processing of the pinger localization board.
 * 
 * Author(s): Evan Briones
 *
 * Copyright (c) 2013 AUVUA Club
 *
 */

#include <adc.h>
#include <dsp.h>
#include <limits.h>
#include <stdio.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdlib.h>
#include <p33FJ64GS608.h>

#define FCY 1
#define SAMPLE_RATE (FCY/256)
#define MAXIMUM_DATAPOINTS 1000

_FBS(BWRP_WRPROTECT_ON)
_FOSCSEL(FNOSC_PRIPLL)
_FOSC(FCKSM_CSECMD & POSCMD_XT & OSCIOFNC_OFF)
_FGS(GWRP_OFF & GCP_OFF)
_FICD(JTAGEN_OFF)
_FWDT(FWDTEN_OFF)

#define wait(condition) do {} while(condition)
#define _ATTR __attribute__((__interrupt__, __auto_psv__))

typedef enum State {
    Initializing,
    Searching,
    Recording,
    Processing,
    Sending,
    Error,
    Reset
} State_t;

typedef enum ErrorFlag {
    SIGNAL_NOT_FOUND = 0x0001,
    OUTPUT_FAILED = 0x0010,
    MATH_ERROR = 0x0100,
} ErrorFlag_t;

typedef struct Telemetry {
    float heading;
    float angle;
} Telemetry_t;

// PIC specific
void initalize();
void reset();
void handle_errors(ErrorFlag_t error);
bool find_signal();
void get_input_signal(float datapoints []);
Telemetry_t calculate_telemetry_info(float datapoints []);
bool send_telemetry_info(Telemetry_t info);
uint8_t* get_bytes(float value);

////////////////////////////////////////////////////////////////////////////////
// Global data
////////////////////////////////////////////////////////////////////////////////
State_t current_state;
ErrorFlag_t error_flag;

int main(int argc, char** argv) {
    current_state = Initializing;
    static bool signal_found = false;
    static bool success = true;
    static float *data;

    Telemetry_t output;

    while(1) {
     

        switch(current_state) {
            case Initializing:
                initalize();
                current_state = Searching;
                break;
            case Searching:
                signal_found = find_signal();
                
                if (signal_found)
                    current_state = Recording;
                else
                    handle_errors(SIGNAL_NOT_FOUND);
                break;
            case Recording:
                
                
                get_input_signal(data);
                current_state = Recording;
                break;
            case Processing:
                output = calculate_telemetry_info(data);
                current_state = Sending;
                break;
            case Sending:
                success = send_telemetry_info(output);
                
                if (success)
                    current_state = Searching;
                else
                    error_flag = (error_flag | OUTPUT_FAILED);
                    current_state = Error;
                break;
            case Error:
                handle_errors(error_flag);
                break;
            case Reset:
                reset();
                data = NULL;
                break;
            default:
                break;
        }
    }

    return (EXIT_SUCCESS);
}

//
// Initializes the p33FJ64GS608 configurations
//
void initalize()
{
    PR1 = SAMPLE_RATE;
    TMR1 = 0;

    T1CONbits.TCKPS0 = 1;
    T1CONbits.TCKPS1 = 1;
    T1CONbits.TCS = 1;
    IEC0bits.T1IE = 1;
    T1CONbits.TON = 1;
}

//
// Resets the p33FJ64GS608 configuration
//
void reset()
{
    error_flag = 0;
    current_state = Initializing;
    
    T1CON = 0;
    TMR1 = 0;
    IEC0bits.T1IE = 0;
}

//
// Handles unexpected errors the localization board encounters.
//
void handle_errors(ErrorFlag_t error)
{
    switch(error) {
        case SIGNAL_NOT_FOUND:
            current_state = Searching;
            break;
        case OUTPUT_FAILED:
            current_state = Reset;
            break;
        case MATH_ERROR:
            current_state = Reset;
            break;
        default:
            break;
    }
}

//
// Searches for the desired output signal
//
bool find_signal()
{
    return false;
}

//
// Fetches the desired signal from the board
//
void get_input_signal(float datapoints [])
{
    wait(!IFS0bits.T1IF);
}

//
// Calculates the desired heading and angle
//
Telemetry_t calculate_telemetry_info(float datapoints [])
{
    Telemetry_t info;

    return info;
}

//
// Sends the telemetry information to the output port
//
bool send_telemetry_info(Telemetry_t info)
{
    return true;
}

//
// Converts the floating point value to a byte array.
//
uint8_t* get_bytes(float value)
{
    uint8_t bytes[MAXIMUM_DATAPOINTS];
    return bytes;
}

////////////////////////////////////////////////////////////////////////////////
// Interrupts and Traps
////////////////////////////////////////////////////////////////////////////////
void _ATTR  _MathError(void)
{
    error_flag = MATH_ERROR;
}

void _ATTR _StackError(void)
{
}

void _ATTR _AddressError(void)
{
}
