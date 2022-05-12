package MyFitnessJourney.VTTP.Project.Fitness.user;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class UserSvcTest {

    @Autowired
    private UserService userSvc;

    @Autowired
    private JdbcTemplate template;

    @BeforeEach
    void setup() {
        // insert into user (username, password, height, weight, bmi, goals) values ('siawli', sha1('siaw'), 1.70, 60, 23, 'be fitter');
        final String SQL_INSERT_USER = 
            "insert into user (username, password, height, weight, bmi, goals) values ('test', sha1('newUser'), 1.80, 65, 19, 'Be able to run 2.4km in <8 mins')";
        
        template.update(SQL_INSERT_USER);
    }

    @AfterEach
    void destroy() {
        final String SQL_DELETE_USER = 
            "delete from user where username = 'test'";
            template.update(SQL_DELETE_USER);
    }

    @Test
    void shouldInsertNewUser() {
        
        UserModel user = new UserModel();
        user.setUsername("fred");
        user.setPassword("fred");
        user.setHeight(1.70f);
        user.setWeight(62f);
        user.setGoals("Get 6 packs!");

        assertTrue(userSvc.insertNewUserSvc(user));

        final String SQL_DELETE_USER = 
            "delete from user where username = 'fred'";
        template.update(SQL_DELETE_USER);
    }


    
}
