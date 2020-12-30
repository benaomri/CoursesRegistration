package bgu.spl.net.srv;

import javax.xml.crypto.Data;
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
        KdamCourses=new Vector<>();
    }

    public String toString(){
        String str="Student: <"+userName+">\n";
        str=str+"Courses:"+KdamCourses;
        return  str;
    }
    public void  addCourse(String course){
        KdamCourses.add(course);
    }
    public void  removeCourse(String course){
        KdamCourses.removeElement(course);
    }

}
