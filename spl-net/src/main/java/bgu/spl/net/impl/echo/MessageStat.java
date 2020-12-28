package bgu.spl.net.impl.echo;
/**
 * This Class Extents Message
 *
 * This Class supports OP code:8
 */
public class MessageStat extends Message {

    public MessageStat(String messageType,String userName)
    {
        this.messageType=messageType;
        this.userName=userName;
    }

    public String toString()
    {
        return messageType+userName+"\0";
    }
}
