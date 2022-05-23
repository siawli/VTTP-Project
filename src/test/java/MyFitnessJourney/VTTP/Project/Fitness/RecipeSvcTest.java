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
    void shouldReturnNonEmptyListWithCorrectPagination() {
        Optional<List<RecipesModel>> listRecipes 
            = recipeSvc.getRecipes("brownie", "Snack", 50);
        assertTrue(listRecipes.isPresent());
        assertTrue(recipeSvc.pagination(1).get().size() < 10);
        assertTrue(recipeSvc.pagination(4).isEmpty());
    }

    @Test
    void shouldReturnEmptyList() {
        Optional<List<RecipesModel>> listRecipes 
            = recipeSvc.getRecipes("abcdefg", "Breakfast", 1000000);
        assertTrue(listRecipes.isEmpty());
    }

    // @Test
    // void shouldGetCorrectPaginationWithRespectivePage() {
    //     Optional<List<RecipesModel>> listRecipes 
    //         = recipeSvc.getRecipes("brownie", "Snack", 50);
    //     // this call would only return 2 result
    //     assertTrue(recipeSvc.pagination(1).get().size() < 10);

    //     assertTrue(recipeSvc.pagination(4).isEmpty());

    // }

}
