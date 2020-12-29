package bgu.spl.net.impl.echo;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.srv.Database;

public class MessagingProtocolImpl implements MessagingProtocol<Message> {
    private boolean shouldTerminate = false;

    @Override
    public Message process( Message msg) {
//        switch (msg.getMessageType()) {
//            case ("ADMINREG"):
//                if (Database.getInstance().checkIfRegister(msg.getFirstData()))
//                    return errorMsg();
//                Database.getInstance().register(msg.getFirstData(), msg.getSecondData());
//                return aplliedMsg(msg);
//            case ("STUDENTREG"):
//                if (Database.getInstance().checkIfRegister(msg.getFirstData()))
//                    return errorMsg();
//                Database.getInstance().register(msg.getFirstData(), msg.getSecondData());
//                return aplliedMsg(msg);
//            case ("LOGIN"):
//                break;
//            case ("LOGOUT"):
//                break;
//            case ("COURSEREG"):
//                break;
//            case ("KDAMCHECK"):
//                break;
//            case ("COURSESTAT"):
//                break;
//            case ("STUDENTSTAT"):
//                break;
//            case ("ISREGISTERED"):
//                break;
//            case ("UNREGISTER"):
//                break;
//            case ("MYCOURSES"):
//                break;
//
//        }
     return null;
    }



    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
//    public Message aplliedMsg(Message msg){
//        return new Message("AKC",msg.getFirstData(),msg.getSecondData());
//    }
//    public Message errorMsg(){
//        return new Message("ERROR",null,null);
//    }
}
