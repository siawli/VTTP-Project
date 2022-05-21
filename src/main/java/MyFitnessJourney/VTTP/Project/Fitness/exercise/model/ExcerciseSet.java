package MyFitnessJourney.VTTP.Project.Fitness.exercise.model;

import java.text.DecimalFormat;

import org.springframework.util.MultiValueMap;

public class ExcerciseSet {

    private String description;
    private String count; 
    // reason why 'count' is a String is because I want to chop .0 if present
    private String timestamp;
    private int setCount;
    private Float restInterval;

    public Float getRestInterval() {
        return restInterval;
    }
    public void setRestInterval(Float restInterval) {
        this.restInterval = restInterval;
    }
    public int getSetCount() {
        return setCount;
    }
    public void setSetCount(int setCount) {
        this.setCount = setCount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = new DecimalFormat("#.##").format(Double.valueOf(count));
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static Exercise createIndivEx(MultiValueMap<String, String> form) {
        Exercise ex = new Exercise();

        int i = 0;
        while (i<=8) {
            String description = form.getFirst("exercise-%d".formatted(i));
            if ((description == null) || (description.trim().length()) == 0) {
                i++;
            } else {
                String _count = form.getFirst("count-%d".formatted(i));
                Float count = Float.parseFloat(_count);

                ExcerciseSet indivExercise = new ExcerciseSet();
                indivExercise.setCount(count.toString());
                indivExercise.setDescription(description);
                ex.addIndividualEx(indivExercise);
                i++;
            }
        }

        return ex;
    }

}
