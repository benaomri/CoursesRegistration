//
// Created by spl211 on 29/12/2020.
//
#include <iostream>
#include <ctime>
#include <vector>
#include "string"


class EncoderDecoder{

public:
    /**
     *
     * @param messege from user
     * @return the  op code that fit to the  message if there is not op code for
     * the message return the opcode for error.
     */
   short opcodeToSend(std::string messege){
        std::string opToReturn=messege.substr(0,messege.find_first_of(' '));

        if(opToReturn =="ADMINREG") return 1 ;
        if(opToReturn =="STUDENTREG") return 2 ;
        if(opToReturn =="LOGIN") return 3;
        if(opToReturn =="LOGOUT") return 4;
        if(opToReturn =="COURSEREG") return 5;
        if(opToReturn =="KDAMCHECK") return 6;
        if(opToReturn =="COURSESTAT") return 7;
        if(opToReturn =="STUDENTSTAT") return 8;
        if(opToReturn =="ISREGISTERED") return 9;
        if(opToReturn =="UNREGISTER") return 10;
        if(opToReturn =="MYCOURSES") return 11;
        if(opToReturn =="ACK") return 12;
        return 13;


        }


/**
 *
 * @param answer
 * @return the messege from the server after decode
 * choose between two option: ACK and ERROR
 */
    std::string decodeOpCode( std::string& answer){
        char* opAnswer=new char[2];
        opAnswer[0]=answer.at(0);
        opAnswer[1]=answer.at(1);
        short op=bytesToShort(opAnswer);
        opAnswer= nullptr;

        char*opRespond=new char[2];
        opRespond[0]=answer.at(2);
        opRespond[1]=answer.at(3);
        short respond=bytesToShort(opRespond);
        std::string toReturn="";
        opRespond= nullptr;

        if(op==12){
            toReturn.append("ACK ");
            toReturn+=std::to_string(respond);
            if(answer.substr(4).length()>1)
                toReturn.append(answer.substr(4,answer.length()-2));
            return toReturn  ;
         }
         else {
            toReturn.append("ERROR ");
            toReturn += std::to_string(respond);
            return toReturn;
        }
     }
     /**
      *
      * @param answer
      * @return print the answer from server after decode
      * return true if suppose to logout
      */
     bool printAnswer(std::string answer){
         std::cout << answer << std::endl;

         if (answer.substr(0,3) == "ACK") {
             if(!answer.substr(4).compare("4")) {//check if suppose to logOut
                 std::cout << "Exiting...\n" << std::endl;
                 return true;
             }
         }else{
             return false;
         }
    }
    void shortToBytes(short num, char* bytesArr)
    {
        bytesArr[0]=((num >> 8) & 0xFF);
        bytesArr[1]=(num & 0xFF);


    }
    short bytesToShort(char* bytesArr)
    {
        short result = (short)((bytesArr[0] & 0xff) << 8);
        result += (short)(bytesArr[1] & 0xff);
        bytesArr= nullptr;
        return result;
    }










};
