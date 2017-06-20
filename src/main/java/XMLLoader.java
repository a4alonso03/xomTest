import nu.xom.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Alonso Garita on 6/19/2017.
 */
public class XMLLoader {

    private File file;
    private Builder parser;


    public XMLLoader() {
        parser = new Builder();
    }

    public List<Administrator> parseAdministrators(String inputFilePath){
        try{
            Document document = parser.build(inputFilePath);
            List<Administrator> adminList = new LinkedList<Administrator>();
            Element root = document.getRootElement();
            Elements children = root.getChildElements(StringConstants.ADMIN_ADMIN_LABEL);

            for (int i = 0; i < children.size(); i++) {
                Administrator admin = extractAdmin(children.get(i));
                adminList.add(admin);
            }
            return adminList;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }catch (ParsingException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Administrator extractAdmin(Element inputElement){
        Administrator admin = new Administrator();

        admin.setAdminID(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_ADMINID_LABEL, true));
        admin.setUsername(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_USERNAME_LABEL,true));
        admin.setPassword(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_PASSWORD_LABEL,true));
        admin.setName(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_NAME_LABEL, true));
        admin.setLastName(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_FIRST_LAST_NAME_LABEL, true));
        admin.setSecondLastName(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_SECOND_LAST_NAME_LABEL, false));
        admin.setEnabled(XOMUtils.extractBooleanValue(inputElement, StringConstants.ADMIN_IS_ENABLED_LABEL, true));

        return admin;
    }

    public List<Student> parseStudents(String inputFilePath){
        try {
            Document document = parser.build(inputFilePath);
            List<Student> studentList = new LinkedList<Student>();
            Element root = document.getRootElement();
            Elements children = root.getChildElements(StringConstants.STUDENT_STUDENT_LABEL);

            for (int i = 0; i < children.size(); i++) {
                Student student = extractStudent(children.get(i));
                studentList.add(student);
            }

            return studentList;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }catch (ParsingException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Student extractStudent(Element inputElement) {
        Student student = new Student();

        student.setStudentID(XOMUtils.extractStringValue(inputElement, StringConstants.STUDENT_STUDENTID_LABEL, true));
        student.setUsername(XOMUtils.extractStringValue(inputElement, StringConstants.STUDENT_USERNAME_LABEL,true));
        student.setPassword(XOMUtils.extractStringValue(inputElement, StringConstants.STUDENT_PASSWORD_LABEL, true));
        student.setName(XOMUtils.extractStringValue(inputElement, StringConstants.STUDENT_NAME_LABEL, true));
        student.setLastName(XOMUtils.extractStringValue(inputElement,StringConstants.STUDENT_FIRST_LAST_NAME_LABEL, true));
        student.setSecondLastName(XOMUtils.extractStringValue(inputElement, StringConstants.STUDENT_SECOND_LAST_NAME_LABEL, false));

        return student;
    }

    public List<Exercise> parseExercises(String inputFilePath){
        try {
            Document document = parser.build(inputFilePath);
            List<Exercise> exerciseList = new LinkedList<Exercise>();
            Element root = document.getRootElement();
            Elements children = root.getChildElements(StringConstants.EXERCISE_EXERCISE_LABEL);

            for (int i = 0; i < children.size(); i++) {
                Exercise exercise = extractExercise(children.get(i));
                exerciseList.add(exercise);
            }
            return exerciseList;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }catch (ParsingException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Exercise extractExercise(Element element){
/*      Exercise exercise = new Exercise();
        exercise.setId(element.getAttributeValue(EXERCISE_ID_LABEL));
        exercise.setProblem(element.getAttributeValue(EXERCISE_PROBLEM_LABEL));

        extractExerciseTopics(element, exercise);
        extractExerciseAnswers(element, exercise);

        return exercise;*/
        return null;
    }

/*

    public void extractExerciseTopics(Element inputElement, Exercise inputExercise){

        Element topicElement = inputElement.getFirstChildElement(EXERCISE_TOPICS_LABEL);

        Elements topicsGroup = topicElement.getChildElements(TOPIC_TOPIC_LABEL);

        Set<Topic> topicList = new HashSet<Topic>();

        for (int i = 0; i < topicsGroup.size(); i++) {
            Topic topic = extractExerciseTopic(topicsGroup.get(i), inputExercise);
            topicList.add(topic);
        }
        inputExercise.setTopicList(topicList);
    }

    public Topic extractExerciseTopic(Element inputElement, Exercise inputExercise){
        Topic topic = new Topic();
        topic.setExercise(inputExercise);
        topic.setName(inputElement.getAttributeValue(TOPIC_NAME_LABEL));
        topic.setNum(Integer.valueOf(inputElement.getAttributeValue(TOPIC_NUMBER_LABEL)));

        return topic;
    }


    public void extractExerciseAnswers(Element inputElement, Exercise inputExercise){

        Element answerElement = inputElement.getFirstChildElement(EXERCISE_ANSWERS_LABEL);

        Elements answersGroup = answerElement.getChildElements(ANSWER_ANSWER_LABEL);

        Set<Answer> answerSet = new HashSet<Answer>();

        for(int i = 0; i < answersGroup.size(); i++){
            Answer answer = extractExerciseAnswer(answersGroup.get(i), inputExercise);
            answerSet.add(answer);
        }
        inputExercise.setAnswerList(answerSet);
    }

    private Answer extractExerciseAnswer(Element inputElement, Exercise inputExercise) {
        ///*Create new answer*/
        //Answer answer = new Answer();
        ///*Set its parameters*/
        //answer.setText(inputElement.getAttributeValue(ANSWER_TEXT_LABEL));
        //answer.setExercise(inputExercise);

        ///*Extract its only procedure if it has one*/
        //Element element;
        //if((element = inputElement.getFirstChildElement(ANSWER_PROCEDURE_LABEL)) != null) {
        //    Procedure procedure = new Procedure();
        //    procedure.setAuthor(element.getAttributeValue(PROCEDURE_AUTHOR_LABEL));
        //    procedure.setProc(element.getAttributeValue(PROCEDURE_PROC_LABEL));
        //    procedure.setAnswer(answer);

         //   answer.setProcedure(procedure);
        //}
        //return answer;
    //}
    // Currently not being used
    public void setFile(String input) {
        this.file = new File(input);
    }

}
