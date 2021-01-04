package bgu.spl.net.srv;

import java.awt.*;
import java.util.Vector;

/**
 *
 */
public class CheckinputTxt {
    String line;
    String courseNum;
    String courseName;
    Vector<String> kdamCorseList;
    int numOfMaxStudents;

    /**
     *
     * @param line
     */
    public CheckinputTxt(String line){
        this.line=line;
        setCourseNum();
        setCourseName();
        setKdamCorseList();
        setNumOfMaxStudents();

    }

    /**
     *
     */
    public void setCourseNum() {
        int i=0;
        while (line.charAt(i)!='|'){
             isNumber(line.charAt(i)); //check that the course is legal num
        i++;
    }
        courseNum=line.substring(0,i);//set course num
        line=line.substring(i);//take of from line
    }

    /**
     *
     */
    public void setCourseName() {
        int i=1;
        while (line.charAt(i)!='|'){
            i++;
        }
        courseName=line.substring(1,i);
        line=line.substring(i);

    }

    /**
     *
     */
    public void setKdamCorseList(){
        kdamCorseList=new Vector<String>();
        int i=1;
        while (line.charAt(i)!='|'){
            int j=i;
            StringBuilder toAdd= new StringBuilder();
            if(line.charAt(j)=='[')
                j++;
            if (line.charAt(j)==']') {

                i=j+1;
                break;
            }
            while(line.charAt(j)!=','&&line.charAt(j)!=']'){

                toAdd.append(line.charAt(j));
                j++;

            }


            kdamCorseList.add(toAdd.toString());

            i=j+1;
        }
        line=line.substring(i);

    }

    /**
     *
     */
    public void setNumOfMaxStudents() {
        numOfMaxStudents=0;
        for (int i = 1; i < line.length(); i++) {
            isNumber(line.charAt(i));//check is number
            numOfMaxStudents=numOfMaxStudents*10;//change string to int
            numOfMaxStudents=numOfMaxStudents+(line.charAt(i)-'0');
        }
        if(numOfMaxStudents<5)
            throw new IllegalArgumentException("there is less then 5 places in course");
    }

    /**
     *
     * @param a
     * check if a is number
     */
    public void isNumber(char a){//check that the course is legal num
      if(  a<'0'||a>'9')
        throw new IllegalArgumentException("illegal input");

    }

    /**
     *
     * @return course num
     */
    public String getCourseNum(){
        return courseNum;
    }

    /**
     *
     * @return course name
     */
    public String getCourseName(){
        return courseName;
    }

    /**
     *
     * @return the number of the max student that can register to course
     */
    public int getNumOfMaxStudents(){
        return numOfMaxStudents;
    }

    /**
     *
     * @return vector of kdam course
     */
    public Vector<String> getKdamCorseList(){
        return kdamCorseList;
    }
}

