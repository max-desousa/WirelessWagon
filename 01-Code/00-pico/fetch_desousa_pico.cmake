if (DEFINED ENV{DESOUSA_PICO_PATH} AND (NOT DESOUSA_PICO_PATH))
    set(DESOUSA_PICO_PATH $ENV{DESOUSA_PICO_PATH})
    message("Using DESOUSA_PICO_PATH from environment ('${DESOUSA_PICO_PATH}')")
endif()

if (DEFINED ENV{DESOUSA_PICO_FETCH_FROM_GIT} AND (NOT DESOUSA_PICO_FETCH_FROM_GIT))
    set(DESOUSA_PICO_FETCH_FROM_GIT $ENV{DESOUSA_PICO_FETCH_FROM_GIT})
    message("Using DESOUSA_PICO_FETCH_FROM_GIT from environment ('${DESOUSA_PICO_FETCH_FROM_GIT}')")
endif()

if (DEFINED ENV{DESOUSA_PICO_FETCH_FROM_GIT_PATH} AND (NOT DESOUSA_PICO_FETCH_FROM_GIT_PATH))
    set(DESOUSA_PICO_FETCH_FROM_GIT_PATH $ENV{DESOUSA_PICO_FETCH_FROM_GIT_PATH})
    message("Using DESOUSA_PICO_FETCH_FROM_GIT_PATH from environment ('${DESOUSA_PICO_FETCH_FROM_GIT_PATH}')")
endif()

set(DESOUSA_PICO_PATH "${DESOUSA_PICO_PATH}" CACHE PATH "Path to Max DeSousa's custom raspberry pi pico tools")
set(DESOUSA_PICO_FETCH_FROM_GIT "${DESOUSA_PICO_FETCH_FROM_GIT}" CACHE BOOL "Set to ON to fetch Max DeSousa's raspberry pi pico tools if not locally locatable")
set(DESOUSA_PATH_FETCH_FROM_GIT_PATH "${DESOUSA_PATH_FETCH_FROM_GIT_PATH}" CACHE FILEPATH "Location to download Max DeSousa'a raspberry pi pico tools")

if (NOT DESOUSA_PICO_PATH)
    if (DESOUSA_PICO_FETCH_FROM_GIT)
        include(FetchContent)
        set(FETCHCONTENT_BASE_DIR_OLD ${FETCHCONTNENT_BASE_DIR})
        if(DESOUSA_PICO_FETCH_FROM_GIT_PATH) 
            get_filename_component(FETCHCONTENT_BASE_DIR "${DESOUSA_PATH_FETCH_FROM_GIT_PATH}" REALPATH BASE_DIR "${CMAKE_SOURCE_DIR}")
        endif()
        FetchContent_Declare(
                desousa_pico
                GIT_REPOSITORY https://github.com/max-desousa/rPiPicoLibs
                GIT_TAG origin/main
        )
        if (NOT desousa_pico)
            message("Downloading Max DeSousa's Raspberry Pi Pico tools")
            FetchContent_Populate(desousa_pico)
            add_subdirectory(${desousa_pico_SOURCE_DIR} ${desousa_pico_BINARY_DIR})
            set(DESOUSA_PICO_PATH ${desousa_pico_SOURCE_DIR})
        endif ()
        set(FETCHCONTNENT_BASE_DIR ${FETCHCONTENT_BASE_DIR_OLD})
    else()
        message(FATAL_ERROR
                "Tools location was not specified. Please set DESOUSA_PICO_PATH or set DESOUSA_PICO_FETCH_FROM_GIT to on to fetch from git."
                )
    endif()
endif()

get_filename_component(DESOUSA_PICO_PATH "${DESOUSA_PICO_PATH}" REALPATH BASE_DIR "${CMAKE_BINARY_DIR}")