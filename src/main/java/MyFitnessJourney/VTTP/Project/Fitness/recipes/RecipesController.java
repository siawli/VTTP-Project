package MyFitnessJourney.VTTP.Project.Fitness.recipes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

    @Autowired
    private RecipesService recipeSvc;

    @PostMapping
    public String recipesForm() {
        return "recipesSearch";
    }

    @GetMapping("/search")
    public ModelAndView searchRecipes(@RequestParam String query, 
        @RequestParam(name = "MealTypes", required = false) String mealTypes,
        @RequestParam(name="maxCalories", required = false, defaultValue = "100000") Integer maxCalories) {

        ModelAndView mav = new ModelAndView();

        Optional<List<RecipesModel>> listOfRecipesOpt = 
            recipeSvc.getRecipes(query, mealTypes, maxCalories);

            
        // System.out.println(">>>>>> " + listOfRecipesOpt.get().get(0).getLabel());

        
        if (listOfRecipesOpt.isEmpty()) {
            mav.setViewName("recipesSearchError");
            return mav;
        }

        System.out.println(">>>>>> " + listOfRecipesOpt.get().get(0).getLabel());

        mav.setViewName("recipes");
        mav.addObject("listRecipes", listOfRecipesOpt.get());
        return mav;
    }
    
}
