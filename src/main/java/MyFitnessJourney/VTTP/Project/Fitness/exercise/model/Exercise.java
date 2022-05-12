package MyFitnessJourney.VTTP.Project.Fitness.exercise.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.util.MultiValueMap;

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


    public static Exercise createEx(Exercise ex, MultiValueMap<String, String> form, String usernameOri) {
        ex.setCalories(Integer.parseInt(form.getFirst("calories")));
        ex.setDate(form.getFirst("date"));
        ex.setTitle(form.getFirst("title"));
        ex.setUsername(usernameOri);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        ex.setTimestamp(now.toString());

        //System.out.println("username: " + ex.getUsername());

        // System.out.println(">>>> ex: " + ex.toString());
        // System.out.println(">>> exList: " + ex.getIndividualEx().get(1).getDescription());
        return ex;
    }
   
}
