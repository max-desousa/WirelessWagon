cmake_minimum_required(VERSION 3.12)

include($ENV{PICO_SDK_PATH}/external/pico_sdk_import.cmake)

project(WirelessWagon C CXX ASM)
set(CMAKE_C_STANDARD 11)

pico_sdk_init()
set(MICRO_TOOLS_INCLUDE_00-Pico ON CACHE BOOL "Configuring MicroTools to add Raspberry Pi Pico libraries")

include(fetch_micro_tools.cmake)

message("I'm expecting text here:")
if(NOT TARGET tst_desousa_pico)
    message("!!! Your custom library was not found !!!")
endif()

add_executable(OnWagonEmbedded main.c)

target_link_libraries(OnWagonEmbedded MICRO_TOOLS_PICO_I2C)

pico_enable_stdio_usb(OnWagonEmbedded 1)

pico_add_extra_outputs(OnWagonEmbedded)
