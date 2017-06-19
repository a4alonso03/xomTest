import java.util.List;

/**
 * Created by Alonso Garita on 6/19/2017.
 */
public class ExerciseDataLoader {
    /*Pertinent services to insert into database*/
    ExerciseXMLLoader xmlLoader;

    public ExerciseDataLoader() {
        this.xmlLoader = new ExerciseXMLLoader();
    }

    public void load(){
        System.out.println("START");
        try {
            xmlLoader.setFile("classpath:data.xml");
            List<Exercise> exerciseList = xmlLoader.parseExercises();

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
