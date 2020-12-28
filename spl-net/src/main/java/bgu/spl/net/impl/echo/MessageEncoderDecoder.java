package bgu.spl.net.impl.echo;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
        if (nextByte == '\n') {
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
        Message outMessage;
        String OPCODE = new String(bytes, 0, 4, StandardCharsets.UTF_8);

        len = 0;
        return result;
    }
}
