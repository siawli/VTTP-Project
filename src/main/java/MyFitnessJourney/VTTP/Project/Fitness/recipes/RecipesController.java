package MyFitnessJourney.VTTP.Project.Fitness.recipes;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/protected/recipes")
public class RecipesController {

    @Autowired
    private RecipesService recipeSvc;

    @GetMapping
    public ModelAndView recipesForm(HttpSession sess) {
        ModelAndView mav = new ModelAndView();
        String count = (String)sess.getAttribute("count");
        if (count != null) {
            mav.addObject("errorMessage", "No search results available! Try another search!");
            mav.setStatus(HttpStatus.NOT_FOUND);
            sess.setAttribute("count", null);
        } else {
            mav.addObject("errorMessage", "");
            mav.setStatus(HttpStatus.OK);
        }

        mav.setViewName("recipesSearch");
        return mav;
    }

    @GetMapping("/search")
    public ModelAndView searchRecipes(@RequestParam("query") String query, 
        @RequestParam(name = "mealTypes", required = false) String mealTypes,
        @RequestParam(name="maxCalories", required = false, defaultValue = "100000000") Integer maxCalories, HttpSession sess) {

        Optional<List<RecipesModel>> listOfRecipesOpt = 
            recipeSvc.getRecipes(query, mealTypes, maxCalories);

        if (listOfRecipesOpt.isEmpty()) {
            // count++;
            sess.setAttribute("count", "1");
            return new ModelAndView("redirect:/protected/recipes");
        } else {
            return new ModelAndView("redirect:/protected/recipes/search/%s/1".formatted(query));
        }
    }

    @GetMapping("/search/{query}/{page}")
    public ModelAndView searchRecipesPagination(@PathVariable String query, @PathVariable int page) {

        ModelAndView mav = new ModelAndView();

        Optional<List<RecipesModel>> listRecipesToShowOpt = recipeSvc.pagination(page);

        if (listRecipesToShowOpt.isEmpty()) {
            mav.setViewName("error");
            return mav;
        }


        mav.setViewName("recipes");
        mav.addObject("listRecipes", listRecipesToShowOpt.get());
        mav.addObject("startPage", page + 1);
        mav.addObject("backPage", page-1);
        mav.addObject("query", query);
        System.out.println(">>>> size in controller: " + recipeSvc.getListRecipes().size());
        System.out.println(">>>>>> page: " + page);

        int leftover = recipeSvc.getListRecipes().size() - page*10;
        if (leftover < 0 ) {
            mav.addObject("showNextPageBtn", false);
        } else {
            mav.addObject("showNextPageBtn", true);
        }

        return mav;
    }
    
}
