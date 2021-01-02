package bgu.spl.net.impl.echo;

import bgu.spl.net.api.MessageEncoderDecoder;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Vector;


public class LineMessageEncoderDecoder implements MessageEncoderDecoder<Message>{
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;
    String OPCODE="00";
    int numOfZero=1;
    int numOfByteToRead=-1;
    int i=0;


    /**
     * encodes the given message to bytes array
     *
     * @param message the message to encode
     * @return the encoded bytes
     */
    public byte[] encode(Message message) {
        String op=message.getMessageType();
        if (op.equals("13")){
            short otherOP= Short.parseShort(message.getData().elementAt(0));
            return  shortToBytes(Short.parseShort(op),otherOP);
        }
        else
            return ackByte(message);

    }

    private  boolean twoParam(String op){return (op.equals("05")|op.equals("06")|op.equals("07")|op.equals("09")|op.equals("10")|op.equals("13"));
    }
    private  boolean oneParam(String op){
        return (op.equals("04")|op.equals("11"));
    }
    private byte[] ackByte(Message msg){
        String optional=msg.toString().substring(5);
        byte[] string=optional.getBytes();
        short op= Short.parseShort(msg.getData().elementAt(0));
        byte[] opCodes=shortToBytes((short) 12,op);
        byte[] toReturn=new byte[opCodes.length+string.length];
        for (int i=0;i<toReturn.length;i++){
            if(i<opCodes.length)
                toReturn[i]=opCodes[i];
            else
                toReturn[i]=string[i-string.length];


        }

        return  toReturn;
    }
    /**
     * add the next byte to the decoding process
     *
     * @param nextByte the next byte to consider for the currently decoded
     *                 message
     * @return a message if this byte completes one or null if it doesnt.
     */
    public Message decodeNextByte(byte nextByte) {
        //Stop argument for '\0'
        if ((nextByte == '\0')&(len>0)){
            System.out.println(i);
            if ((numOfZero==1)) {
                return popString();
            }
            else {
                numOfZero--;
                OPCODE="00";
            }
        }

        pushByte(nextByte);
        //Stop argument for limited bytes
        if (len==numOfByteToRead){
            return popString();

        }
        i++;
        return null; //not a line yet
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }
        //Setting the expected finishing code
        if (len==2) {
            byte[] op={bytes[0],bytes[1]};
            short newOP=bytesToShort(op);
            if (newOP>9)
             OPCODE =""+newOP;
            else
                OPCODE="0"+newOP;
            System.out.println("OPCODE is "+OPCODE);
            if (NumOfZeros()) {
                numOfZero=setNumberOfZeros();
            }
            else
                numOfByteToRead();

            System.out.println("numOfZero IZ "+numOfZero);
        }
        bytes[len++] = nextByte;

    }
    private  boolean NumOfZeros(){
        return (OPCODE.equals("01")|OPCODE.equals("02")|OPCODE.equals("03")|OPCODE.equals("08")|OPCODE.equals("12"));
    }
    private int setNumberOfZeros(){
        if(OPCODE.equals("01")|OPCODE.equals("02")|OPCODE.equals("03"))
            return 2;
        else
            return 1;

    }
    private  void numOfByteToRead()
    {
        if (OPCODE.equals("04")|OPCODE.equals("11"))
            numOfByteToRead=2;
        else
            numOfByteToRead=4;

    }
    private Message popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
//        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        int start=2;
        Vector<String > data=new Vector<>();
        String OPCODE = "" + bytesToShort(bytes);

        if (twoParam(OPCODE)) {
            short course = bytesToShort(bytes, 2);
            Vector<String> d = new Vector<>();
            if(course>9)
                d.add("" + course);
            else
                d.add("0" + course);
            len = 0;
            return new Message(OPCODE, d);

        }
        else if (oneParam(OPCODE)) {
            len = 0;
            return new Message(OPCODE, new Vector<>());
        }
        else if (OPCODE.equals("12")){
            byte[] newOP={bytes[2],bytes[3]};
            short op=bytesToShort(newOP);
            if(op>10)
                data.add(""+op);
            else
                data.add("0"+op);
            start=4;
        }


        String result = new String(bytes, start, len, StandardCharsets.UTF_8);
        data.addAll( stringToVec(result));
        len = 0;
        return new Message(OPCODE, data);


    }
    private Vector<String> stringToVec(String str)
    {
        Vector<String> data=new Vector<>();
        int index=2;
        String name="";
        while(index<str.length()) {
            if(str.charAt(index)=='\0') {
                data.add(name);
                name="";
            }
            else {
                name = name + str.charAt(index);
            }
            index++;
        }
        data.add(name);
        return data;

    }
    public static short bytesToShort(byte[] byteArr)
    {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }
    public static short bytesToShort(byte[] byteArr,int i)
    {
        short result = (short)((byteArr[i] & 0xff) << 8);
        result += (short)(byteArr[i+1] & 0xff);
        return result;
    }

    public static byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }
    public static byte[] shortToBytes(short num,short course)
    {
        byte[] bytesArr = new byte[4];
        byte[] c=shortToBytes(course);
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        bytesArr[2] = c[0];
        bytesArr[3] = c[1];
        return bytesArr;
    }
}
