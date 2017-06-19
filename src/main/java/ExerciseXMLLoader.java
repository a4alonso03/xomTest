

import nu.xom.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Alonso Garita on 6/19/2017.
 */
public class ExerciseXMLLoader {

    private File file;

    //XML label exercise element
    private static final String EXERCISE_EXERCISE_LABEL = "exercise";
    private static final String EXERCISE_ID_LABEL = "id";
    private static final String EXERCISE_PROBLEM_LABEL = "problem";
    private static final String EXERCISE_TOPICS_LABEL = "topics";
    private static final String EXERCISE_ANSWERS_LABEL = "answers";

    //XML label topic element
    private static final String TOPIC_TOPIC_LABEL = "topic";
    private static final String TOPIC_NAME_LABEL = "name";
    private static final String TOPIC_NUMBER_LABEL = "num";

    //XML label answer element
    private static final String ANSWER_ANSWER_LABEL = "answer";
    private static final String ANSWER_TEXT_LABEL = "text";
    private static final String ANSWER_PROCEDURE_LABEL = "procedure";

    //XML label procedure element
    private static final String PROCEDURE_PROC_LABEL = "proc";
    private static final String PROCEDURE_AUTHOR_LABEL = "author";


    public List<Exercise> parseExercises() {
        Builder parser = new Builder();
        try {
            Document document = parser.build(this.file);
            List<Exercise> exerciseList = new LinkedList<Exercise>();
            Element root = document.getRootElement();
            Elements children = root.getChildElements(EXERCISE_EXERCISE_LABEL);

            for(int i = 0; i < children.size(); i++){
                Exercise exercise = extractExercise(children.get(i));
                exerciseList.add(exercise);
            }

            return exerciseList;
        }catch (ParsingException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Exercise extractExercise(Element element){ //TODO:Revisar la parte de XOMUtils y si hace diferencia
        Exercise exercise = new Exercise();
        exercise.setId(element.getAttributeValue(EXERCISE_ID_LABEL));
        exercise.setProblem(element.getAttributeValue(EXERCISE_PROBLEM_LABEL));

        extractExerciseTopics(element, exercise);
        extractExerciseAnswers(element, exercise);

        return exercise;
    }



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
        /*Create new answer*/
        Answer answer = new Answer();
        /*Set its parameters*/
        answer.setText(inputElement.getAttributeValue(ANSWER_TEXT_LABEL));
        answer.setExercise(inputExercise);

        /*Extract its only procedure if it has one*/
        Element element;
        if((element = inputElement.getFirstChildElement(ANSWER_PROCEDURE_LABEL)) != null) {
            Procedure procedure = new Procedure();
            procedure.setAuthor(element.getAttributeValue(PROCEDURE_AUTHOR_LABEL));
            procedure.setProc(element.getAttributeValue(PROCEDURE_PROC_LABEL));
            procedure.setAnswer(answer);

            answer.setProcedure(procedure);
        }
        return answer;
    }


    public void setFile(String input) {
        this.file = new File(input);
    }

}
