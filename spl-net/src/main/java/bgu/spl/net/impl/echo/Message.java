package bgu.spl.net.impl.echo;

public class Message {
    private String messageName;
    private String userName;
    private String userPassword;


    public Message(String messageName,String userName,String userPassword){
        this.messageName=messageName;
        this.userName=userName;
        this.userPassword=userPassword;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
    public String getCourseNum(){return "2";}

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
