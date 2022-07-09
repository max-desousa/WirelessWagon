#include "simplePico/i2c.h"

int main(void) 
  {
  uint8_t error = 0;

  SetupI2cBus(i2c0, 1, 2, 115200); 
  return error;
  }
