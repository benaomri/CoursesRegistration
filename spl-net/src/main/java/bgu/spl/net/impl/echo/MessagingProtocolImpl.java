package bgu.spl.net.impl.echo;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.srv.Database;

public class MessagingProtocolImpl implements MessagingProtocol {
    private boolean shouldTerminate=false;
    @Override
    public Object process(Message msg) {
        switch (msg.getMessageName()){
            case ("ADMINREG"):
                if (Database.getInstance().checkIfRegister(msg.getUserName()))
                    return errorMsg();
                Database.getInstance().register(msg.getUserName(), msg.getUserPassword());
                return aplliedMsg(msg);
                break;
            case ("STUDENTREG"):
                if (Database.getInstance().checkIfRegister(msg.getUserName()))
                    return errorMsg();
                Database.getInstance().register(msg.getUserName(), msg.getUserPassword());
                return aplliedMsg(msg);
                break;
            case("LOGIN"):
                break;
            case ("LOGOUT"):
                break;
            case ("COURSEREG"):
                break;
            case("KDAMCHECK"):
                break;
            case("COURSESTAT"):
                break;
            case("STUDENTSTAT"):
                break;
            case("ISREGISTERED"):
                break;
            case ("UNREGISTER"):
                break;
            case ("MYCOURSES"):
                break;

        }

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
