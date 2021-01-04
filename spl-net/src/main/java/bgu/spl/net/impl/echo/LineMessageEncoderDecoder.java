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
    /**
     * Check if we in twoParam CASE
     * Case:(5,6,7,9,10)
     * @param op
     * @returnif we in twoParam CASE
     */
    private  boolean twoParam(String op){return (op.equals("05")|op.equals("06")|op.equals("07")|op.equals("09")|op.equals("10")|op.equals("13"));
    }

    /**
     * Check if we in ONEPARAM CASE
     * Case:(4,11)
     * @param op
     * @returnif we in ONEPARAM CASE
     */
    private  boolean oneParam(String op){
        return (op.equals("04")|op.equals("11"));
    }

    /**
     * Encoding ACK msg to byte
     * @param msg of type ACK
     * @return decoded ack msg
     */
    private byte[] ackByte(Message msg){
        String optional=msg.toString().substring(4)+'\0';
        byte[] optionalInBytes=optional.getBytes();
        short op= Short.parseShort(msg.getData().elementAt(0));
        byte[] opCodes=shortToBytes((short) 12,op);
        byte[] toReturn=new byte[opCodes.length+optionalInBytes.length];
        for (int i=0;i<opCodes.length;i++)
                toReturn[i]=opCodes[i];
        for (int j=0;j<optionalInBytes.length;j++)
            toReturn[j+opCodes.length]=optionalInBytes[j];

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
        if (stopTORead(nextByte))
            return popString();
        pushByte(nextByte);
        //Stop argument for limited bytes
        if (len==numOfByteToRead){
            return popString();

        }
        i++;

        if (OPCODE.equals("13"))
            return new Message("13",new Vector<>());

        return null; //not a line yet
    }

    /**
     * Our stopping Function
     * @param nextByte
     * @return if to stop read
     */
    private  boolean stopTORead(byte nextByte) {
            if (len <= 2)
                return false;
            else {
                if (OPCODE.equals("13"))
                    return false;
                else if (twoParam(OPCODE)) {
                    System.out.println("Now in two param in stopTORead");
                    System.out.println("CHECK IF len == 4 "+(len == 4));
                    return len == 4;
                }
                else if (oneParam(OPCODE))
                    return len == 2;
                else if(nextByte == '\0'){
                    if (numOfZero == 1)
                        return true;
                    else {
                        numOfZero--;
                        OPCODE="00";
                        return false;
                    }
                }
                else
                    return false;
            }
    }

    /**
     * Push byte to arrray
     * @param nextByte
     */
    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }
        bytes[len++] = nextByte;
        //Setting the expected finishing code
        if (len==2) {
            byte[] op={bytes[0],bytes[1]};
            short newOP=bytesToShort(op);
            System.out.println("OPCODE in push:"+newOP);
            if (newOP>9)
                OPCODE =""+newOP;
            else
                OPCODE="0"+newOP;
            if (NumOfZeros()) {
                numOfZero=setNumberOfZeros();
            }
            else
                numOfByteToRead();
        }
    }

    /**
     *
     * @return if we in zeros read or in byte read
     */
    private  boolean NumOfZeros(){
        return (OPCODE.equals("01")|OPCODE.equals("02")|OPCODE.equals("03")|OPCODE.equals("08")|OPCODE.equals("12"));
    }

    /**
     * Get how many zeros we need to read
     * @return int num of how many zeros we read
     */
    private int setNumberOfZeros(){
        if(OPCODE.equals("01")|OPCODE.equals("02")|OPCODE.equals("03"))
            return 2;
        else
            return 1;

    }

    /**
     * Set the number of bytes to read in case of @OneParam or @TwoParam
     */
    private  void numOfByteToRead()
    {
        if (OPCODE.equals("04")|OPCODE.equals("11"))
            numOfByteToRead=2;
        else
            numOfByteToRead=4;

    }

    /**
     * From byte array
     * @return message
     */
    private Message popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
//        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));

        int start=2;
        Vector<String > data=new Vector<>();
        short getOP=bytesToShort(bytes);
        String OPCODE;
        if (getOP>9)
            OPCODE = "" +getOP;
        else
            OPCODE="0"+getOP;

        if (twoParam(OPCODE)) {
            short course = bytesToShort(bytes, 2);
            Vector<String> d = new Vector<>();
            if(course>9)
                d.add("" + course);
            else
                d.add("0" + course);
            reINIT();
            return new Message(OPCODE, d);

        }
        else if (oneParam(OPCODE)) {
            reINIT();
            return new Message(OPCODE, new Vector<>());
        }
//        else if (OPCODE.equals("12")){
//            byte[] newOP={bytes[2],bytes[3]};
//            short op=bytesToShort(newOP);
//            if(op>10)
//                data.add(""+op);
//            else
//                data.add("0"+op);
//            start=4;
//        }


        String result = new String(bytes, start, len, StandardCharsets.UTF_8);
        data.addAll( stringToVec(result));
        reINIT();
        return new Message(OPCODE, data);


    }

    /**
     * ReInit all valuse:
     * i,len,bytes
     */
    private void reINIT()
    {
        i=0;
        len = 0;
        bytes = new byte[1 << 10];
        System.out.println(Arrays.toString(bytes));
    }

    /**
     *
     * @param str that we get to create Data
     * @return Data for message
     */
    private Vector<String> stringToVec(String str)
    {
        Vector<String> data=new Vector<>();
        int index=0;
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

    /**
     * From byte[] to Short
     * @param byteArr
     * @return short
     */
    public static short bytesToShort(byte[] byteArr)
    {
        short result = (short)((byteArr[0] & 0xff) << 8);
        result += (short)(byteArr[1] & 0xff);
        return result;
    }

    /**
     * Create short from bytes from index @param i
     * @param byteArr
     * @param i
     * @return short
     */
    public static short bytesToShort(byte[] byteArr,int i)
    {
        short result = (short)((byteArr[i] & 0xff) << 8);
        result += (short)(byteArr[i+1] & 0xff);
        return result;
    }

    /**
     * From short to byte
     * @param num
     * @return byte array
     */
    public static byte[] shortToBytes(short num)
    {
        byte[] bytesArr = new byte[2];
        bytesArr[0] = (byte)((num >> 8) & 0xFF);
        bytesArr[1] = (byte)(num & 0xFF);
        return bytesArr;
    }

    /**
     *  from 2 short to byte
     * @param num OPCODE
     * @param course COURSE OPCODE
     * @return byte array
     */
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
