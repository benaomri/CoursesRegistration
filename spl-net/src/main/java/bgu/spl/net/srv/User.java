package bgu.spl.net.srv;

import java.awt.*;
import java.util.Vector;

public class User {
    String userName;
    String userPassword;
    boolean isAdmin;
    boolean login;
    Vector<String> KdamCourses;

    public User(String userName,String userPassword){
        this.userName=userName;
        this.userPassword=userPassword;
        isAdmin=false;
        login=false;
        KdamCourses=null;
    }
}
