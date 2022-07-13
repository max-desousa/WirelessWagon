cmake_minimum_required(VERSION 3.12)

message("\n\nRunning pico_sdk_import.cmake:\n")
include($ENV{PICO_SDK_PATH}/external/pico_sdk_import.cmake)

project(WirelessWagon C CXX ASM)
set(CMAKE_C_STANDARD 11)

message("\n\nRunning pico_sdk_init():\n")
pico_sdk_init()

set(MICRO_TOOLS_INCLUDE_00-Pico ON CACHE BOOL "Configuring MicroTools to add Raspberry Pi Pico libraries")

message("\n\nRunning fetch_micro_tools.cmake:\n")
include(fetch_micro_tools.cmake)

add_executable(OnWagonEmbedded main.c)

target_link_libraries(OnWagonEmbedded 
  MICRO_TOOLS_PICO_I2C 
  MICRO_TOOLS_PRECOMPILER
  MICRO_TOOLS_GPS_ZEDF9P)

pico_enable_stdio_usb(OnWagonEmbedded 1)

pico_add_extra_outputs(OnWagonEmbedded)
