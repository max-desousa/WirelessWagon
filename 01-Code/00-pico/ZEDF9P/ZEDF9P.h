#ifndef ZEDF9P_H
#define ZEDF9P_h

#include <stdbool.h>
#include <stdint.h>
#include "hardware/i2c.h"

uint8_t Zedf9pDeviceAddress = 0x42;

i2c_inst_t* Zedf9pI2cBus = i2c0;

bool DeviceConnected();

#endif
