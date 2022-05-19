package MyFitnessJourney.VTTP.Project.Fitness.recipes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.StackWalker.Option;
import java.net.MalformedURLException;
import java.net.URL;
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

    // @Value("${eda.app.id}")
    // private String appId;

    // @Value("${eda.app.key}")
    // private String appKey;

    private static final String appId = System.getenv("EDA_APP_ID");
    private static final String appKey = System.getenv("EDA_APP_KEY");
    public List<RecipesModel> listOfRecipes = new LinkedList<>();
    
    final String DEFAULT_URL = "https://api.edamam.com/api/recipes/v2";
    
    public Optional<List<RecipesModel>> getRecipes(String query, String mealTypes, Integer maxCalories) {

        String[] mealTypesArr = null;
        String url = null;

        UriComponentsBuilder urlB = UriComponentsBuilder
                        .fromUriString(DEFAULT_URL)
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

        System.out.println("calories: " + maxCalories.toString());
        if (maxCalories != 100000) {
            urlB.queryParam("calories", maxCalories.toString());
        }

        url = urlB.toUriString();

        /* 
        String url = "https://api.edamam.com/api/recipes/v2?q=chocolate%20brownie&type=public&app_id=0d2abaa4&app_key=041ecf38e812638aff96a58efd15ad85";
        RequestEntity<Void> req = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        This method not working for two words input due to inputStream error??? :(
        */

        int count = 0;
        listOfRecipes = new LinkedList<>();

        while (count < 100) {
            InputStream is = null;
            try {
                is = new URL(url).openStream();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            JsonReader reader = Json.createReader(is);
            JsonObject data = reader.readObject();

            if (data.getInt("to") == 0) {
                return Optional.empty();
            } 
            
            JsonArray recipesArr = data.getJsonArray("hits");
            for (JsonValue recipe : recipesArr) {
                RecipesModel indivRecipe = new RecipesModel();
                
                JsonObject recipeDetails = recipe.asJsonObject().getJsonObject("recipe");
                indivRecipe.setLabel(recipeDetails.getString("label"));

                indivRecipe.setUrl(recipeDetails.getString("url"));

                JsonObject imagesSizes = recipeDetails.getJsonObject("images");
                JsonObject imagesThumbnail = imagesSizes.getJsonObject("REGULAR");
                indivRecipe.setImage(imagesThumbnail.getString("url"));

                JsonArray ingredientLines = recipeDetails.getJsonArray("ingredientLines");
                for (JsonValue ingredientLine : ingredientLines) {
                    indivRecipe.addIngredientLine(ingredientLine.toString().replace("\"", ""));
                }

                JsonObject totalDaily = recipeDetails.getJsonObject("totalDaily").getJsonObject("ENERC_KCAL");
                indivRecipe.setCalories(totalDaily.getInt("quantity"));

                listOfRecipes.add(indivRecipe);
            }
            try {
                JsonObject _links = data.getJsonObject("_links");
                url = _links.getJsonObject("next").getString("href");
                System.out.println(">>>> url:" + url);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

            count = listOfRecipes.size();
            // System.out.println(">>>>> count: " + count);
        }

        System.out.println(">>>> sizeOfListOfRecipes: " + listOfRecipes.size());

        return Optional.of(listOfRecipes);
    }

    public List<RecipesModel> getListRecipes() {
        return listOfRecipes;
    }

    public Optional<List<RecipesModel>> pagination(int page) {
        // List<RecipesModel> listRecipesReq = new LinkedList<>();

        if (listOfRecipes.size() > page*10) {
            return Optional.of(listOfRecipes.subList(page*10-10, page*10));
        } else if (listOfRecipes.size() < page*10) {
            return Optional.empty();
        } else {
            return Optional.of(listOfRecipes.subList(page*10-10, listOfRecipes.size()));
        }
    }


    /*
    seems to have error here when used with ResponseEntity 
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
    */
    
}
