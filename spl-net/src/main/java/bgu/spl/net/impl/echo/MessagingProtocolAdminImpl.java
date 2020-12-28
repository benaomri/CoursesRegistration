package bgu.spl.net.impl.echo;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.srv.Database;

public class MessagingProtocolAdminImpl implements MessagingProtocol<Message> {
    private boolean shouldTerminate = false;

    @Override
    public Message process( Message msg) {
        switch (msg.getMessageName()) {
            case ("ADMINREG"):
                if (Database.getInstance().checkIfRegister(msg.getUserName()))
                    return errorMsg();
                Database.getInstance().register(msg.getUserName(), msg.getUserPassword());
                Database.getInstance().isAdmin(msg.getUserName());
                return aplliedMsg(msg);

            case ("LOGIN"):
                break;
            case ("LOGOUT"):
                break;
            case ("KDAMCHECK"):
                break;
            case ("COURSESTAT"):
                if(Database.getInstance().checkIfcourseExist(msg.getCourseNum()))
                    return errorMsg();
                return aplliedMsg(msg);//todo: ack retunrn course stat

            case ("STUDENTSTAT"):
                if (!Database.getInstance().isLogin(msg.getUserName()))
                    return errorMsg();
                return aplliedMsg(msg);//todo:ack return student Stat

            case ("ISREGISTERED"):
                if ((checkLogin(msg.getUserName()))||(checkCourseExist(msg.getCourseNum())))
                    return errorMsg();
                return aplliedMsg(msg);//todo: ack return reg status

            case ("UNREGISTER"):
                if(checkLogin(msg.getUserName()))
                if(Database.getInstance().checkRegStudentToCourse(msg.getUserName(), msg.getCourseNum()))
                    return aplliedMsg(msg);
                return errorMsg();


            case ("MYCOURSES"):
                if (!checkLogin(msg.getUserName()))
                    return errorMsg();
                return aplliedMsg(msg);


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
    public boolean checkLogin(String userName){
        return Database.getInstance().checkIfRegister(userName)&&
                Database.getInstance().isLogin(userName);
    }
    public boolean checkCourseExist(String courseNum){
        return Database.getInstance().checkIfcourseExist(courseNum);
    }

}
