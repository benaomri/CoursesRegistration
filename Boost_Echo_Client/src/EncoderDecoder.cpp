//
// Created by spl211 on 29/12/2020.
//
#include <iostream>
#include <ctime>
#include <vector>
#include "string"


class EncoderDecoder{

//#define ADMINREG "0001"
//#define STUDENTREG 0002
//#define LOGIN 0003
//#define LOGOUT 0004
//#define COURSEREG 0005
//#define KDAMCHECK 0006
//#define COURSESTAT 0007
//#define STUDENTSTAT 0008
//#define ISREGISTER 0009
//#define UNREGISTER 0010
//#define MYCOURSES 0011
//#define ACK 0012
//#define ERR 0013

public:
     std::string opcodeToSend(std::string messege){
          std::cout<<"encoderdecoder"<<std::endl;
        std::string opToReturn=messege.substr(0,messege.find_first_of(' '));

           if(opToReturn =="ADMINREG") return "00 01";
            if(opToReturn =="STUDENTREG") return "00 02";
//            if(opToReturn =="LOGIN") return 03;
//        if(opToReturn =="LOGOUT") return 04;
//        if(opToReturn =="COURSEREG") return 05;
//        if(opToReturn =="KDAMCHECK") return 06;
//        if(opToReturn =="COURSESTAT") return 07;
//        if(opToReturn =="STUDENTSTAT") return 100;
//        if(opToReturn =="ISREGISTER") return 101;
//        if(opToReturn =="UNREGISTER") return 10;
//        if(opToReturn =="MYCOURSES") return 11;
//        if(opToReturn =="ACK") return 12;
//        if(opToReturn =="ERR") return 13;
//            default:return "err";
//            switch (opToReturn) {
//            case ("ADMINREG"): return "0001";
//            case ("STUDENTREG"): return "0002";
//            case ("LOGIN"): return "0003";
//            case ("LOGOUT"): return "0004";
//            case ("COURSEREG"): return "0005";
//            case ("KDAMCHECK"): return "0006";
//            case ("COURSESTAT"): return "0007";
//            case ("STUDENTSTAT"): return "0008";
//            case ("ISREGISTER"): return "0009";
//            case ("UNREGISTER"): return "0010";
//            case ("MYCOURSES"): return "0011";
//            case ("ACK"): return "0012";
//            case ("ERR"): return "0013";
//            default:return "err";

        }
        ////short to byte never use
//    short bytesToShort(char* bytesArr)
//    {
//        short result = (short)((bytesArr[0] & 0xff) << 8);
//        result += (short)(bytesArr[1] & 0xff);
//        return result;
//    }
/**
 *
 * @param answer
 * @return the messege from the server after decode
 */
    std::string decodeOpCode( std::string& answer){
         if(answer.substr(0,5).compare("0012")){
             std::cout<<"answer"<<std::endl;
            std::cout<<answer<<std::endl;
            std::cout<<answer.substr(0,5)<<std::endl;
            std::cout<<answer.substr(5)<<std::endl;
            std::cout<<answer.substr(4)<<std::endl;
            std::cout<<answer.substr(3)<<std::endl;
             return "ACK"+answer.substr(3);

         }

         else  return "ERROR "+answer.substr(3);

     }









};
