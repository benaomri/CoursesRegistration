package bgu.spl.net;
import bgu.spl.net.srv.*;
import bgu.spl.net.impl.echo.*;

import javax.xml.crypto.Data;



import java.util.Vector;

public class Test {
    static LineMessageEncoderDecoder  encoderDecoder = new LineMessageEncoderDecoder();
    static MessagingProtocolImpl prot = new MessagingProtocolImpl();

    public static void main(String[] args) {
        Database.getInstance().initialize("/home/spl211/IdeaProjects/CoursesRegistration/spl-net/src/main/java/bgu/spl/net/srv/Courses.txt");
        Vector<String> data=new Vector<>();










    }

    private static Message getMessage(String OPCODE, Vector<String> data) {
        Message newMsg;
        byte[] arr;
        Message msg;
        int i=0;
        newMsg=new Message(OPCODE, data);
        arr = encoderDecoder.encode(newMsg);
        msg=null;
        while (msg==null)
            msg=encoderDecoder.decodeNextByte(arr[i++]);
        return prot.process(msg);
    }
    public static short bytesToShort(byte[] byteArr)
    {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }
    public static byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }


}
