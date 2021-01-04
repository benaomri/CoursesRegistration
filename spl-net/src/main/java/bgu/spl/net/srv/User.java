package bgu.spl.net.srv;

import javax.xml.crypto.Data;
import java.awt.*;
import java.util.Comparator;
import java.util.Vector;

public class User {
    String userName;
    String userPassword;
    boolean isAdmin;
    boolean login;
    Vector<courseInfo> KdamCourses;

    public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
        isAdmin = false;
        login = false;
        KdamCourses = new Vector<>();
    }

    public String toString() {
        String str = "Student: <" + userName + ">\n";
        if (!KdamCourses.isEmpty()) {
            Comparator<courseInfo> comp=new studentInfoCompratoor();
            KdamCourses.sort(comp);
        }
        String courses="[";
        for(courseInfo course:KdamCourses)
            courses=courses+course.courseNum+",";
        str = str + "Courses:" + courses.substring(0,courses.length()-1)+"]";
        return str;
    }

    public void addCourse(String course) {
        KdamCourses.add(Database.getInstance().getCourse(course));
    }

    public void removeCourse(String course) {
        KdamCourses.removeElement(course);
    }

}


