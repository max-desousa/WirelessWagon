add_library(zedf9p_lib INTERFACE)
#target_sources(zedf9p_lib INTERFACE ${CMAKE_CURRENT_SOURCE_DIR}/ZEDF9P.h ${CMAKE_CURRENT_SOURCE_DIR}/ZEDF9P.c)
target_sources(zedf9p_lib INTERFACE ${CMAKE_CURRENT_SOURCE_DIR}/ZEDF9P.c ${CMAKE_CURRENT_SOURCE_DIR}/ZEDF9P.h)
target_link_libraries(zedf9p_lib INTERFACE hardware_i2c)
target_include_directories(zedf9p_lib INTERFACE ${CMAKE_CURRENT_SOURCE_DIR})

#target_include_directories(zedf9p_lib PRIVATE ${CMAKE_CURRENT_SOURCE_DIR})
