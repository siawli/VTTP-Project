package MyFitnessJourney.VTTP.Project.Fitness;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import MyFitnessJourney.VTTP.Project.Fitness.recipes.RecipesModel;
import MyFitnessJourney.VTTP.Project.Fitness.recipes.RecipesService;

@SpringBootTest
public class RecipeSvcTest {

    @Autowired
    private RecipesService recipeSvc;

    @Test
    void shouldReturnNonEmptyList() {

        Optional<List<RecipesModel>> listRecipes 
            = recipeSvc.getRecipes("pizza", "breakfast", 1000000);
        assertTrue(listRecipes.isPresent());
    }

    @Test
    void shouldReturnEmptyList() {

        Optional<List<RecipesModel>> listRecipes 
            = recipeSvc.getRecipes("abcdefg", "breakfast", 1000000);
        assertTrue(listRecipes.isEmpty());
    }
    
}
