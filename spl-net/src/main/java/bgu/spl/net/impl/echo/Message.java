package bgu.spl.net.impl.echo;
/**
 * This Father Class Message
 *
 */
public class Message {
    protected String messageType;
    protected String userName;

    //We use this code only in string
    public String getUserName()
    {
        return  userName;
    }
    public void setUserName(String userName)
    {
        this.userName=userName;
    }


}
