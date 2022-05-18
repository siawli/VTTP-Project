package MyFitnessJourney.VTTP.Project.Fitness;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;

import MyFitnessJourney.VTTP.Project.Fitness.user.UserModel;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import static MyFitnessJourney.VTTP.Project.Fitness.exercise.ExerciseRepo.ExerciseQueries.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExerciseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JdbcTemplate template;

    @Test
    void shouldReturnLogExercisePage() {
        RequestBuilder req = MockMvcRequestBuilders
                .get("/protected/fitness")
                .accept(MediaType.TEXT_HTML_VALUE)
                .sessionAttr("username", "test")
                .sessionAttr("password", "test");

        try {
            this.mvc.perform(req).andExpect(content().string(containsString("On Track to your Fitness Goals")));
        } catch (Exception ex) {
            fail("fail to get exercise form page");
            return;
        }
    }

    @Test
    void shouldReturnExercisesInTheDay() {
        RequestBuilder req = MockMvcRequestBuilders
                .get("/protected/fitness/search/exercises")
                .queryParam("date", "2022-05-18")
                .sessionAttr("username", "test")
                .accept(MediaType.TEXT_HTML_VALUE);

        try {
            this.mvc.perform(req).andExpect(content().string(containsString("Total calories ")));
        } catch (Exception ex) {
            fail("fail to get exercise search by day page with list of ex");
            return;
        }
    }

    @Test
    void shouldReturnNoExercisesDoneForTheDay() {
        RequestBuilder req = MockMvcRequestBuilders
            .get("/protected/fitness/search/exercises")
            .queryParam("date", "2022-05-20")
            .sessionAttr("username", "test")
            .accept(MediaType.TEXT_HTML_VALUE);
            
        try {
            this.mvc.perform(req).andExpect(content().string(containsString("No exercises")));
        } catch (Exception ex) {
            fail("failed to get exercise search with zero exercises");
        }
    }

    // @Test
    // void shouldInsertExercises() {
    //     RequestBuilder req = MockMvcRequestBuilders
    //             .post("/protected/fitness/exercise")
    //             .sessionAttr("username", "test")
    //             .param("form", "{title=[EMOM], date=[2022-05-17], exercise-1=[Lunges], count-1=[20], exercise-2=[Running], count-2=[2.4], exercise-3=[], count-3=[1], exercise-4=[], count-4=[1], exercise-5=[], count-5=[1], exercise-6=[], count-6=[1], exercise-7=[], count-7=[1], exercise-8=[], count-8=[1], setCount=[1], restInterval=[0], calories=[0]}");

    //         try {
    //             this.mvc.perform(req).andExpect(content().string(containsString("Great job")));
    //         } catch (Exception ex) {
    //             fail("failed to logged exercises");
    //         }
    // }

    // @Test
    // void shouldInsertExercisesAndReturnListExOfDay() {
        // RequestBuilder req = MockMvcRequestBuilders
        //         .post("/protected/fitness/exercise")
        //         .sessionAttr("username", "test")
        //         .param("title", "EMOM")
        //         .param("date", "2022-05-18")
        //         .param("exercise-1", "Lunges")
        //         .param("count-1", "20")
        //         .param("exercise-2", "Running")
        //         .param("count-2", "2.4")
        //         .param("exercise-3", "")
        //         .param("count-3", "1")
        //         .param("exercise-4", "")
        //         .param("count-4", "1")
        //         .param("exercise-5", "")
        //         .param("count-5", "1")
        //         .param("exercise-6", "")
        //         .param("count-6", "1")
        //         .param("exercise-7", "")
        //         .param("count-7", "1")
        //         .param("exercise-8", "")
        //         .param("count-8", "1")
        //            .param("restInterval", "0")
        //         .param("calories", "0")
        //         .accept(MediaType.TEXT_HTML_VALUE);
    //     MultiValueMap<String, String> map = createMVM();
    //     RequestBuilder req = MockMvcRequestBuilders
    //         .post("/protected/fitness/exercise")
    //         .sessionAttr("username", "test")
    //         .flashAttr("form", map);
    //     try {
    //         this.mvc.perform(req).andExpect(content().string(containsString("Great job")));
    //     } catch (Exception ex) {
    //         fail("failed to log exercises and exercise page");
    //         return;
    //     }
    // }     .param("setCount", "1")
        // 

    private MultiValueMap<String, String> createMVM() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("title", "EMOM");
        map.add("date", "2022-05-18");
        map.add("exercise-1", "Lunges");
        map.add("count-1", "20");
        map.add("exercise-2", "Running");
        map.add("count-2", "2map.4");
        map.add("exercise-3", "");
        map.add("count-3", "1");
        map.add("exercise-4", "");
        map.add("count-4", "1");
        map.add("exercise-5", "");
        map.add("count-5", "1");
        map.add("exercise-6", "");
        map.add("count-6", "1");
        map.add("exercise-7", "");
        map.add("count-7", "1");
        map.add("exercise-8", "");
        map.add("count-8", "1");
        map.add("setCount", "1");
        map.add("restInterval", "0");
        map.add("calories", "0");

        return map;  
    }

    private UserModel createUser(String username) {

        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(username);
        user.setHeight(1.70f);
        user.setWeight(62f);
        user.setGoals("Get 6 packs!");
        user.setBmi(user.getHeight(), user.getWeight());

        return user;
    }


    @BeforeEach
    void setup() {
        // insert into user (username, password, height, weight, bmi, goals) values ('siawli', sha1('siaw'), 1.70, 60, 23, 'be fitter');
        String username = "test";
        UserModel user = createUser(username);
        final String SQL_INSERT_USER = 
            "insert into user (username, password, height, weight, bmi, goals) values (?, ?, ?, ?, ? ,?)";
        
        template.update(SQL_INSERT_USER, username, user.getPassword(), user.getHeight(), user.getWeight(), user.getBmi(), user.getGoals());

        template.update(SQL_INSERT_EXERCISE, "EMOM", "2022-05-18", "2022-05-18 05:28:54", "180", "test");
        // public static final String SQL_INSERT_EXERCISE = 
        //      "insert into exercise (exercise_title, exercise_date, exercise_time, exercise_calories, username) values (?, ?, ?, ?, ?)";
        template.update(SQL_INSERT_INDIVIDUAL_EX, "Lunges", "20", "1", "2", "2022-05-18 05:28:54");
        template.update(SQL_INSERT_INDIVIDUAL_EX, "Sit-Ups", "20", "1", "2", "2022-05-18 05:28:54");
        // public static final String SQL_INSERT_INDIVIDUAL_EX = 
        //      "insert into exercise_set (step_description, step_count, set_count, set_rest_interval, exercise_time) values (?, ?, ?, ?, ?)";
    }

    @AfterEach
    void destroy() {
        final String SQL_DELETE_USER = 
            "delete from user where username = 'test'";
            template.update(SQL_DELETE_USER);
    }
    
}
