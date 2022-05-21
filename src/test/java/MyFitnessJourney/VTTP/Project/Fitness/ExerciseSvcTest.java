package MyFitnessJourney.VTTP.Project.Fitness;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import MyFitnessJourney.VTTP.Project.Fitness.exercise.ExerciseService;
import MyFitnessJourney.VTTP.Project.Fitness.exercise.model.ExcerciseSet;
import MyFitnessJourney.VTTP.Project.Fitness.exercise.model.Exercise;

@SpringBootTest
public class ExerciseSvcTest {

    @Autowired
    private ExerciseService exSvc;

    @Autowired
    private JdbcTemplate template;

    @Test
    void shouldInsertNewEx() {
        String username = "test";
        Exercise exercise = createEx("Lunges", "Sit-Ups", "Squats");

        exSvc.insertNewExercise(username, exercise);

        Optional<List<Exercise>> exList = exSvc.getAllEx(username, "2022-05-01");

        assertTrue(exList.isPresent());
    }

    @Test
    void shouldNotInsertNewEx() {
        String username = "fail";
        Exercise exercise = createEx("Lunges", "Sit-Ups", "Squats");

        assertThrows(Exception.class, () -> exSvc.insertNewExercise(username, exercise));

        Optional<List<Exercise>> exList = exSvc.getAllEx(username, "2022-05-01");

        assertTrue(exList.isEmpty());
    }

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

    private Exercise createEx(String d1, String d2, String d3) {
        Exercise ex = new Exercise();
        ex.setTitle("EMOM");
        ex.setDate("2022-05-01");
        ex.setCalories(180);
        ex.setUsername("test");
        ex.setSetCount(1);
        ex.setRestInterval("1");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        ex.setTimestamp(now.toString());

        List<String> listExericseDescription = new ArrayList<>();
        listExericseDescription.add(d1);
        listExericseDescription.add(d2);
        listExericseDescription.add(d3);

        for (String des : listExericseDescription) {
            ExcerciseSet exSet = createExSet(now.toString(), des);
            ex.addIndividualEx(exSet);
        }

        return ex;
    }

    private ExcerciseSet createExSet(String timestamp, String description) {
        ExcerciseSet exSet = new ExcerciseSet();
        Random r = new Random();

        exSet.setCount(Float.toString(r.nextFloat(0, 50)));
        exSet.setTimestamp(timestamp);
        exSet.setDescription(description);

        return exSet;
    }

    
}
