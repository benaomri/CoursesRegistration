package bgu.spl.net.srv;

import java.util.Scanner;
import java.util.Vector;
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
    public boolean initialize(String coursesFilePath)  {
        File file=new File(coursesFilePath);
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
     * @return true if register the student
     */
    public boolean register(String name,String password) {
        try {
            registerMapStudent.putIfAbsent(name, new User(name, password));
        } catch (Exception e) {
            System.out.println("Put if absent");
            return false;
        }
        return true;
    }


    /**
     *
     * @param name
     * @param Pass
     * @return true if is the same password
     */
    public boolean checkPassword(String name,String Pass) {
        return registerMapStudent.get(name).userPassword.equals(Pass);
    }

    /**
     *
     * @param name
     * @return true if the user is register
     */
    public boolean checkIfRegister(String name)
    {
        System.out.println(registerMapStudent.containsKey(name));
        return registerMapStudent.containsKey(name);
    }

    /**
     *
     * @param name
     * change login status to true if the user is not login
     */
    public synchronized void login(String name){
        registerMapStudent.get(name).login=!isLogin(name);
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
    public void makeAdmin(String name){
        registerMapStudent.get(name).isAdmin=true;
    }
    public boolean isAdmin(String name){
        return registerMapStudent.get(name).isAdmin;
    }

    /**
     *
     * @param courseNum
     * @param userName
     * @return if its pissible to register to course
     */
    public boolean checkIfPossibleToReg(String courseNum,String userName){

        return  checkIfCourseExist(courseNum)&&//check course exist in the dataBase
                checkSpaceInCourse(courseNum)&&///check course has space
                checkKdam(courseNum, userName);//checking that the student has all the kdam courses

    }

    /**
     *
     * @param courseNum
     * @return if there course exist in the dataBase
     */
    public boolean checkIfCourseExist(String courseNum){
        return coursesMap.containsKey(courseNum);
    }

    /**
     *
     * @param courseNum
     * @return true if there is space to reg t course
     */
    public boolean checkSpaceInCourse(String courseNum){
        return coursesMap.get(courseNum).numOfLeftSeats>0;
    }


    /**
     *
     * @param courseNum
     * @param userName
     * @return true if userName has all the kdam course for the courseNum
     */
    public boolean checkKdam(String courseNum,String userName){//checking that the student has all the kdam courses
        return   !registerMapStudent.get(userName).KdamCourses.contains(courseNum)&&
                  registerMapStudent.get(userName).KdamCourses.containsAll(coursesMap.get(courseNum).KdamCoursesList);
        }


    /**
     * register a student to course
     * @param userName
     * @param courseNumber
     */
     public synchronized void registerToCourse(String userName, String courseNumber){
         registerMapStudent.get(userName).addCourse(courseNumber);//add to the student that he did the course
        coursesMap.get(courseNumber).regStudent(userName);
     }

    /**
     *
     * @param userName
     * @param courseNumber
     */
    public synchronized void unregisterToCourse(String userName, String courseNumber) {
        registerMapStudent.get(userName).removeCourse(courseNumber);
        coursesMap.get(courseNumber).unregStudent(userName);
    }

    /**
     *
     * @param user
     * @return
     */
    public String getMyCourse(String user){
        return registerMapStudent.get(user).KdamCourses.toString();
    }

    /**
     *
     * @param userName
     * @param courseNum
     * @return
     */
    public boolean checkRegStudentToCourse(String userName,String courseNum){
        return coursesMap.get(courseNum).studentsRegToCourse.contains(userName);
    }

    /**
     *
     * @param coursNum
     * @return
     */
    public Vector<String> getKdam(String coursNum){
        return coursesMap.get(coursNum).KdamCoursesList;
    }

    /**

     * @param course
     * @return
     */
    public String courseStat(String course){
        return coursesMap.get(course).toString();
    }

    public String studentStat(String student){
        return registerMapStudent.get(student).toString();
    }

    @Override
    public String toString() {
        return "Database{" +
                "registerMapStudent=" + registerMapStudent.toString() +
                ", coursesMap=" + coursesMap.toString() +
                '}';
    }


}
