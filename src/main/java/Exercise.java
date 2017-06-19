import java.util.Set;

/**
 * Created by Alonso Garita on 6/19/2017.
 */
public class Exercise {
    private String id;
    private String problem;
    private Set<Topic> topicList;
    private Set<Answer> answerList;


    public Set<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(Set<Answer> answerList) {
        this.answerList = answerList;
    }

    public Set<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(Set<Topic> topicList) {
        this.topicList = topicList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

}