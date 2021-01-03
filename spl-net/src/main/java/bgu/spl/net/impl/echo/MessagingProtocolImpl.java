package bgu.spl.net.impl.echo;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.srv.Database;



import java.util.Vector;

public class MessagingProtocolImpl implements MessagingProtocol<Message> {
    private boolean shouldTerminate = false;
    String userName=null;
    boolean isAdmin;

    @Override
    public Message process( Message msg) {
        System.out.println(msg.getMessageType());
        switch (msg.getMessageType()) {
            case ("01"): { //AdminRegister
                String user = msg.getData().elementAt(0);
                String pass = msg.getData().elementAt(1);
                System.out.println("in 01");
                if (Database.getInstance().checkIfRegister(user)||!Database.getInstance().register(user, pass))//
                    return errorMsg("01");
                Database.getInstance().makeAdmin(user);
                System.out.println("now create admin");
                Vector<String> data = new Vector<>();
                data.add("01");
                return ackMsg(data);
            }
            case ("02"): { //StudentRegister
                String user = msg.getData().elementAt(0);
                String pass = msg.getData().elementAt(1);

                if (Database.getInstance().checkIfRegister(user))
                    return errorMsg("02");
                Database.getInstance().register(user, pass);
                Vector<String> data = new Vector<>();
                data.add("02");
                return ackMsg(data);

            }
            case ("03"):{//Login

                if (!isLogin()) {//check if is not login
                    String user = msg.getData().elementAt(0);
                    String pass = msg.getData().elementAt(1);

                    if (!Database.getInstance().checkIfRegister(user)||!Database.getInstance().checkPassword(user,pass))
                        return errorMsg("03");//return err if is not register or wrong password
                    Database.getInstance().login(user);
                    if (Database.getInstance().isLogin(user)) {//check if he succeed to login
                        userName = user;
                        isAdmin = Database.getInstance().isAdmin(user);
                        Vector<String> data = new Vector<>();
                        data.add("03");
                        return ackMsg(data);
                    }
                }
                return errorMsg("03");
            }
            case ("04"): {//LogOut
                if(isLogin()) {
                    if (!Database.getInstance().checkIfRegister(userName))
                        return errorMsg("04");

                    Database.getInstance().logout(userName);
                    Vector<String> data = new Vector<>();
                    data.add("04");
                    userName=null;
                    return ackMsg(data);
                }
                else
                    return errorMsg("04");
            }
            case ("05"): {//RegisterCourse
                if (isLogin()) {
                    if (isAdmin) {
                        System.out.println("is admin op code 5");
                        return errorMsg("05");
                    }else {
                        String courseNum=msg.getData().elementAt(0);
                        System.out.println("course num at op 5 is "+courseNum);
                        if (!Database.getInstance().checkIfPossibleToReg(courseNum, userName)) {
                            System.out.println("checkIfPossibleTo reg"+Database.getInstance().checkIfPossibleToReg(courseNum, userName));
                            return errorMsg("05");
                        }
                        Database.getInstance().registerToCourse(userName, courseNum);
                        Vector<String> data=new Vector<>();
                        data.add("05");
                        return ackMsg(data);
                    }
                }
                else
                    return errorMsg("05");

            }

            case ("06"): {//KDAMCHECK
                if(isLogin()) {
                    String course=msg.getData().elementAt(0);
                    Vector<String> data=new Vector<>();
                    data.add("06");
                    data.add("\n"+Database.getInstance().getKdam(course).toString());
                    return ackMsg(data);
                }
                else
                    return errorMsg("06");

            }
            case ("07"): {//"COURSESTAT"
                if (isLogin()&&isAdmin) {
                    String course=msg.getData().elementAt(0);
                    if (!Database.getInstance().checkIfcourseExist(course))
                        return errorMsg("07");
                    Vector<String> data=new Vector<>();
                    data.add("07");
                    data.add("\n"+Database.getInstance().courseStat(course));
                    return ackMsg(data);
                }
                else
                    return errorMsg("07");
            }
            case ("08"): {//STUDENTSTAT
                if (isLogin()&&isAdmin) {
                    String student=msg.getData().elementAt(0);
                    System.out.println("in op 08 student name is"+student);
                    Vector<String> data=new Vector<>();
                    data.add("08");
                    data.add("\n"+Database.getInstance().studentStat(student));
                    return ackMsg(data);
                }
                else
                    return errorMsg("08");
            }
            case ("09") :{//"ISREGISTERED"
                if(isLogin()&&!isAdmin) {
                    String course=msg.getData().elementAt(0);
                    Vector<String> data=new Vector<>();
                    data.add("09");
                    if (Database.getInstance().checkRegStudentToCourse(userName,course))
                        data.add("\nREGISTERED");
                    else
                        data.add("\nNOT REGISTERED");
                    return ackMsg(data);
                }
                else
                    return errorMsg("09");
            }
            case ("10"): {//UNREGISTER
                if(isLogin()&&!isAdmin) {
                    String course = msg.getData().elementAt(0);
                    if (Database.getInstance().checkRegStudentToCourse(userName, course)) {
                        Database.getInstance().unregisterToCourse(userName, course);
                        Vector<String> data = new Vector<>();
                        data.add("10");
                        return ackMsg(data);
                    }
                }
                return errorMsg("10");
            }
            case ("11"): {//MYCOURSES
                if (isLogin() && !isAdmin) {
                    Vector<String> data=new Vector<>();
                    data.add("11");
                    data.add("\n"+Database.getInstance().getMyCourse(userName));
                    return ackMsg(data);
                } else
                    return errorMsg("11");

            }
        }
        return errorMsg("13");

    }



    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
    public Message ackMsg(Vector<String> data){
        return new MessageACK(data);
    }
    public Message errorMsg(String OP){
        Vector<String> err=new Vector<>();
        err.add(OP);
        return new MessageErr(err);
    }
    public boolean checkLogin(String userName){
        return Database.getInstance().checkIfRegister(userName)&&
                Database.getInstance().isLogin(userName);
    }
    public boolean checkCourseExist(String courseNum){
        return Database.getInstance().checkIfcourseExist(courseNum);
    }

    public  boolean isLogin(){
        return userName!=null;
    }

}