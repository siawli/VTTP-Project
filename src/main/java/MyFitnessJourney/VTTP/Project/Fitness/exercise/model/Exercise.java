package MyFitnessJourney.VTTP.Project.Fitness.exercise.model;

import java.util.LinkedList;
import java.util.List;

public class Exercise {

    private String title;
    private String date;
    private String timestamp;
    private int calories;
    private String username;
    private List<ExcerciseSet> individualEx = new LinkedList<>();
    
    public List<ExcerciseSet> getIndividualEx() {
        return individualEx;
    }
    public void setIndividualEx(List<ExcerciseSet> individualEx) {
        this.individualEx = individualEx;
    }
    public void addIndividualEx(ExcerciseSet ex) {
        this.individualEx.add(ex);
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
