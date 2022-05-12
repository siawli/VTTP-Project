package MyFitnessJourney.VTTP.Project.Fitness.exercise.model;

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

}
