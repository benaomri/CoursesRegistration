#include </home/spl211/CLionProjects/CoursesRegistration/Boost_Echo_Client/include/connectionHandler.h>
#include <iostream>
#include <mutex>
#include <thread>
#include "/home/spl211/CLionProjects/CoursesRegistration/Boost_Echo_Client/src/EncoderDecoder.cpp"
#include <algorithm>
using boost::asio::ip::tcp;
#include <boost/lexical_cast.hpp>
#include <boost/algorithm/string/replace.hpp>
using std::cin;
using std::cout;
using std::cerr;
using std::endl;
using std::string;
 /**
  *
  * @param host
  * @param port
  */
ConnectionHandler::ConnectionHandler(string host, short port): host_(host), port_(port), io_service_(), socket_(io_service_){}
    /**
     *
     */
ConnectionHandler::~ConnectionHandler() {
    close();
}

bool ConnectionHandler::connect() {
    std::cout << "Starting connect to "
              << host_ << ":" << port_ << std::endl;
    try {
        tcp::endpoint endpoint(boost::asio::ip::address::from_string(host_), port_); // the server endpoint
        boost::system::error_code error;
        socket_.connect(endpoint, error);
        if (error)
            throw boost::system::system_error(error);
    }
    catch (std::exception& e) {
        std::cerr << "Connection failed (Error: " << e.what() << ')' << std::endl;
        return false;
    }
    return true;
}

bool ConnectionHandler::getBytes(char bytes[], unsigned int bytesToRead) {
    size_t tmp = 0;
    boost::system::error_code error;
    try {
        while (!error && bytesToRead > tmp ) {
            tmp += socket_.read_some(boost::asio::buffer(bytes+tmp, bytesToRead-tmp), error);
        }
        if(error)
            throw boost::system::system_error(error);
    } catch (std::exception& e) {
        std::cerr << "recv failed (Error: " << e.what() << ')' << std::endl;
        return false;
    }
    return true;
}
/**
 *
 * @param bytes
 * @param bytesToWrite
 * @return
 */
bool ConnectionHandler::sendBytes(const char bytes[], int bytesToWrite) {
    int tmp = 0;
    boost::system::error_code error;
    try {
        while (!error && bytesToWrite > tmp ) {
            tmp += socket_.write_some(boost::asio::buffer(bytes + tmp, bytesToWrite - tmp), error);
        }
        if(error)
            throw boost::system::system_error(error);
    } catch (std::exception& e) {
        std::cerr << "recv failed (Error: " << e.what() << ')' << std::endl;
        return false;
    }

    return true;
}
/**
 *
 * @param line
 * @return
 */
bool ConnectionHandler::getLine(std::string& line) {
    return getFrameAscii(line, '\0');
}
/**
 *
 * @param line
 * @return
 */
bool ConnectionHandler::sendLine(std::string& line) {

    return sendFrameAscii(line, '\0');
}

/**
 *
 * @param frame
 * @param delimiter
 * @return
 */
bool ConnectionHandler::getFrameAscii(std::string& frame, char delimiter) {
    char ch;
    int getOPC=0;

    // Stop when we encounter the null character.
    // Notice that the null character is not appended to the frame string.
    try {
        do{
            if(!getBytes(&ch, 1))
                return false;

            frame.append(1, ch);
            if(getOPC==3){
                if(frame.at(1)!='\f')
                    ch='\0';
            }
            getOPC++;

        }while ((getOPC<4)|(delimiter != ch));
    } catch (std::exception& e) {
        std::cerr << "recv failed2 (Error: " << e.what() << ')' << std::endl;
        return false;
    }
    return true;
}

/**
 *
 * @param frame
 * @param delimiter
 * @return true if sucssed
 */
bool ConnectionHandler::sendFrameAscii(std::string& frame, char delimiter) {
    EncoderDecoder c;
    short opCode=c.opcodeToSend(frame);
    char* numberChar =new char[2];
    shortToBytes(opCode,numberChar);
    if(opCode==13)//13 is default number to avoid illegal input from client
        return true;

    bool result= sendBytes(numberChar,2);////send op code
    delete[] numberChar;// delete numberChar to avoid leaking

    ////ADMINREG STUDENTREG LOGIN
    if(result&&(opCode==1||opCode==2||opCode==3)){
        std::string subFrame=frame.substr(frame.find_first_of(' ')+1);
        std::string subSubFrame=subFrame.substr(0,subFrame.find_first_of(' '));
        result = sendBytes(subSubFrame.c_str(),subSubFrame.length());
        if(!result) return false;
        result =  sendBytes(&delimiter,1);
        if(!result) return false;
        subSubFrame=subFrame.substr(subFrame.find_first_of(' ')+1);
        result= sendBytes(subSubFrame.c_str(),subSubFrame.length());
        if(!result) return false;
        return  sendBytes(&delimiter,1);
    }
    ////STUDENTSTAT if true send student name finish with \0
    if(result&&(opCode==8)){
        std::string subFrame=frame.substr(frame.find_first_of(' ')+1);
        result = sendBytes(subFrame.c_str(),subFrame.length());
        if(!result) return false;
        return  sendBytes(&delimiter,1);

    }
    ////LOGOUT or MYCOURSES *dont need to send more then opCode
    if(result&&(opCode==4||opCode==11))
        return true;

    //////handdle COURSEREG KDAMCHECK COURSESTAT ISREGISTERED UNREGISTER
    if (result&&(opCode==5||opCode==6||opCode==7||opCode==9||opCode==10)){
        std::string courseNum=frame.substr(frame.find_first_of(' ')+1);// the end of the message course number
        short shortCourseNum = boost::lexical_cast<short>(courseNum);//change string to short
        c.shortToBytes(shortCourseNum,numberChar);//change from short to byte
        return  sendBytes(numberChar,2);

    }

    return result;

}
/**
 * unblocking method
 * @return amount of bytes from server that is possible to read
 */
unsigned long ConnectionHandler::readableByts() {
    boost::asio::socket_base::bytes_readable command(true);
    socket_.io_control(command);
    std::size_t bytes_readable = command.get();
    return bytes_readable ;
}
void ConnectionHandler::shortToBytes(short num, char *bytesArr) {

    bytesArr[0]=((num >> 8) & 0xFF);
    bytesArr[1]=(num & 0xFF);


}

// Close down the connection properly.
void ConnectionHandler::close() {
    try{
        socket_.close();
    } catch (...) {
        std::cout << "closing failed: connection already closed" << std::endl;
    }
}

