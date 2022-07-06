#ifndef CUSTOM_PICO_FUNCTIONS
#define CUSTOM_PICO_FUNCTIONS

uint8_t pico_InitI2cBus(uint8_t _sclPin, uint8_t _sdaPin, uint8_t _baud);
uint8_t pico_InitSpiBus();
uint8_t pico_InitUardBus();

#endif
