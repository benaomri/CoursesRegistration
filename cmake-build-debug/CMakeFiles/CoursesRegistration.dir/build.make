# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.17

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /home/spl211/study/CLion-2020.2.4/clion-2020.2.4/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /home/spl211/study/CLion-2020.2.4/clion-2020.2.4/bin/cmake/linux/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/spl211/CLionProjects/CoursesRegistration

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/spl211/CLionProjects/CoursesRegistration/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/CoursesRegistration.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/CoursesRegistration.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/CoursesRegistration.dir/flags.make

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.o: CMakeFiles/CoursesRegistration.dir/flags.make
CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.o: ../BGRSclient/src/connectionHandler.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/CLionProjects/CoursesRegistration/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.o -c /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/connectionHandler.cpp

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/connectionHandler.cpp > CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.i

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/connectionHandler.cpp -o CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.s

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.o: CMakeFiles/CoursesRegistration.dir/flags.make
CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.o: ../BGRSclient/src/BGRSclient.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/CLionProjects/CoursesRegistration/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.o -c /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/BGRSclient.cpp

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/BGRSclient.cpp > CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.i

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/BGRSclient.cpp -o CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.s

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.o: CMakeFiles/CoursesRegistration.dir/flags.make
CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.o: ../BGRSclient/src/Task.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/CLionProjects/CoursesRegistration/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.o -c /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/Task.cpp

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/Task.cpp > CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.i

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/Task.cpp -o CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.s

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.o: CMakeFiles/CoursesRegistration.dir/flags.make
CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.o: ../BGRSclient/src/EncoderDecoder.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/CLionProjects/CoursesRegistration/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.o -c /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/EncoderDecoder.cpp

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/EncoderDecoder.cpp > CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.i

CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/CLionProjects/CoursesRegistration/BGRSclient/src/EncoderDecoder.cpp -o CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.s

# Object files for target CoursesRegistration
CoursesRegistration_OBJECTS = \
"CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.o" \
"CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.o" \
"CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.o" \
"CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.o"

# External object files for target CoursesRegistration
CoursesRegistration_EXTERNAL_OBJECTS =

CoursesRegistration: CMakeFiles/CoursesRegistration.dir/BGRSclient/src/connectionHandler.cpp.o
CoursesRegistration: CMakeFiles/CoursesRegistration.dir/BGRSclient/src/BGRSclient.cpp.o
CoursesRegistration: CMakeFiles/CoursesRegistration.dir/BGRSclient/src/Task.cpp.o
CoursesRegistration: CMakeFiles/CoursesRegistration.dir/BGRSclient/src/EncoderDecoder.cpp.o
CoursesRegistration: CMakeFiles/CoursesRegistration.dir/build.make
CoursesRegistration: CMakeFiles/CoursesRegistration.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/spl211/CLionProjects/CoursesRegistration/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Linking CXX executable CoursesRegistration"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/CoursesRegistration.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/CoursesRegistration.dir/build: CoursesRegistration

.PHONY : CMakeFiles/CoursesRegistration.dir/build

CMakeFiles/CoursesRegistration.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/CoursesRegistration.dir/cmake_clean.cmake
.PHONY : CMakeFiles/CoursesRegistration.dir/clean

CMakeFiles/CoursesRegistration.dir/depend:
	cd /home/spl211/CLionProjects/CoursesRegistration/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/spl211/CLionProjects/CoursesRegistration /home/spl211/CLionProjects/CoursesRegistration /home/spl211/CLionProjects/CoursesRegistration/cmake-build-debug /home/spl211/CLionProjects/CoursesRegistration/cmake-build-debug /home/spl211/CLionProjects/CoursesRegistration/cmake-build-debug/CMakeFiles/CoursesRegistration.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/CoursesRegistration.dir/depend
