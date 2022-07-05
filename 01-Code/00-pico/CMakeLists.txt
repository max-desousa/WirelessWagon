cmake_minimum_required(VERSION 3.12)

include($ENV{PICO_SDK_PATH}/external/pico_sdk_import.cmake)

project(Position_Accuracy C CXX ASM)
set(CMAKE_C_STANDARD 11)

pico_sdk_init()

add_subdirectory(ZEDF9P)
add_subdirectory(LatLongAccur)

add_executable(Wagon main.c)

target_link_libraries(Wagon zedf9p_lib hardware_i2c hardware_gpio pico_stdlib)

pico_enable_stdio_usb(Wagon 1)

pico_add_extra_outputs(Wagon)
