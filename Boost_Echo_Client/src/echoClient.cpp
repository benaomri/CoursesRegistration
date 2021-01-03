#include "boost/asio/socket_base.hpp"
#include "boost/asio.hpp"

#include <stdlib.h>
#include </home/spl211/CLionProjects/CoursesRegistration/Boost_Echo_Client/include/connectionHandler.h>
#include <iostream>
#include <mutex>
#include <thread>
#include "/home/spl211/CLionProjects/CoursesRegistration/Boost_Echo_Client/src/Task.cpp"
#include "string"
#include "queue"
#include "/home/spl211/CLionProjects/CoursesRegistration/Boost_Echo_Client/src/EncoderDecoder.cpp"

/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/
int main (int argc, char *argv[]) {

//    if (argc < 3) {
//        std::cerr << "Usage: " << argv[0] << " host port" << std::endl << std::endl;
//        return -1;
//    }

    std::string host = "127.0.0.1";//argv[1]

    short port = atoi("7776");//argv[2]
    bool continueRun=true;
    ConnectionHandler connectionHandler(host, port);
    if (!connectionHandler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }

	//From here we will see the rest of the ehco client implementation:
	std::queue<std::string>messegeQueue;
	Task readFromKey(messegeQueue);
    std::thread threadReader(&Task::run,readFromKey);
    EncoderDecoder c;
    int len=0;

    while (continueRun) {


        if(!readFromKey.messegeQueue.empty()) {
            std::string line = readFromKey.messegeQueue.front();
            readFromKey.messegeQueue.pop();
             len = line.length();
            if (!connectionHandler.sendLine(line)) {
                std::cout << "Disconnected. Exiting...\n" << std::endl;
                break;
            }
//            std::cout << "Sent " << len+1 << " bytes to server" << std::endl;
        }
////		 connectionHandler.sendLine(line) appends '\n' to the message. Therefor we send len+1 bytes.




        // We can use one of three options to read data from the server:
        // 1. Read a fixed number of characters
        // 2. Read a line (up to the newline character using the getline() buffered reader
        // 3. Read up to the null character
        std::string answer;
        // Get back an answer: by using the expected number of bytes (len bytes + newline delimiter)
        // We could also use: connectionHandler.getline(answer) and then get the answer without the newline char at the end

        //////////////////////////////////





        if(connectionHandler.readableByts()>0) {
            if (!connectionHandler.getLine(answer)) {
                std::cout << "Disconnected. Exiting...\n" << std::endl;
                break;
            }

            len = answer.length();
            // A C string must end with a 0 char delimiter.  When we filled the answer buffer from the socket
            // we filled up to the \n char - we must make sure now that a 0 char is also present. So we truncate last character.



            answer = c.decodeOpCode(answer);
//            std::cout << "Reply: " << answer << " " <<  std::endl << std::endl;
            bool teminateNPrint=c.printAnswer(answer);
            if (teminateNPrint) {
                readFromKey.shouldTermint = teminateNPrint;
                continueRun = !teminateNPrint;
                //connectionHandler.close();
            }

        }

    }
    return 0;
}

