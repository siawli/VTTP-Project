// package MyFitnessJourney.VTTP.Project.Fitness;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.support.rowset.SqlRowSet;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.RequestBuilder;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// import MyFitnessJourney.VTTP.Project.Fitness.user.UserModel;
// import MyFitnessJourney.VTTP.Project.Fitness.user.UserService;

// import static org.hamcrest.Matchers.containsString;
// import static org.junit.Assert.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import java.util.Optional;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class AuthenicationControllerTest {

//     @Autowired
//     private MockMvc mvc;

//     @Autowired
//     private JdbcTemplate template;

//     @Autowired
//     private UserService userSvc;

//     @Test
//     void shouldGetProtectedHomePage() {
//         RequestBuilder req = MockMvcRequestBuilders
//             .get("/protected/home")
//             .sessionAttr("user", createUser("test"))
//             .sessionAttr("username", "test")
//             .sessionAttr("password", "test")
//             .sessionAttr("user", createUser("test"));
            
//         try {
//             this.mvc.perform(req).andExpect(content().string(containsString("Welcome")));
//         } catch (Exception ex) {
//             fail("failed to get home page", ex);
//             return;
//         }

//     }

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
