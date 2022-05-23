package MyFitnessJourney.VTTP.Project.Fitness.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import MyFitnessJourney.VTTP.Project.Fitness.user.UserModel;
import MyFitnessJourney.VTTP.Project.Fitness.user.UserService;

@Controller
public class HealthyController {

    @Autowired
    private UserService userSvc;

    @GetMapping()
    public ModelAndView login(HttpSession sess) {
        String countLogin = (String)sess.getAttribute("countLogin");
        // System.out.println(">>>>> sess: ");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");

        if (countLogin != null) { 
        // if (countLogin == 1) {
            mav.setStatus(HttpStatus.NOT_FOUND);
            mav.addObject("errorMessage", "Username or password incorrect! Please try again or sign up for an account!");
            // sess.setAttribute("countLogin", null);
            // countLogin = 0;
        } else {
            mav.addObject("errorMessage", "");
            mav.setStatus(HttpStatus.OK);
        }
        return mav;
    }

    @GetMapping("/signUp")
    public ModelAndView newSignUpError(HttpSession sess) {
        String countSignUp = (String)sess.getAttribute("countSignUp");
        
        ModelAndView mav = new ModelAndView();
        mav.setViewName("signUp");

        if (countSignUp != null) {
        // if (countSignUp == 1) {
            mav.addObject("errorMessage", "Username already taken! Try another username!");
            mav.setStatus(HttpStatus.NOT_ACCEPTABLE);
            sess.setAttribute("countSignUp", null);
            // countSignUp = 0;
        } else {
            mav.addObject("errorMessage", "");
            mav.setStatus(HttpStatus.OK);
        }

        return mav;
    }

    @PostMapping("/home")
    public ModelAndView homePage(@ModelAttribute("user") UserModel user, 
        @ModelAttribute("home") String home, HttpSession sess) {
            
        ModelAndView mav = new ModelAndView();
        // System.out.printin(">>>> username: " + user.getUsername());

        if (home.equals("login")) {
            Optional<UserModel> userOpt = userSvc.findUserByUsernameSvc(user);
            if (userOpt.isEmpty()) {
                sess.setAttribute("countLogin", "1");
                // countLogin++;
                return new ModelAndView("redirect:/");
            } else {
                sess.setAttribute("username", user.getUsername());
                sess.setAttribute("password", user.getPassword());
                sess.setAttribute("user", user);
                return new ModelAndView("redirect:/protected/home");
            }
        } else if (home.equals("signUp")) {
            user.setBmi(user.getHeight(), user.getWeight());
            try {
                userSvc.insertNewUserSvc(user);
            } catch (Exception ex) {
                ex.printStackTrace();
                sess.setAttribute("countSignUp", "1");
                // countSignUp++;
                return new ModelAndView("redirect:/signUp");
            }
            sess.setAttribute("username", user.getUsername());
            sess.setAttribute("password", user.getPassword());
            sess.setAttribute("user", user);
            mav = new ModelAndView("redirect:/protected/home");
        }

        return mav;
    }

    @GetMapping("/logout")
    public ModelAndView getLogout(HttpSession sess) {
        sess.invalidate();
        return new ModelAndView("redirect:/");
    }

}
