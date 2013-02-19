/**
 * AUVUA Arduino Breakout
 ***
 * Runs on a standard Arduino (Duemilanove, Uno, etc).
 * Digital pins 0-9 are outputs set by host computer,
 * digital pins 10-13 and analog pins 0-5 are telemetry
 * inputs sent to host. Messages are sent at 20Hz.
 **/
 
//analog input references and associated values
int analogs[2][6] = {{A0, A1, A2, A3, A4, A5},
                     {0, 0, 0, 0, 0, 0}};
//outgoing message buffer
uint8_t msg[18];

/*
 * setup, called once at power-on
 */
void setup() {
  for(int i = 0; i < 14; i++)
    if(i < 10) pinMode(i, OUTPUT);
    else pinMode(i, INPUT);
    
  Serial.begin(57600);
}

/*
 * loop, called continuously after setup returns
 */
void loop() {
  long start = micros();
  // read and set data
  if(Serial.available() >= 14) {
    if(Serial.read() == '*' && Serial.read() == '*') {
      for(int i = 0; i < 10; i++)
        digitalWrite(i, (Serial.read() == 'h' ? HIGH : LOW));
      Serial.read(); Serial.read();
    }
    // bad data, flush input buffer
    while(Serial.available())
      Serial.read();
  }
  
  // read analog inputs
  for(int i = 0; i < 6; i++)
    analogs[1][i] = analogRead(analogs[0][i]);
    
  // build message
  msg[0] = '*';
  msg[17] = '-';
  for(int i = 0; i < 4; i++)
    msg[i+1] = digitalRead(i+10);
  for(int i = 0; i < 6; i++) {
    msg[2*i+5] = (unsigned char)(analogs[1][i] >> 8);
    msg[2*i+6] = (unsigned char)(analogs[1][i] & 0x00FF);
  }
  
  // send message
  for(int i = 0; i < 18; i++)
    Serial.write(msg[i]);  
  
  while(micros() < start + 50000);
}
