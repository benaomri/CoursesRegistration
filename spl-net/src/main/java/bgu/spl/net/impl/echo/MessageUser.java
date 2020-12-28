package bgu.spl.net.impl.echo;

//For OP Code 1,2,3
public class MessageUser extends Message {
    private String password;

    public  MessageUser(String messageType,String userName,String password)
    {
        this.messageType=messageType;
        this.userName=userName;
        this.password=password;
    }

    public  String getPassword()
    {
        return password;
    }
    public  String toString(){
        return  messageType+userName+"\0"+password+"\0";
    }

}
