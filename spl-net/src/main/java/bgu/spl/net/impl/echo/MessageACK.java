package bgu.spl.net.impl.echo;
/**
 * This Class Extents Message
 *
 * This Class supports OP code:12
 */

public class MessageACK extends Message {

    String ACKopCode;
    String opCodeOptional;

    public MessageACK(String messageType,String userName,String ACKopCode)
    {
        this.messageType=messageType;
        this.userName=userName;
        this.ACKopCode=ACKopCode;
        this.opCodeOptional=null;
    }
    public MessageACK(String messageType,String userName,String ACKopCode,String optional)
    {
        this.messageType=messageType;
        this.userName=userName;
        this.ACKopCode=ACKopCode;
        this.opCodeOptional=null;
        this.opCodeOptional=optional;
    }

    @Override
    public String toString() {
        if (opCodeOptional!=null)
            return messageType+userName+ACKopCode+opCodeOptional;
        else
            return messageType+userName+ACKopCode;


    }
}
