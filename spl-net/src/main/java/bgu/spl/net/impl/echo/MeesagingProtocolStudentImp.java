package bgu.spl.net.impl.echo;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.srv.Database;

public class MeesagingProtocolStudentImp implements MessagingProtocol<Message> {

    private boolean shouldTerminate = false;

        @Override
        public Message process( Message msg) {
            switch (msg.getMessageName()) {
                case ("STUDENTREG"):
                    if (Database.getInstance().checkIfRegister(msg.getUserName()))
                        return errorMsg();
                    Database.getInstance().register(msg.getUserName(), msg.getUserPassword());
                    return aplliedMsg(msg);

                case ("LOGIN"):
                    if(!Database.getInstance().checkIfRegister(msg.getUserName()))
                        return errorMsg();
                    Database.getInstance().login(msg.getUserName());

                    break;
                case ("LOGOUT"):
                    if(!Database.getInstance().checkIfRegister(msg.getUserName()))
                        return errorMsg();
                    if ((!Database.getInstance().isLogin(msg.getUserName())))
                        return errorMsg();
                    Database.getInstance().logout(msg.getUserName());
                    return aplliedMsg(msg);

                case ("COURSEREG"):
                    if(!Database.getInstance().checkIfPossibleToReg(msg.getCourseNum(), msg.getUserName()))
                        return errorMsg();
                    Database.getInstance().addCourseToKdam(msg.getUserName(), msg.getCourseNum());
                    Database.getInstance().updateLeftCourse(msg.getCourseNum());
                    return aplliedMsg(msg);


                case ("KDAMCHECK"):
                    return aplliedMsg(msg);//todo:the ack should return kdam coursus


                case ("ISREGISTERED"):
                    break;
                case ("UNREGISTER"):
                    break;
                case ("MYCOURSES"):
                    break;

            }
            return errorMsg();

        }

        @Override
        public boolean shouldTerminate() {
            return shouldTerminate;
        }
        public Message aplliedMsg(Message msg){
            return new Message("AKC",msg.getUserName(),msg.getUserPassword());
        }
        public Message errorMsg(){
            return new Message("ERROR",null,null);
        }
    }


