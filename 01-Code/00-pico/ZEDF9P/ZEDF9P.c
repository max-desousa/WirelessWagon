#include "ZEDF9P.h"

uint8_t Zedf9pDeviceAddress = 0x42;

i2c_inst_t* Zedf9pI2cBus = i2c0;

bool DeviceConnected()
  {
  int numBytesRead;
  uint8_t data;
  numBytesRead = i2c_read_blocking(Zedf9pI2cBus, Zedf9pDeviceAddress, &data, 1, false);
  return (numBytesRead >= 0);
  }

uint8_t initializeModule(busses _busProtocol, uint32_t _baudRate, UBX_TYPES _ubxMethod);
  {
  switch(_busProtocol)
    {
    case I2C:
      pico_InitI2cBus();
      break;
    }
  pico_initI2CBus(
  #ifdef USB_DEBUG_PRINT_CONSOLE
  #endif
  }
