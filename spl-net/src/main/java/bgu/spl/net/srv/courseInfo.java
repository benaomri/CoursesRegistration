package bgu.spl.net.srv;

import java.awt.*;

public class courseInfo {
    String courseNum;
    String courseName;
    List KdamCoursesList;
    int numOfMaxStudents;
   public courseInfo(String courseNum,String courseName,List kdamCoursesList,int numOfMaxStudents){
       this.courseNum=courseNum;
       this.courseName=courseName;
       this.KdamCoursesList=kdamCoursesList;
       this.numOfMaxStudents=numOfMaxStudents;
   }
    public String getCourseNum(){
        return courseNum;
    }
    public String getCourseName(){
        return courseName;
    }
    public int getNumOfMaxStudents(){
        return numOfMaxStudents;
    }
    public List getKdamCorseList() {
        return KdamCoursesList;
    }

    }
