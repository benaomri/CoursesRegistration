//
// Created by spl211 on 28/12/2020.
//
#include <iostream>
#include <thread>
#include <mutex>
#include <utility>
#include <vector>
#include <string>
#include "queue"
class Task{

    // std::mutex & _mutex;
public:

    Task (std::queue<std::string>&_messegeQueue)  : messegeQueue(_messegeQueue)  {}
    bool shouldTermint=false;


    void run(){
        while (1){
//        for(int i=0;i<=1;i++){
            if(shouldTermint)
                break;
            const short bufsize = 1024;
            char buf[bufsize];
            std::cin.getline(buf, bufsize);
            std::string line(buf);
            messegeQueue.push(line);
            std::cout<<"thred run"<<std::endl;



        }
    }

    std::queue<std::string> &messegeQueue;
};
