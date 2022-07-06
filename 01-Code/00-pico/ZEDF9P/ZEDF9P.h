#ifndef ZEDF9P_H
#define ZEDF9P_H

#include "hardware/i2c.h"

bool DeviceConnected();

typedef enum busses {
  I2C;
  SPI;
  UART;
  ONEWIRE;
} busses;

typedef enum UBX_TYPES {
  NMEA,
  UBX,
  RTCM,
  SPARTN
} UBX_TYPES;

#endif
