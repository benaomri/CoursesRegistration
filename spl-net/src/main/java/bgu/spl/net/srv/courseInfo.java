package bgu.spl.net.srv;


import java.util.Vector;

public class courseInfo {
    String courseNum;
    String courseName;
    Vector<String> KdamCoursesList;
    int numOfMaxStudents;
    int numOfLeftSeats;
    Vector<String> studentsRegToCourse;

   public courseInfo(String courseNum,String courseName,Vector<String> kdamCoursesList,int numOfMaxStudents){
       this.courseNum=courseNum;
       this.courseName=courseName;
       this.KdamCoursesList=kdamCoursesList;
       this.numOfMaxStudents=numOfMaxStudents;
       numOfLeftSeats=numOfMaxStudents;

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
    public Vector<String> getKdamCorseList() {
        return KdamCoursesList;
    }

    }
