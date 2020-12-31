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
            if(opToReturn =="LOGIN") return "00 03";
        if(opToReturn =="LOGOUT") return "00 04";
        if(opToReturn =="COURSEREG") return "00 05";
        if(opToReturn =="KDAMCHECK") return "00 06";
        if(opToReturn =="COURSESTAT") return "00 07";
        if(opToReturn =="STUDENTSTAT") return "00 08";
        if(opToReturn =="ISREGISTER") return "00 09";
        if(opToReturn =="UNREGISTER") return "00 10";
        if(opToReturn =="MYCOURSES") return "00 11";
        if(opToReturn =="ACK") return "00 12";
        if(opToReturn =="ERR") return "00 13";
//            default:return "err";


        }
        ////short to byte never use

/**
 *
 * @param answer
 * @return the messege from the server after decode
 */
    std::string decodeOpCode( std::string& answer){
         if(answer.substr(0,5).compare("0012")){
             std::cout<<"answer"<<std::endl;
             return "ACK " + answer.substr(3);


         }

         else  return "ERROR "+answer.substr(3);

     }









};
