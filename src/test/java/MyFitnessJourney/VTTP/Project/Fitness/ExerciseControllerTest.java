package MyFitnessJourney.VTTP.Project.Fitness;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import MyFitnessJourney.VTTP.Project.Fitness.exercise.ExerciseService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@SpringBootTest
@AutoConfigureMockMvc
public class ExerciseControllerTest {

    @Autowired
    private ExerciseService exSvc;

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldgetUsernameAndPassword() {
        RequestBuilder req = MockMvcRequestBuilders
                            .post("/fitness")
                            .accept(MediaType.TEXT_HTML_VALUE)
                            .param("username", "test")
                            .param("password", "test");


        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex ) {
            fail("cannot inovocate controller");
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        try {
            String payload = resp.getContentAsString();
            //System.out.println(">>> resp: " + payload);
            assertNotNull(payload);
        } catch (Exception ex) {
            fail("cannot retrieve payload");
            return;
        }

    }

    // @Test
    // void shouldLogExercises() {
    //     System.out.println("RUNNING");

    //     RequestBuilder req = MockMvcRequestBuilders
    //                 .post("/fitness/exercise", createForm())
    //                 .contentType(MediaType.APPLICATION_JSON);

    //     // RequestBuilder req = MockMvcRequestBuilders
    //     //         .post("/fitness/exercise", createForm())
    //     //         .accept(MediaType.TEXT_HTML_VALUE);

    //     // RequestBuilder req = MockMvcRequestBuilders
    //     //         .post("/fitness/exercise")
    //     //         .accept(MediaType.TEXT_HTML_VALUE)        
    //     //         .param("name", "test")
    //     //         .param("password", "test")
    //     //         .param("title", "EMOM")
    //     //         .param("date", "2022-05-01")
    //     //         .param("exercise-1", "Lunges")
    //     //         .param("count-1", "20")
    //     //         .param("exercise-2", "Sit-ups")
    //     //         .param("count-2", "20")
    //     //         .param("exercise-3", "Russian Twists")
    //     //         .param("count-3", "20")
    //     //         .param("exercise-4", "Squats")
    //     //         .param("count-4", "20")
    //     //         .param("exercise-5", "")
    //     //         .param("count-5", "1")
    //     //         .param("exercise-6", "")
    //     //         .param("count-6", "1")
    //     //         .param("exercise-7", "")
    //     //         .param("count-7", "1")
    //     //         .param("exercise-8", "")
    //     //         .param("count-8", "1")
    //     //         .param("setCount", "3")
    //     //         .param("restInterval", "3")
    //     //         .param("calories", "80");
        
    //     MvcResult result = null;

    //     try {
    //         result = mvc.perform(req).andReturn();
    //     } catch (Exception ex ) {
    //         fail("cannot inovocate controller");
    //         return;
    //     }
        
    //     MockHttpServletResponse resp = result.getResponse();
    //     try {
    //         String payload = resp.getContentAsString();
    //         System.out.println(">>> ???????: " + payload);
    //         assertNotNull(payload);
    //     } catch (Exception ex) {
    //         fail("cannot retrieve payload");
    //         return;
    //     }
    // }

    @BeforeEach
    void setup() {
        // insert into user (username, password, height, weight, bmi, goals) values ('siawli', sha1('siaw'), 1.70, 60, 23, 'be fitter');
        final String SQL_INSERT_USER = 
            "insert into user (username, password, height, weight, bmi, goals) values ('test',sha1('test'), 1.70, 60, 23 ,'Stronger')";
        
        template.update(SQL_INSERT_USER);
    }

    @AfterEach
    void destroy() {
        final String SQL_DELETE_USER = 
            "delete from user where username = 'test'";
            template.update(SQL_DELETE_USER);
    }

    private JsonObject createForm() {
        JsonObject jsObj = Json.createObjectBuilder()
        .add("name", "test")
        .add("password", "test")
        .add("title", "EMOM")
        .add("date", "2022-05-01")
        .add("exercise-1", "Lunges")
        .add("count-1", "20")
        .add("exercise-2", "Sit-ups")
        .add("count-2", "20")
        .add("exercise-3", "Russian Twists")
        .add("count-3", "20")
        .add("exercise-4", "Squats")
        .add("count-4", "20")
        .add("exercise-5", "")
        .add("count-5", "1")
        .add("exercise-6", "")
        .add("count-6", "1")
        .add("exercise-7", "")
        .add("count-7", "1")
        .add("exercise-8", "")
        .add("count-8", "1")
        .add("setCount", "3")
        .add("restInterval", "3")
        .add("calories", "80")
        .build();

        return jsObj;
    }


    // private Exercise createEx(String d1, String d2, String d3) {
    //     Exercise ex = new Exercise();
    //     ex.setTitle("EMOM");
    //     ex.setDate("2022-05-01");
    //     ex.setCalories(180);
    //     ex.setUsername("test");

    //     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    //     LocalDateTime now = LocalDateTime.now();
    //     ex.setTimestamp(now.toString());

    //     List<String> listExericseDescription = new ArrayList<>();
    //     listExericseDescription.add(d1);
    //     listExericseDescription.add(d2);
    //     listExericseDescription.add(d3);

    //     for (String des : listExericseDescription) {
    //         ExcerciseSet exSet = createExSet(now.toString(), des);
    //         ex.addIndividualEx(exSet);
    //     }

    //     return ex;
    // }

    // private ExcerciseSet createExSet(String timestamp, String description) {
    //     ExcerciseSet exSet = new ExcerciseSet();
    //     Random r = new Random();

    //     exSet.setCount(r.nextFloat(0, 50));
    //     exSet.setSetCount(r.nextInt(0, 10));
    //     exSet.setRestInterval(r.nextFloat(0, 90));
    //     exSet.setTimestamp(timestamp);
    //     exSet.setDescription(description);

    //     return exSet;
    // }


    
}
