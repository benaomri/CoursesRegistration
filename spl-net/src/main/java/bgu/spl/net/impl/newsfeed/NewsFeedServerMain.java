package bgu.spl.net.impl.newsfeed;

import bgu.spl.net.impl.echo.MeesagingProtocolStudentImp;
import bgu.spl.net.impl.echo.Message;
import bgu.spl.net.impl.echo.MessageEncoderDecoder;
import bgu.spl.net.impl.rci.ObjectEncoderDecoder;
import bgu.spl.net.impl.rci.RemoteCommandInvocationProtocol;
import bgu.spl.net.srv.Server;

public class NewsFeedServerMain {


    public static void main(String[] args) {
        NewsFeed feed = new NewsFeed(); //one shared object
//        Message mss=new Message();

// you can use any server...
//        Server.threadPerClient(
//                7777, //port
//                () -> new RemoteCommandInvocationProtocol<>(feed), //protocol factory
//                ObjectEncoderDecoder::new //message encoder decoder factory
//        ).serve();

//        Server.reactor(
//                Runtime.getRuntime().availableProcessors(),
//                7776, //port
//                () ->  new RemoteCommandInvocationProtocol<>(feed), //protocol factory
//                ObjectEncoderDecoder::new //message encoder decoder factory
//        ).serve();
        Server.reactor(
                Runtime.getRuntime().availableProcessors(),
                7776, //port
                () ->  new RemoteCommandInvocationProtocol(), //protocol factory
                MessageEncoderDecoder::new //message encoder decoder factory
        ).serve();

    }
}
