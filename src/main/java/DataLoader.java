
import java.util.List;

/**
 * Created by Alonso Garita on 6/19/2017.
 */
public class DataLoader {
    /*Pertinent services to insert into database*/
    XMLLoader xmlLoader;

    public DataLoader() {
        this.xmlLoader = new XMLLoader();
    }

    public void load(){
        System.out.println("DATA LOAD START");
        try {
            List<Administrator> adminList = xmlLoader.parseAdministrators("classpath:administratorData.xml");
            List<Student> studentList = xmlLoader.parseStudents("classpath:studentData.xml");
            List<Exercise> exerciseList = xmlLoader.parseExercises("classpath:exerciseData.xml");

            //insert every exercise to database;
                //insert every answer to database;
                    //insert every procedure to database;
                //insert every topic to database;

            System.out.println("finished");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
