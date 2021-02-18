#include <SoftwareSerial.h>

#define SOFT_RX 11
#define SOFT_TX 10
#define KEY 4
#define EN KEY
#define BUTTON1 8
#define BUTTON2 2
#define BLUETOOTH_POWER 5
#define STATE 6

//SoftwareSerial BluetoothSerial(SOFT_RX, SOFT_TX);

//void InitializeBluetoothModule();
//void InitializeBluetoothModuleFLAG();

bool BT_FLAG = false;

void setup() {

  // put your setup code here, to run once:
  pinMode(KEY, OUTPUT);
  pinMode(BUTTON1, INPUT_PULLUP);
  pinMode(BUTTON2, INPUT_PULLUP);
  pinMode(BLUETOOTH_POWER, OUTPUT);
  pinMode(STATE, INPUT_PULLUP);
  pinMode(A2, OUTPUT);

  digitalWrite(A2, false);
  digitalWrite(KEY, false);
  delay(1000);
  digitalWrite(BLUETOOTH_POWER, true);

  attachInterrupt(digitalPinToInterrupt(BUTTON2), InitializeBluetoothModuleFLAG, FALLING);

  //BluetoothSerial.begin(38400);
  Serial.begin(9600);

  
  

}

void loop() {
  // put your main code here, to run repeatedly:

  /*if (BT_FLAG) InitializeBluetoothModule();

  if(BluetoothSerial.available()) {
    Serial.write(BluetoothSerial.read());
  }

  if (Serial.available())
  {
    BluetoothSerial.write(Serial.read());
  }
  */
}

void InitializeBluetoothModuleFLAG()
{
  BT_FLAG = true;
}

void InitializeBluetoothModule() {
  
  digitalWrite(A2, true);
  delay(10);
  Serial.println("Entering AT Command Mode");
  digitalWrite(BLUETOOTH_POWER, false);
  digitalWrite(KEY, true);
  delay(1000);
  digitalWrite(BLUETOOTH_POWER, true);
  delay(1000);
  //BluetoothSerial.write("AT+ORGL\r\n");
  delay(1000);
  //BluetoothSerial.write("AT+ROLE=0\r\n");
  delay(1000);
  //BluetoothSerial.write("AT+NAME=Parcheezi\r\n");
  delay(1000);
  //BluetoothSerial.write("AT+UART=38400,1,0\r\n");
  delay(1000);
  //BluetoothSerial.write("AT+CLASS=1\r\n");
  //delay(1000);
  digitalWrite(BLUETOOTH_POWER, false);
  delay(1000);
  digitalWrite(KEY, false);
  delay(1000);
  digitalWrite(BLUETOOTH_POWER, true);
  BT_FLAG = false;
  digitalWrite(A2, false);
}
