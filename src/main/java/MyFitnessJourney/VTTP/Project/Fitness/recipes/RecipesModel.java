package MyFitnessJourney.VTTP.Project.Fitness.recipes;

import java.util.LinkedList;
import java.util.List;

public class RecipesModel {

    private String label;
    private List<String> ingredientLines = new LinkedList<>();
    private int calories;
    private String image;
    private String url;
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public List<String> getIngredientLines() {
        return ingredientLines;
    }
    public void setIngredientLines(List<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }
    public void addIngredientLine(String ingredientLine) {
        this.ingredientLines.add(ingredientLine);
    }
    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    

    
}
