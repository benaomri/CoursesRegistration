package bgu.spl.net.srv;


import bgu.spl.net.impl.echo.LineMessageEncoderDecoder;
import bgu.spl.net.impl.echo.MessagingProtocolImpl;


public class MainServerThreadPerC {
    public static void main(String[] args) {
        Database.getInstance().initialize("/home/spl211/IdeaProjects/CoursesRegistration/spl-net/src/main/java/bgu/spl/net/srv/Courses.txt");

         new ThredPerClientServer(7776, () -> new MessagingProtocolImpl(), LineMessageEncoderDecoder::new).serve();
    }
}
