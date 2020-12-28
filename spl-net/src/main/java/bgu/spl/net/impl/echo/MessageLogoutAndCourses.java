package bgu.spl.net.impl.echo;
/**
 * This Class Extents Message
 *
 * This Class supports OP code:4,11
 */

public class MessageLogoutAndCourses extends Message {

    public MessageLogoutAndCourses(String messageType,String userName)
    {
        this.messageType=messageType;
        this.userName=userName;
    }

    public String toString()
    {
        return messageType+userName;
    }
}