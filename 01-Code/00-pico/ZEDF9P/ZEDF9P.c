#include "ZEDF9P.h"

bool DeviceConnected()
  {
  int numBytesRead;
  uint8_t data;
  numBytesRead = i2c_read_blocking(Zedf9pI2cBus, 0x42, &data, 1, false);
  return (numBytesRead >= 0);
  }
