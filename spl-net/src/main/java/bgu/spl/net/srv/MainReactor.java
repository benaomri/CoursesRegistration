package bgu.spl.net.srv;

import bgu.spl.net.impl.echo.Message;
import bgu.spl.net.impl.echo.MessageEncoderDecoder;
import bgu.spl.net.impl.echo.MessagingProtocolImpl;
import bgu.spl.net.impl.rci.RemoteCommandInvocationProtocol;

public class MainReactor {
    public static void main(String[] args) {
        Database.getInstance().initialize("/home/spl211/IdeaProjects/CoursesRegistration/spl-net/src/main/java/bgu/spl/net/srv/Courses.txt");
        new Reactor(5,7776,
                ()->new MessagingProtocolImpl(),
                MessageEncoderDecoder::new).serve();

//        Server.reactor(
//                Runtime.getRuntime().availableProcessors(),
//                7776, //port
//                () ->  new RemoteCommandInvocationProtocol(), //protocol factory
//                MessageEncoderDecoder::new //message encoder decoder factory
//        ).serve();
//
//    }
    }
}
