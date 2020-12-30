package bgu.spl.net;
import bgu.spl.net.srv.*;
import bgu.spl.net.impl.echo.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Vector;

public class Test {

    public static void main(String[] args) {

        MessageEncoderDecoder encoderDecoder = new MessageEncoderDecoder();
        MessagingProtocolImpl prot = new MessagingProtocolImpl();
        Database.getInstance().initialize("/home/spl211/IdeaProjects/CoursesRegistration/spl-net/src/main/java/bgu/spl/net/srv/Courses.txt");

        //Check Registration
        Vector<String> Data = new Vector<>();
        Data.add("Morty a123");
        Message newMsg = new Message("00 02", Data);
        byte[] arr = encoderDecoder.encode(newMsg);
        Message msg=null;
        int i=0;
        while (msg==null)
            msg=encoderDecoder.decodeNextByte(arr[i++]);
        Message getMsg=prot.process(msg);
        assertTrue(Database.getInstance().checkIfRegister("Morty"));
        assertSame("12", getMsg.getMessageType());

        //Check Login
        Data.clear();
        Data.add("Morty a123");
        newMsg=new Message("00 03",Data);
        assertFalse(Database.getInstance().isLogin("Morty"));
        arr = encoderDecoder.encode(newMsg);
        msg=null;
        i=0;
        while (msg==null)
            msg=encoderDecoder.decodeNextByte(arr[i++]);
       getMsg=prot.process(msg);
        assertTrue(Database.getInstance().isLogin("Morty"));
        assertSame("12", getMsg.getMessageType());


        //Check Login
        Data.clear();
        Data.add("Morty a123");
        newMsg=new Message("00 03",Data);
        assertFalse(Database.getInstance().isLogin("Morty"));
        arr = encoderDecoder.encode(newMsg);
        msg=null;
        i=0;
        while (msg==null)
            msg=encoderDecoder.decodeNextByte(arr[i++]);
        getMsg=prot.process(msg);
        assertTrue(Database.getInstance().isLogin("Morty"));
        assertSame("12", getMsg.getMessageType());


    }

    public static byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }
}
