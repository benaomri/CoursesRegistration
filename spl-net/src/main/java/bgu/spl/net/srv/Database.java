package bgu.spl.net.srv;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {
    private static class singeltonDatabaseHolder{//static class for
        private static final Database instance=new Database();
    }
    private ConcurrentHashMap<String,User>registerMapStudent;
    private ConcurrentHashMap<String,courseInfo>coursesMap;

    //to prevent user from creating new Database
    private Database() {
        registerMapStudent=new ConcurrentHashMap<String, User>();
        coursesMap=new ConcurrentHashMap<String, courseInfo>();

    }

    /**
     * Retrieves the single instance of this class.
     */
    public static Database getInstance() {
        return singeltonDatabaseHolder.instance;
    }

    /**
     * loades the courses from the file path specified
     * into the Database, returns true if successful.
     */
    boolean initialize(String coursesFilePath)  {
        File file=new File(coursesFilePath);
//        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
      try {
          Scanner scanner=new Scanner(file);
          while (scanner.hasNextLine()){
              String line =scanner.nextLine();
              try {
                  CheckinputTxt checkedLine=new CheckinputTxt(line);
                  courseInfo courseFromLine=new courseInfo(checkedLine.getCourseNum(),checkedLine.getCourseName(),checkedLine.getKdamCorseList(),checkedLine.getNumOfMaxStudents());
                  coursesMap.putIfAbsent(courseFromLine.courseNum,courseFromLine);

              }catch (Exception e){
                  return false;
              }
          }

      }catch (FileNotFoundException e){
          return false;
      }



        return true;
    }

    /**
     *
     * @param name
     * @param password
     */
    public void register(String name,String password){
        registerMapStudent.putIfAbsent(name,new User(name,password));
    }

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public String getPassword(String name) throws Exception {
        if (!(checkIfRegister(name)))
            throw new Exception(name+"is not register");
        return registerMapStudent.get(name).userPassword;
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean checkIfRegister(String name)
    {
        return registerMapStudent.containsKey(name);
    }

    /**
     *
     * @param name
     */
    public void login(String name){
        registerMapStudent.get(name).login=true;
    }

    /**
     *
     * @param name
     * @return true if the user is register and login
     */
    public boolean isLogin(String name){

        return checkIfRegister(name)&&registerMapStudent.get(name).login;
    }

    /**
     *
     * @param name
     */
    public void logout(String name){
        registerMapStudent.get(name).login=false;
    }

    /**
     *
     * @param name
     */
    public void isAdmin(String name){
        registerMapStudent.get(name).isAdmin=true;
    }

    /**
     *
     * @param courseNum
     * @param userName
     * @return if its pissible to register to course
     */
    public boolean checkIfPossibleToReg(String courseNum,String userName){
        return  isLogin(userName)&&//check user is logIn
                checkIfcourseExist(courseNum)&&//check course exist in the dataBase
                checkSpaceInCourse(courseNum)&&///check course has space
                checkKdam(courseNum, userName);//checking that the student has all the kdam courses
    }

    /**
     *
     * @param courseNum
     * @return if there course exist in the dataBase
     */
    public boolean checkIfcourseExist(String courseNum){
        return coursesMap.containsKey(courseNum);
    }
    public boolean checkSpaceInCourse(String courseNum){
        return coursesMap.get(courseNum).numOfLeftSeats>0;
    }


    /**
     *
     * @param courseNum
     * @param userName
     * @return
     */
    public boolean checkKdam(String courseNum,String userName){//checking that the student has all the kdam courses
        return registerMapStudent.get(userName).KdamCourses.containsAll(coursesMap.get(courseNum).KdamCoursesList);


        }

    /**
     *
     * @param userName
     * @param courseNum
     */
    public void addCourseToKdam(String userName,String courseNum){
        registerMapStudent.get(userName).KdamCourses.add(courseNum);
    }

    /**
     *
     * @param courseNum
     */
    public void updateLeftCourse(String courseNum){
        coursesMap.get(courseNum).numOfLeftSeats--;
    }
    public boolean checkRegStudentToCourse(String userName,String courseNum){
        return coursesMap.get(courseNum).studentsRegToCourse.contains(userName);
    }

 public static void main(String[] args) {
Database d=new Database();
     System.out.println(d.initialize("/home/spl211/IdeaProjects/DataBase/src/Courses.txt"));
     System.out.println(d.coursesMap.get("101").courseNum);

}
}
