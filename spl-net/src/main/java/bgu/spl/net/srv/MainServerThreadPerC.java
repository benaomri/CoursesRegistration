package bgu.spl.net.srv;


import bgu.spl.net.impl.echo.LineMessageEncoderDecoder;
import bgu.spl.net.impl.echo.MessagingProtocolImpl;


public class MainServerThreadPerC {
    public static void main(String[] args) {

         new ThredPerClientServer(7776, () -> new MessagingProtocolImpl(), LineMessageEncoderDecoder::new).serve();
    }
}
