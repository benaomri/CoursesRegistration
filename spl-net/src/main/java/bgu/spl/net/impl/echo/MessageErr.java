package bgu.spl.net.impl.echo;

import java.util.Vector;

public class MessageErr extends Message {
    String errOPCODe;
    public MessageErr(String opCode, Vector<String> data) {
        super(opCode, data);
        errOPCODe=data.elementAt(0);
    }

    @Override
    public String toString() {
        return messageType+errOPCODe;
    }
}
