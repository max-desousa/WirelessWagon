#include "custom_pico.h"
#include "hardware/gpio.h"
#include "hardware/i2c.h"
#include "pico/stdlib.h"

bool picoStdioHasBeenSetup = false;

uint8_t pico_InitI2cBus(uint8_t _sclPin, uint8_t _sdaPin, uint8_t _baud)
  {
  uint8_t errorCode = 0;
  /* First check that the SCL and SDA pin are in a compatible
   * I2C HW interface. If they conflict return error code */
  errorCode |= compatibleI2cPins(_sclPin, _sdaPin);
  if (errorCode > 0)
    {
    return errorCode;
    }

  /* Next, make sure that our stdio is all set up in the module */
  if (!picoStdioHasBeenSetup) pico_InitStdio();

  /* Because pins are compatible, set them up for I2C */
  gpio_set_function(_sclPin, GPIO_FUNC_I2C);
  gpio_set_function(_sdaPin, GPIO_FUNC_I2C);
  gpio_pull_up(_sclPin);
  gpio_pull_up(_sdaPin);

  return errorCode;
  }
uint8_t pico_InitSpiBus()
  {
  return 0;
  }
uint8_t pico_InitUardBus()
  {
  return 0;
  }

void pico_InitStdio(void)
  {
  stdio_init_all();
  picoStdioHasBeenSetup = true;
  }
