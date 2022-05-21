package MyFitnessJourney.VTTP.Project.Fitness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldGetRecipeSearchPage() {
        RequestBuilder req = MockMvcRequestBuilders.get("/protected/recipes")
                .accept(MediaType.TEXT_HTML_VALUE).sessionAttr("username", "test");
        try {
            this.mvc.perform(req).andExpect(content().string(containsString("master-chefing")));
        } catch (Exception ex) {
            fail("fail to get recipes search page");
            return;
        }
    }

    @Test
    void shouldGetRecipeSearchPageWithErrorMessage() {
        RequestBuilder req = MockMvcRequestBuilders.get("/protected/recipes")
                .accept(MediaType.TEXT_HTML_VALUE)
                .sessionAttr("username", "test")
                .sessionAttr("count", "1");
        try {
            this.mvc.perform(req).andExpect(content().string(containsString("No search results available! Try another search!")));
        } catch (Exception ex) {
            fail("fail to get recipes search page with error page");
            return;
    }
    }

    @Test
    void shouldReturnSearchRecipesPage() {
        RequestBuilder req = MockMvcRequestBuilders.get("/protected/recipes/search")
            .accept(MediaType.TEXT_HTML_VALUE)
            .queryParam("query", "pizza")
            .queryParam("mealTypes", "breakfast")
            .queryParam("maxCalories", "1000")
            .sessionAttr("username", "test");

        try {
            this.mvc.perform(req).andExpect(redirectedUrl("/protected/recipes/search/pizza/1"));
        } catch (Exception ex) {
            fail("fail to redirect to pagination page");
            return;
        }
    }

    @Test
    void shouldNotReturnSearchRecipesPage() {
        RequestBuilder req = MockMvcRequestBuilders.get("/protected/recipes/search")
            .accept(MediaType.TEXT_HTML_VALUE)
            .queryParam("query", "abcdef")
            .queryParam("mealTypes", "breakfast")
            .queryParam("maxCalories", "1000")
            .sessionAttr("username", "test");
        
        try {
            this.mvc.perform(req).andExpect(redirectedUrl("/protected/recipes"));
        } catch (Exception ex) {
            fail("fail to get redirected to /protected/recipes from failed recipe search");
            return;
        }
    }

    @Test
    void shouldGetPaginationPages() {
        RequestBuilder req = MockMvcRequestBuilders.get("/protected/recipes/search/pizza/1")
                .sessionAttr("username", "test")
                .accept(MediaType.TEXT_HTML_VALUE);

        try {
            this.mvc.perform(req).andExpect(content().string(containsString("pizza")));
        } catch (Exception ex) {
            fail("failed to get search results and paginated page");
            return;
        }
    }
    
}
