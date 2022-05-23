// package MyFitnessJourney.VTTP.Project.Fitness;

// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.Optional;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.jdbc.core.JdbcTemplate;

// import MyFitnessJourney.VTTP.Project.Fitness.user.UserModel;
// import MyFitnessJourney.VTTP.Project.Fitness.user.UserService;

// @SpringBootTest
// public class UserSvcTest {

//     @Autowired
//     private UserService userSvc;

//     @Autowired
//     private JdbcTemplate template;

//     @Test
//     void shouldInsertNewUserAndFindAddedUser() {
        
//         UserModel user = new UserModel();
//         user.setUsername("tommy");
//         user.setPassword("tommy");
//         user.setHeight(1.70f);
//         user.setWeight(62f);
//         user.setGoals("Get 6 packs!");
//         user.setBmi(user.getHeight(), user.getWeight());

//         assertTrue(userSvc.insertNewUserSvc(user));

//         Optional<UserModel> userOpt = userSvc.findUserByUsernameSvc(user);
//         assertTrue(userOpt.isPresent());

//         final String SQL_DELETE_USER = "delete from user where username = ?";
//         template.update(SQL_DELETE_USER, "tommy");

//     }

//     // @Test
//     // void shouldBeAbleToFindUser() {
//     //     Optional<UserModel> userOpt = userSvc.findUserByUsernameSvc(createUser("test"));
//     //     assertTrue(userOpt.isPresent());
//     // }

//     private UserModel createUser(String username) {

//         UserModel user = new UserModel();
//         user.setUsername(username);
//         user.setPassword(username);
//         user.setHeight(1.70f);
//         user.setWeight(62f);
//         user.setGoals("Get 6 packs!");
//         user.setBmi(user.getHeight(), user.getWeight());

//         return user;
//     }

//     @BeforeEach
//     void setup() {
//         // insert into user (username, password, height, weight, bmi, goals) values ('siawli', sha1('siaw'), 1.70, 60, 23, 'be fitter');
//         String username = "test";
//         UserModel user = createUser(username);
//         final String SQL_INSERT_USER = 
//             "insert into user (username, password, height, weight, bmi, goals) values (?, sha1(?), ?, ?, ?, ?)";
        
//         template.update(SQL_INSERT_USER, username, user.getPassword(), user.getHeight(), user.getWeight(), user.getBmi(), user.getGoals());
//     }

//     @AfterEach
//     void destroy() {
//         final String SQL_DELETE_USER = 
//             "delete from user where username = 'test'";
//             template.update(SQL_DELETE_USER);
//     }

// }
