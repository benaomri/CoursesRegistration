cmake_minimum_required(VERSION 3.17)
project(CoursesRegistration)

set(CMAKE_CXX_STANDARD 11)
link_libraries("-lboost_system")
link_libraries(-pthread)
add_executable(CoursesRegistration
        Boost_Echo_Client/src/connectionHandler.cpp
        Boost_Echo_Client/src/echoClient.cpp
        /home/spl211/CLionProjects/CoursesRegistration/Boost_Echo_Client/src/Task.cpp
        /home/spl211/CLionProjects/CoursesRegistration/Boost_Echo_Client/src/EncoderDecoder.cpp
        )
