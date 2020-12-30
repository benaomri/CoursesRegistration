package bgu.spl.net.srv;


import java.util.Comparator;
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
       studentsRegToCourse=new Vector<>();

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
    public void regStudent(String studentName){
       studentsRegToCourse.add(studentName);
       numOfLeftSeats--;
    }
    public void unregStudent(String studentName){
        studentsRegToCourse.removeElement(studentName);
        numOfLeftSeats++;
    }


    @Override
    public String toString() {
        String str="Course: ("+courseNum+") <"+courseName+">\n";
        str=str+"Seats Available: <"+(numOfMaxStudents-numOfLeftSeats)+">/<"+numOfMaxStudents+">\n";
        if(!studentsRegToCourse.isEmpty())
          studentsRegToCourse.sort(Comparator.naturalOrder());
        str=str+"Students Registered: <"+studentsRegToCourse.toString()+">";
        return str;

    }
}
