#include "microcontroller/pico.h"
#include "simplePico/i2c.h"
#include "gps/zedf9p.h"

int main(void) 
  {
  uint8_t error = 0;

  //SetupI2cBus(1, 2, 115200); 
  i2c_struct i2c_setup;
  i2c_setup.dataPin = 1;
  i2c_setup.clockPin = 2;
  i2c_setup.baudRate = 115200;
  //SimpleSetupZedf9p(i2c, &i2c_setup);
  return error;
  }
