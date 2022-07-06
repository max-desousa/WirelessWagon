#include <stdio.h>
//#include "ZEDF9P.h"
#include "hardware/i2c.h"
#include "hardware/gpio.h"
#include "pico/stdlib.h"

int main(void) 
  {
  stdio_init_all();
  sleep_ms(5000);
  printf("Initializing I2C pins...\n");
  gpio_set_function(0, GPIO_FUNC_I2C);
  gpio_set_function(1, GPIO_FUNC_I2C);
  gpio_pull_up(0);
  gpio_pull_up(1);
  printf("Initializing I2C device (device 0)...\n");
  i2c_init(i2c0, 115200);

  bool zedf9pWiredCorrectly;
  zedf9pWiredCorrectly = DeviceConnected();

  ///* Begin copying src... */
  //myGNSS.setI2COutput(COM_TYPE_UBX);
  //  {
  //  return (setPortOutput(COM_PORT_I2C, comSettings, maxWait
  //  //class 0x06 for GetPortSettingsInternal
  //  //CFG == 0x00 for GetPortSettingsInternal
  //  }
  //myGNSS.getLatitude();
  //myGNSS.getLongitude();
  //myGNSS.getAltitude();
  //myGNSS.getPositionAccuracy();

  if (zedf9pWiredCorrectly)
    {
    printf("Device wired correctly.\n");
    }
  else
    {
    printf("Device not responding\n");
    }
  return zedf9pWiredCorrectly;
  }
