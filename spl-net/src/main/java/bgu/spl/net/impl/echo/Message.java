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

    @Override
    public String toString() {
        String[] sArray=this.data.toString().split(",");
        for (int i=0;i<sArray.length;i++){
            if (i==0)
                sArray[0]=sArray[0].substring(1);
            if(i==sArray.length-1)
                sArray[i]=sArray[i].substring(0,sArray[i].length()-1);
            if (i>0)
                sArray[i]=sArray[i].substring(1);
        }


        String acc=sArray[0];
        for (int i=1;i<sArray.length;i++){
            acc = acc + sArray[i]+'\0';
        }
        return messageType+acc;
    }
}
