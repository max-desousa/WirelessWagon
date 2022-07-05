add_executable(Example1 main.c)

pico_enable_stdio_usb(Example1 1)
pico_add_extra_outputs(Example1)

target_link_libraries(Example1
hardware_i2c
hardware_gpio
pico_stdlib)
