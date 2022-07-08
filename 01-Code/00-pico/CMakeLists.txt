cmake_minimum_required(VERSION 3.12)

include($ENV{PICO_SDK_PATH}/external/pico_sdk_import.cmake)

project(Position_Accuracy C CXX ASM)
set(CMAKE_C_STANDARD 11)

pico_sdk_init()
set(PICO_SDK_INITIALIZED ON)
set(PICO_SDK_INITIALIZED "${PICO_SDK_INITIALIZED}" CACHE INTERNAL "Setting cache variable to let other imports know I've setup the pico SDK")

add_subdirectory(/home/max/Libs/00-MicroTools ${CMAKE_BINARY_DIR}/00-MicroTools)

add_executable(Wagon main.c)

if(NOT TARGET desousa_micro_tools_pico_i2c)
  message("! ! ! !   problem. Library target not found")
else()
  message("-- Don't worry max, your library was found")
endif()

target_link_libraries(Wagon desousa_micro_tools_pico_i2c)

pico_enable_stdio_usb(Wagon 1)

pico_add_extra_outputs(Wagon)
