add_library(board_pico INTERFACE)
target_sources(board_pico INTERFACE ${CMAKE_CURRENT_SOURCE_DIR}/custom_pico.h ${CMAKE_CURRENT_SOURCE_DIR}/custom_pico.c)
target_link_libraries(board_pico INTERFACE 
hardware_i2c
hardware_gpio)
target_include_directories(board_pico INTERFACE ${CMAKE_CURRENT_SOURCE_DIR})
