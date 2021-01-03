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
        switch (msg.getMessageType()) {
            /**
             * Admin Regestrion
             * In data we have :
             *      1. User Name
             *      2. User Password
             *  We check:
             *      1. That client not login
             *      2. That user not taken
             */
            case ("01"): { //AdminRegister
                if (isLogin())
                    return errorMsg("01");
                String user = msg.getData().elementAt(0);
                String pass = msg.getData().elementAt(1);
                if (Database.getInstance().checkIfRegister(user)||!Database.getInstance().register(user, pass))//
                    return errorMsg("01");
                Database.getInstance().makeAdmin(user);
                Vector<String> data = new Vector<>();
                data.add("01");
                return ackMsg(data);
            }
            /**
             * Student Regestrion
             * In data we have :
             *      1. User Name
             *      2. User Password
             *  We check:
             *      1. That client not login
             *      2. That user not taken
             */
            case ("02"): { //StudentRegister
                if (isLogin())
                    return errorMsg("02");
                String user = msg.getData().elementAt(0);
                String pass = msg.getData().elementAt(1);

                if (Database.getInstance().checkIfRegister(user))
                    return errorMsg("02");
                Database.getInstance().register(user, pass);
                Vector<String> data = new Vector<>();
                data.add("02");
                return ackMsg(data);

            }
            /**
             * Login case
             * In data we have 2 datas:
             *      1. User Name
             *      2. User Password
             *
             * We check before:
             *      1. if client login before
             *      2. if user exist in DB and not login in other client
             *      3. Password is correct
             *  We set:
             *       1. Client is login in DB
             *       2. Init varibale UserName with the user name
             *       3. Flag the Admin Flag
             */
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
            /**
             * Case 4: Logout
             * We get Only OPCODE
             * We check:
             *   1. if user is login
             *   2. if user is register to system
             */
            case ("04"): {
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
            /**
             * Case 05= Register To Course
             * In Data we have the 2'nd Course
             * We check if:
             *    1.If user is login
             *    2.If user is admin
             *    3.If it possible to to register to course
             */
            case ("05"): {
                if (isLogin()) {
                    if (isAdmin) {
                        return errorMsg("05");
                    }else {
                        String courseNum=msg.getData().elementAt(0);
                        if (!Database.getInstance().checkIfPossibleToReg(courseNum, userName)) {
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
            /**
             * Case 06==KDAMCHECK
             * In data we have 2'nd OPCODE of course
             * We check that user is login and that the course is exist
             */
            case ("06"): {
                if(isLogin()) {
                    String course=msg.getData().elementAt(0);

                    if(!Database.getInstance().checkIfCourseExist(course))
                        return errorMsg("06");

                    Vector<String> data=new Vector<>();
                    data.add("06");
                    data.add("\n"+Database.getInstance().getKdam(course).toString());
                    return ackMsg(data);
                }
                else
                    return errorMsg("06");

            }
            /**
             * case 07=="COURSESTAT"
             * We get OPcode of course in data
             * We check:
             *      1.That user is admin
             *      2.That user is login
             *      3.That the course Exist
             */
            case ("07"): {
                if (isLogin()&&isAdmin) {
                    String course=msg.getData().elementAt(0);
                    if (!Database.getInstance().checkIfCourseExist(course))
                        return errorMsg("07");
                    Vector<String> data=new Vector<>();
                    data.add("07");
                    data.add("\n"+Database.getInstance().courseStat(course));
                    return ackMsg(data);
                }
                else
                    return errorMsg("07");
            }
            /**
             * case 08=="STUDENTSTAT"
             * We get student user name in data
             * We check:
             *      1.That user is admin
             *      2.That user is login
             *      3.That the student name Exist
             */
            case ("08"): {
                if (isLogin()&&isAdmin) {
                    String student=msg.getData().elementAt(0);
                    if (!Database.getInstance().checkIfRegister(student))
                        return errorMsg("08");
                    Vector<String> data=new Vector<>();
                    data.add("08");
                    data.add("\n"+Database.getInstance().studentStat(student));
                    return ackMsg(data);
                }
                else
                    return errorMsg("08");
            }
            /**
             * case 09=="ISREGISTERED"
             * We get course OPcode in data
             * We check:
             *      1.That user is not admin
             *      2.That user is login
             *      3.That the course Exist
             */
            case ("09") :{//"ISREGISTERED"
                if(isLogin()&&!isAdmin) {
                    String course=msg.getData().elementAt(0);
                    if (!Database.getInstance().checkIfCourseExist(course))
                        return errorMsg("09");
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
            /**
             * case 10=="UNREGISTER"
             * We get course OPcode in data
             * We check:
             *      1.That user is not admin
             *      2.That user is login
             *      3.That the course Exist
             *      4.That the student register to course
             */
            case ("10"): {
                if(isLogin()&&!isAdmin) {
                    String course = msg.getData().elementAt(0);
                    if (!Database.getInstance().checkIfCourseExist(course))
                        return errorMsg(course);
                    if (Database.getInstance().checkRegStudentToCourse(userName, course)) {
                        Database.getInstance().unregisterToCourse(userName, course);
                        Vector<String> data = new Vector<>();
                        data.add("10");
                        return ackMsg(data);
                    }
                }
                return errorMsg("10");
            }
            /**
             * case 11=="MYCOURSES"
             * We check:
             *      1.That user is not admin
             *      2.That user is login
             */
            case ("11"): {
                if (isLogin() && !isAdmin) {
                    Vector<String> data=new Vector<>();
                    data.add("11");
                    data.add("\n"+Database.getInstance().getMyCourse(userName));
                    return ackMsg(data);
                } else
                    return errorMsg("11");

            }
        }
        //if we get something else we return error error
        return errorMsg("13");

    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }

    /**
     *
     * @param data to put in ACK msg
     * @return ACK msg
     */
    public Message ackMsg(Vector<String> data){
        return new MessageACK(data);
    }

    /**
     * Creating error msg
     * @param OP the OPcode that failed
     * @return Message of type error
     */
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
        return Database.getInstance().checkIfCourseExist(courseNum);
    }

    /**
     *
     * @return if user is login
     */
    public  boolean isLogin(){
        return userName!=null;
    }

}
