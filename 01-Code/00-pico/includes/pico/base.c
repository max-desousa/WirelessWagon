#include "base.h"
#include <stdlib.h>
#include <stdint.h>
#include "hardware/i2c.h"

static uint8_t I2CBussesInUse = 0x00;

uint8_t InitializeI2C(uint _baudRate)
  {
  uint8_t result = 0;
  uint actualBaudRate = 0;
  if (I2CBussesInUse >= 0x03)
    {
    result = 0x00;
    }
  if ((I2CBussesInUse & 0x01) > 0)
    {
    actualBaudRate = i2c_init(i2c0, _baudRate);
    if (actualBaudRate > 0)
      {
      if (actualBaudRate == _baudRate)
        {
        result = 0x01;
        }
      else
        {
        result = 0x81;
        }
      }
    else
      {
      result = 0x00;
      }
    }
  else
    {
    actualBaudRate = i2c_init(i2c1, _baudRate);
    if (actualBaudRate > 0)
      {
      if (actualBaudRate == _baudRate)
        {
        result = 0x02;
        }
      else
        {
        result = 0x82;
        }
      }
    else
      {
      result = 0x00;
      }
    }
  return result;
  }
