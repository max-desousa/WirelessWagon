#include <stdio.h>
#include "pico/stdlib.h"
#include "hardware/i2c.h"
//#include "pico/time.h"

bool reserved_addr(uint8_t addr)
  {
  return (addr & 0x78) == 0 || (addr & 0x78) == 0x78;
  }

int main(void) {
  
  while(true)
{
  sleep_ms(5000);
  printf("Initializing stdio...\n");
  stdio_init_all();

  printf("Initializing I2C pins...\n");
  gpio_set_function(0, GPIO_FUNC_I2C);
  gpio_set_function(1, GPIO_FUNC_I2C);
  gpio_pull_up(0);
  gpio_pull_up(1);
  printf("Initializing I2C device (device 0)...\n");
  i2c_init(i2c0, 115200);

  printf("\nI2C Pico Scan\n");
  printf("   0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F\n");

  for (int addr = 0; addr < (1 << 7); ++addr) 
    {
    if (addr % 16 == 0) 
      {
      printf("%02x ", addr);
      }
    int ret;
    uint8_t rxdata;
    if (reserved_addr(addr))
      {
      ret = PICO_ERROR_GENERIC;
      }
    else
      {
      ret = i2c_read_blocking(i2c_default, addr, &rxdata, 1, false);
      }
 
    printf(ret < 0 ? "." : "@");
    printf(addr % 16 == 15 ? "\n" : "  ");
    }
  printf("Done.\n");
  
  }
  return 0;
}
