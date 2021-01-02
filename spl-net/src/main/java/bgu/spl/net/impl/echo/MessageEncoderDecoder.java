package bgu.spl.net.impl.echo;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Vector;

public class MessageEncoderDecoder implements bgu.spl.net.api.MessageEncoderDecoder<Message> {
    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;

    /**
     * add the next byte to the decoding process
     *
     * @param nextByte the next byte to consider for the currently decoded
     *                 message
     * @return a message if this byte completes one or null if it doesnt.
     */
    @Override
    public Message decodeNextByte(byte nextByte) {
        if (nextByte == '\0') {
            return popString();
        }

        pushByte(nextByte);
        return null; //not a line yet
    }

    /**
     * encodes the given message to bytes array
     *
     * @param message the message to encode
     * @return the encoded bytes
     */
    @Override
    public byte[] encode(Message message) {
        return (message.toString() + "\0").getBytes(); //uses utf8 by default
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }

        bytes[len++] = nextByte;
    }

    private Message popString() {
        //notice that we explicitly requesting that the string will be decoded from UTF-8
        //this is not actually required as it is the default encoding in java.
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        String OPCODE=result.substring(3,5);
        Vector<String> data=stringToVec(result);
        len = 0;
        return new Message(OPCODE,data);

    }
    private Vector<String> stringToVec(String str)
    {
        Vector<String> data=new Vector<>();
        int index=6;
        StringBuilder name= new StringBuilder();
        while(index<str.length()) {

             if(str.charAt(index)==' ') {
                data.add(name.toString());
                name = new StringBuilder();
            }
            else {
                if (!(str.charAt(index)=='[' | str.charAt(index)==']'))
                    name.append(str.charAt(index));
            }
            index++;
        }
        data.add(name.toString());
        return data;

    }
}
