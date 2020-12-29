package bgu.spl.net.impl.echo;

import java.util.Vector;

/**
 * This Father Class Message
 *
 */
public class Message {
    protected String messageType;
    protected Vector<String> data;


    public Message(String opCode,Vector<String> data)
    {
        messageType=opCode;
        this.data=data;
    }

    public String getMessageType(){
        return messageType;
    }

    public Vector<String> getData(){
        return data;
    }



}
