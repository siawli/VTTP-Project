package MyFitnessJourney.VTTP.Project.Fitness.user;

public class UserModel {

    private String username;
    private String password;
    private Float height = 0f;
    private Float weight = 0f;
    private Float bmi = 0f;
    private String goals = null;

    public String getGoals() {
        return goals;
    }
    public void setGoals(String goals) {
        this.goals = goals;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(Float height) {
        this.height = height;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(Float weight) {
        this.weight = weight;
    }
    public Float getBmi() {
        return bmi;
    }
    public void setBmi(Float height, Float weight) {
        this.bmi = weight / (height * height);
    }
    public void setBmi(Float bmi) {
        this.bmi = bmi;
    }
    
}
