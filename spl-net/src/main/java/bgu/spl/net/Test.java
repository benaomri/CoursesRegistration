package bgu.spl.net;
import bgu.spl.net.srv.*;
import bgu.spl.net.impl.echo.*;
import static org.junit.Assert.*;

import java.util.Vector;

public class Test {

    public static void main(String[] args) {




        LineMessageEncoderDecoder encoderDecoder = new LineMessageEncoderDecoder();
        MessagingProtocolImpl prot = new MessagingProtocolImpl();
        Database.getInstance().initialize("/home/spl211/IdeaProjects/CoursesRegistration/spl-net/src/main/java/bgu/spl/net/srv/Courses.txt");

//        System.out.println("------Start Student Check------");
//        //Check Registration
//        Vector<String> Data = new Vector<>();
//        Data.add("Morty\0a123\0");
//        Message newMsg = new Message("02", Data);
//        byte[] arr = encoderDecoder.encode(newMsg);
//        Message msg=null;
//        int i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        Message getMsg=prot.process(msg);
//        assertTrue(Database.getInstance().checkIfRegister("Morty"));
//        assertSame("12", getMsg.getMessageType());
//
//        //Check Login
//        Data.clear();
//        Data.add("Morty\0a123\0");
//        newMsg=new Message("03",Data);
//        assertFalse(Database.getInstance().isLogin("Morty"));
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//       getMsg=prot.process(msg);
//        assertTrue(Database.getInstance().isLogin("Morty"));
//        assertSame("12", getMsg.getMessageType());


        //Check Register To course
//        Vector<String> Data=new Vector<>();
//        Data.add("101");
//        Message newMsg=new Message("05",Data);
//        assertFalse(Database.getInstance().checkRegStudentToCourse("Morty","101"));
//        byte[] arr = encoderDecoder.encode(newMsg);
//        Message msg=null;
//        int i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        Message getMsg=prot.process(msg);
//        assertTrue(Database.getInstance().checkRegStudentToCourse("Morty","101"));
//        assertSame("12", getMsg.getMessageType());


        byte[] thisArr={0,1};
//        System.out.println(bytesToShort(thisArr));
        short s=32700;

        //Check ACK
        Vector<String> d=new Vector<>();
        d.add("04");
        Message ack=new MessageACK(d);
        byte[] a=encoderDecoder.encode(ack);
        int i=0;
        Message recivedMsg=null;
        while (recivedMsg==null)
            recivedMsg=encoderDecoder.decodeNextByte(a[i++]);
        System.out.println(recivedMsg);


        //Check ERR
        d.clear();
        d.add("04");
        Message err=new MessageErr(d);
        a=encoderDecoder.encode(err);
        i=0;
        recivedMsg=null;
        while (i<a.length)
            System.out.print(a[i++]+",");
        System.out.println();
        i=0;

        while (recivedMsg==null)
            recivedMsg=encoderDecoder.decodeNextByte(a[i++]);
        System.out.println(recivedMsg);



//        System.out.println(thisArr.length);
//        System.out.println(bytesToShort(thisArr));
//
//        Data.clear();
//        Data.add("102");
//        newMsg=new Message("00 05",Data);
//        assertFalse(Database.getInstance().checkRegStudentToCourse("Morty","102"));
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        getMsg=prot.process(msg);
//        assertTrue(Database.getInstance().checkRegStudentToCourse("Morty","102"));
//        assertSame("12", getMsg.getMessageType());
//
//        //Try to register Kdam
//        Data.clear();
//        Data.add("311");
//        newMsg=new Message("00 05",Data);
//        assertFalse(Database.getInstance().checkRegStudentToCourse("Morty","311"));
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        getMsg=prot.process(msg);
//        assertFalse(Database.getInstance().checkRegStudentToCourse("Morty","311"));
//        assertSame("13", getMsg.getMessageType());
//
//        //Check If register
//        Data.clear();
//        Data.add("102");
//        newMsg=new Message("00 09",Data);
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        getMsg=prot.process(msg);
//        assertEquals("12 09 REGISTERED",getMsg.toString());
//
//        //Check Mycourse
//       getMsg = getMessage(encoderDecoder,"00 11", prot, Data);
//        assertEquals("12 11 [101, 102]",getMsg.toString());
//
//        //Now try to Do Admin commands
//        Data.clear();
//        Data.add("102");
//        newMsg=new Message("00 07",Data);
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        getMsg=prot.process(msg);
//        assertEquals("13 07",getMsg.toString());
//
//        Data.clear();
//        Data.add("Morty");
//        newMsg=new Message("00 08",Data);
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        getMsg=prot.process(msg);
//        assertEquals("13 08",getMsg.toString());
//
//
//        //Check UnRegister
//        Data.clear();
//        Data.add("102");
//        newMsg=new Message("00 10",Data);
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        getMsg=prot.process(msg);
//        String before=getMsg.toString();
//        //Check MyCourse
//        getMsg = getMessage(encoderDecoder,"00 11", prot, Data);
//        assertNotEquals(getMsg.toString(),before,"Check if the courses student register after cancel is the same");
//        System.out.println("------Finish Student Check------");
//
//
//
//        System.out.println("------Start Admin Check------");
//        Data.clear();
//        Data.add("Admin admin");
//        newMsg=new Message("00 01",Data);
//        assertFalse(Database.getInstance().checkIfRegister("Admin"));
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        getMsg=prot.process(msg);
//        assertTrue(Database.getInstance().checkIfRegister("Admin"));
//        assertSame("12", getMsg.getMessageType());
//
//
//        Data.clear();
//        Data.add("Admin admin");
//        newMsg=new Message("00 03",Data);
//        assertFalse(Database.getInstance().isLogin("Admin"));
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        getMsg=prot.process(msg);
//        assertTrue(Database.getInstance().isLogin("Admin"));
//        assertSame("12", getMsg.getMessageType());
//
//
//
//
//        Data.clear();
//        Data.add("101");
//        newMsg=new Message("00 07",Data);
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        getMsg=prot.process(msg);
//        assertEquals("12 07 \n" +
//                "Course: (101) <Algebra1>\n" +
//                "Seats Available: <1>/<25>\n" +
//                "Students Registered: <[Morty]>",getMsg.toString());
//
//
//        Data.clear();
//        Data.add("Morty");
//        newMsg=new Message("00 08",Data);
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        getMsg=prot.process(msg);
//        assertEquals("12 08 \n" +
//                "Student: <Morty>\n" +
//                "Courses:[101]",getMsg.toString());
//
//
//        //Try now to do student things:
//        Data.clear();
//        Data.add("102");
//        newMsg=new Message("00 05",Data);
//        assertFalse(Database.getInstance().checkRegStudentToCourse("Admin","102"));
//        arr = encoderDecoder.encode(newMsg);
//        msg=null;
//        i=0;
//        while (msg==null)
//            msg=encoderDecoder.decodeNextByte(arr[i++]);
//        getMsg=prot.process(msg);
//        assertFalse(Database.getInstance().checkRegStudentToCourse("Admin","102"));
//        assertEquals("13 05",getMsg.toString());
//
//
//
//
//        System.out.println("------Finish Admin Check------");






    }

    private static Message getMessage(LineMessageEncoderDecoder encoderDecoder,String OPCODE, MessagingProtocolImpl prot, Vector<String> data) {
        Message newMsg;
        byte[] arr;
        Message msg;
        int i;
        Message getMsg;
        data.clear();
        newMsg=new Message(OPCODE, data);
        arr = encoderDecoder.encode(newMsg);
        msg=null;
        i=0;
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
