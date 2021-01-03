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

    public MessageACK(Vector<String> data)
    {
        super("12",data);
        this.ACKopCode=data.elementAt(0);
        if (data.size()==1)
         this.opCodeOptional="";
        else
            this.opCodeOptional=data.elementAt(1);
    }


    @Override
    public String toString() {
            return messageType+ACKopCode+opCodeOptional;
    }
}
