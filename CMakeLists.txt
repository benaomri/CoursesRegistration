cmake_minimum_required(VERSION 3.17)
project(CoursesRegistration)

set(CMAKE_CXX_STANDARD 11)
link_libraries("-lboost_system")
link_libraries(-pthread)
add_executable(CoursesRegistration
        /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/connectionHandler.cpp
        BGRSclient/src/BGRSclient.cpp
        /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/Task.cpp
        /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/EncoderDecoder.cpp
        )
