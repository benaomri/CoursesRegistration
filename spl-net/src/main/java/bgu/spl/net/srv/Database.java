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
    private ConcurrentHashMap<String,String>registerMapStudent;
    private ConcurrentHashMap<String,courseInfo>coursesMap;

    //to prevent user from creating new Database
    private Database() {
        registerMapStudent=new ConcurrentHashMap<String, String>();
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
    private void register(String name,String password){
        registerMapStudent.putIfAbsent(name,password);
    }
    private String getPassword(String name) throws Exception {
        if (!(checkIfRegister(name)))
            throw new Exception(name+"is not register");
        return registerMapStudent.get(name);
    }
    private boolean checkIfRegister(String name){
        return registerMapStudent.containsKey(name);
    }

 public static void main(String[] args) {
Database d=new Database();
     System.out.println(d.initialize("/home/spl211/IdeaProjects/DataBase/src/Courses.txt"));
     System.out.println(d.coursesMap.get("101").courseNum);

}
}
