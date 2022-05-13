package MyFitnessJourney.VTTP.Project.Fitness.recipes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@Service
public class RecipesService {

    @Value("${eda.app.id}")
    private String appId;

    @Value("${eda.app.key}")
    private String appKey;
    
    final String DEFAULT_URL = "https://api.edamam.com/api/recipes/v2";
    
    public Optional<List<RecipesModel>> getRecipes(String query, String mealTypes, Integer maxCalories) {

        String[] mealTypesArr = null;
        // query = query.replace(" ", "%20");
        // System.out.println(">>>>> query: " + query);
        UriComponentsBuilder urlB = UriComponentsBuilder.fromUriString(DEFAULT_URL)
                        .queryParam("q", query)
                        .queryParam("type", "public")
                        .queryParam("app_id", appId)
                        .queryParam("app_key", appKey);

        if (mealTypes != null) {
            mealTypesArr = mealTypes.split(",");
            if (mealTypesArr.length != 5) {
                urlB.queryParam("mealType", (Object[])mealTypesArr);
            }
        }

        if (maxCalories != 100000) {
            urlB.queryParam("calories", maxCalories.toString());
        }

        String url = urlB.toUriString();
        System.out.println(">>>>> url + maxCal: " + url);

        RequestEntity<Void> req = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        System.out.println(">>>> resp: " + resp.toString());
        JsonObject data = getJsonObjFromResp(resp);
        System.out.println(">>>>> data: " + data.toString());

        List<RecipesModel> listOfRecipes = new LinkedList<>();
        
        JsonArray recipesArr = data.getJsonArray("hits");
        System.out.println(">>>>>>> recipes: " + recipesArr.toString());
        for (JsonValue recipe : recipesArr) {
            RecipesModel indivRecipe = new RecipesModel();
            
            JsonObject recipeDetails = recipe.asJsonObject().getJsonObject("recipe");
            indivRecipe.setLabel(recipeDetails.getString("label"));

            JsonObject imagesSizes = recipeDetails.getJsonObject("images");
            JsonObject imagesThumbnail = imagesSizes.getJsonObject("THUMBNAIL");
            indivRecipe.setImage(imagesThumbnail.getString("url"));

            JsonArray cautionsArr = recipeDetails.getJsonArray("cautions");
            for (JsonValue caution : cautionsArr) {
                indivRecipe.addCautions(caution.toString());
            }

            JsonArray ingredientLines = recipeDetails.getJsonArray("ingredientLines");
            for (JsonValue ingredientLine : ingredientLines) {
                indivRecipe.addIngredientLine(ingredientLine.toString().replace("\"", ""));
            }

            JsonObject totalDaily = recipeDetails.getJsonObject("totalDaily").getJsonObject("ENERC_KCAL");
            indivRecipe.setCalories(totalDaily.getInt("quantity"));

            listOfRecipes.add(indivRecipe);
        }

        System.out.println(">>>>> listSize: " + listOfRecipes.size());

        if (listOfRecipes.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(listOfRecipes);
    }


    private JsonObject getJsonObjFromResp(ResponseEntity<String> resp) {
        JsonObject data = null;
        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            JsonReader reader = Json.createReader(is);
            data = reader.readObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return data;
    }
    
}
