// package MyFitnessJourney.VTTP.Project.Fitness;

// import static org.junit.Assert.fail;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.when;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.mock.web.MockHttpServletResponse;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.RequestBuilder;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// import static org.hamcrest.Matchers.containsString;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

// import MyFitnessJourney.VTTP.Project.Fitness.user.UserModel;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class HealthyControllerTest {

//     @Autowired
//     private MockMvc mvc;

//     @Autowired
//     private JdbcTemplate template;

//     @Test
//     void shouldGetLoginPage()   {

//         // HttpSession session = mock(HttpSession.class);
//         // when(request.getSession()).thenReturn(session);
//         // when(request.getSession().g etAttribute("countLogin")).thenReturn(null);

//         RequestBuilder req = MockMvcRequestBuilders.get("/")
//                 .accept(MediaType.TEXT_HTML_VALUE).sessionAttr("username", "test");

//         try {
//             this.mvc.perform(req).andExpect(content().string(containsString("Login")));
//         } catch (Exception ex) {
//             fail("fail to get Login page");
//             return;
//         }
//     }


// //     @Test
// //     void shouldGetSignUpPage() {
// //         RequestBuilder req = MockMvcRequestBuilders.get("/signUp")
// //                 .accept(MediaType.TEXT_HTML_VALUE);

// //         try {
// //             this.mvc.perform(req).andExpect(content().string(containsString("Sign Up")));
// //         } catch (Exception ex) {
// //             fail("fail to get Sign Up page");
// //             return;
// //         }
// //     }

// //     @Test
// //     void shouldGetLoginPageBecauseUserDoesNotExist() {
// //         RequestBuilder req = MockMvcRequestBuilders.post("/home")
// //             .accept(MediaType.TEXT_HTML_VALUE)
// //             .param("home", "login")
// //             .flashAttr("user", createUser("tommy"));

// //         try {
// //             mvc
// //             .perform(req)
// //             .andExpect(redirectedUrl("/"));
// //         } catch (Exception ex) {
// //             fail("cannot get redirected url to index page");
// //             return;
// //         }

// //     }

//     // @Test
//     // void shouldGetHomePageAsUserExists() {

//     //     HttpSession session = mock(HttpSession.class);
//     //     when(request.getSession()).thenReturn(session);
//     //     when(request.getSession().getAttribute("username")).thenReturn("test");

//     //     RequestBuilder req = MockMvcRequestBuilders.post("/home")
//     //     .accept(MediaType.TEXT_HTML_VALUE)
//     //     .queryParam("home", "login")
//     //     .flashAttr("user", createUser("test"));
        
//     //     try {
//     //         mvc
//     //         .perform(req)
//     //         .andExpect(redirectedUrl("/protected/home"));
//     //     } catch (Exception ex) {
//     //         fail("cannot get redirected url to /protected/home");
//     //         return;
//     //     }
//     // }

// //     @Test
// //     void shouldGetSignUpAgainAsUsernameTaken() {
// //         RequestBuilder req = MockMvcRequestBuilders.post("/home")
// //             .accept(MediaType.TEXT_HTML_VALUE)
// //             .param("home", "signUp")
// //             .flashAttr("user", createUser("test"));

// //         try {
// //             mvc
// //             .perform(req)
// //             .andExpect(redirectedUrl("/signUp"));
// //         } catch (Exception ex) {
// //             fail("cannot get redirected url to signUp");
// //             return;
// //         }
// //     }

// //     @Test
// //     void shouldGetHomePageAsUsernameNotTaken() {
// //         RequestBuilder req = MockMvcRequestBuilders.post("/home")
// //         .accept(MediaType.TEXT_HTML_VALUE)
// //         .param("home", "signUp")
// //         .flashAttr("user", createUser("test2"));

// //         try {
// //             mvc
// //             .perform(req)
// //             .andExpect(redirectedUrl("/protected/home"));
// //         } catch (Exception ex) {
// //             fail("cannot get redirected url to protected/home");
// //         }

// //         final String SQL_DELETE_USER = 
// //             "delete from user where username = 'test2'";
// //         template.update(SQL_DELETE_USER);
// //     }

// //     @Test
// //     void shouldLogout() {
// //         RequestBuilder req = MockMvcRequestBuilders.get("/logout");
// //         try {
// //             this.mvc.perform(req).andExpect(redirectedUrl("/"));
// //         } catch (Exception ex) {
// //             fail("cannot get redirected url to index page from logout");
// //             return;
// //         }
// //     }

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
//             "insert into user (username, password, height, weight, bmi, goals) values (?, sha1(?), ?, ?, ? ,?)";
        
//         template.update(SQL_INSERT_USER, username, user.getPassword(), user.getHeight(), user.getWeight(), user.getBmi(), user.getGoals());
//     }

//     @AfterEach
//     void destroy() {
//         final String SQL_DELETE_USER = 
//                 "delete from user where username = 'test'";
//             template.update(SQL_DELETE_USER);
//     }
// }
