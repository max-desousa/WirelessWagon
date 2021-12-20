#include <SoftwareSerial.h>

//#define SOFT_RX 11
//#define SOFT_TX 10
#define KEY 12
#define EN KEY
#define BUTTON1 3
#define BUTTON2 2
#define BLUETOOTH_POWER 11
//#define STATE 6
#define LED 13

//SoftwareSerial BluetoothSerial(SOFT_RX, SOFT_TX);

//void InitializeBluetoothModule();
//void InitializeBluetoothModuleFLAG();

bool InitializeBluetooth = false;

void setup() {

  // put your setup code here, to run once:
  pinMode(KEY, OUTPUT);
  pinMode(LED, OUTPUT);
  pinMode(BUTTON1, INPUT_PULLUP);
  pinMode(BUTTON2, INPUT_PULLUP);
  pinMode(BLUETOOTH_POWER, OUTPUT);
  //pinMode(STATE, INPUT_PULLUP);

  digitalWrite(LED, false);

  digitalWrite(KEY, false);
  digitalWrite(BLUETOOTH_POWER, true);

  attachInterrupt(digitalPinToInterrupt(BUTTON2), InitializeBluetoothModuleFlag, FALLING);

  Serial.begin(38400);

  
  

}

void loop() {
  // put your main code here, to run repeatedly:

  if (InitializeBluetooth) InitializeBluetoothModule();

  if (Serial.available())
  {
    Serial.print(Serial.read());
  }
  
}

void InitializeBluetoothModuleFlag() {
  InitializeBluetooth = true;
}

void InitializeBluetoothModule() {
  InitializeBluetooth = false;
  digitalWrite(LED, true);
  Serial.flush();
  Serial.begin(38400);
  delay(10);
  Serial.println("Entering AT Command Mode");
  digitalWrite(BLUETOOTH_POWER, false);
  digitalWrite(KEY, true);
  delay(1000);
  digitalWrite(BLUETOOTH_POWER, true);
  delay(1000);
  Serial.write("AT+ORGL\r\n");
  delay(1000);
  Serial.write("AT+ROLE=0\r\n");
  delay(1000);
  String commandPrefix = "AT+NAME=";
  //String deviceName = "InspectorGadget";
  String deviceName = "GodComplex";
  String randomNumber = String(random(9999));
  String newLine = "\r\n";
  String finalDeviceName = commandPrefix + deviceName + randomNumber + newLine;
  char* finalDeviceNameCharArray = (char*) malloc(sizeof(char)*(finalDeviceName.length() + 1));
  finalDeviceName.toCharArray(finalDeviceNameCharArray, finalDeviceName.length() + 1);
  Serial.write(finalDeviceNameCharArray);
  delay(1000);
  Serial.write("AT+UART=38400,1,0\r\n");
  delay(1000);
  Serial.write("AT+CLASS=1\r\n");
  //delay(1000);
  //digitalWrite(BLUETOOTH_POWER, false);
  //delay(1000);
  //digitalWrite(KEY, false);
  //delay(1000);
  //digitalWrite(BLUETOOTH_POWER, true);
  digitalWrite(LED, false);
  Serial.flush();
  Serial.begin(38400);
}
