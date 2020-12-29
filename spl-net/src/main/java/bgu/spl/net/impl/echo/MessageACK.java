package bgu.spl.net.impl.echo;

import java.util.Vector;

/**
 * This Class Extents Message
 *
 * This Class supports OP code:12
 */

public class MessageACK extends Message {

    String ACKopCode;
    String opCodeOptional;

    public MessageACK(String messageType, Vector<String> data)
    {
        super(messageType,data);
        this.messageType=messageType;
        this.ACKopCode=data.elementAt(0);
        if (data.size()==1)
         this.opCodeOptional=null;
        else
            this.opCodeOptional=data.elementAt(1);
    }


    @Override
    public String toString() {
        if (opCodeOptional!=null)
            return messageType+ACKopCode+opCodeOptional;
        else
            return messageType+ACKopCode;


    }
}
