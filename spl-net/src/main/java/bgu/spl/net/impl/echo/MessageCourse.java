package bgu.spl.net.impl.echo;


/**
 * This Class Extents Message
 *
 * This Class supports OP code:5,6,7,9,10,12
 */
public class MessageCourse extends Message {
    private  String courseNumber;

    public MessageCourse(String messageType,String userName,String courseNumber)
    {
        this.messageType=messageType;
        this.userName=userName;
        this.courseNumber=courseNumber;
    }

    public String getCourseNumber(){
        return courseNumber;
    }

    @Override
    public String toString() {
        return messageType+userName+courseNumber;
    }
}
