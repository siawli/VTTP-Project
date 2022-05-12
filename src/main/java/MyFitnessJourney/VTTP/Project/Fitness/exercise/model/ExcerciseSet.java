package MyFitnessJourney.VTTP.Project.Fitness.exercise.model;

import org.springframework.util.MultiValueMap;

public class ExcerciseSet {

    private String description;
    private Float count;
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
    public Float getCount() {
        return count;
    }
    public void setCount(Float count) {
        this.count = count;
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
                indivExercise.setCount(count);
                indivExercise.setDescription(description);
                indivExercise.setSetCount(Integer.parseInt(form.getFirst("setCount")));
                indivExercise.setRestInterval(Float.parseFloat(form.getFirst("restInterval")));
                ex.addIndividualEx(indivExercise);
                i++;
            }
        }

        return ex;
    }

}
