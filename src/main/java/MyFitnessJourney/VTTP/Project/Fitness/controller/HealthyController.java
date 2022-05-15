package MyFitnessJourney.VTTP.Project.Fitness.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import MyFitnessJourney.VTTP.Project.Fitness.user.UserModel;
import MyFitnessJourney.VTTP.Project.Fitness.user.UserService;

@Controller
public class HealthyController {

    private static int countSignUp = 0;
    private static int countLogin = 0;

    @Autowired
    private UserService userSvc;

    /*
    wah not sure is it because haven't learn frontend, but it was so difficult to redirect to the same page and handling the GET vs POST request!!! That's why I have 'count' to see whether the redirection is due to a GET (refresh on page or redirected from login) vs whether it was redirected from the controller due to the error thrown when username is not found/incorrect.
    */
    
    @PostMapping("/login")
    public String login(Model model) {
        model.addAttribute("errorMessage", "");
        return "login";
    }

    @GetMapping("/login")
    public ModelAndView loginError() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        if (countLogin > 0) { 
            mav.setStatus(HttpStatus.NOT_FOUND);
            mav.addObject("errorMessage", "Username or password is incorrect! Please try again or ");
            countLogin = 0;
        } else {
            mav.addObject("errorMessage", "");
            mav.setStatus(HttpStatus.OK);
        }
        return mav;
    }

    @PostMapping("/signUp")
    public String newSignUp(Model model) {
        model.addAttribute("errorMessage", "");
        return "signUp";
    }

    @GetMapping("/signUp")
    public ModelAndView newSignUpError() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("signUp");
        
        if (countSignUp > 0) {
            mav.addObject("errorMessage", "Username already taken! Try another username!");
            mav.setStatus(HttpStatus.NOT_ACCEPTABLE);
            countSignUp = 0;
        } else {
            mav.addObject("errorMessage", "");
            mav.setStatus(HttpStatus.OK);
        }

        return mav;
    }

    @PostMapping("/home")
    public ModelAndView homePage(@ModelAttribute UserModel user, 
        @ModelAttribute("home") String home, @ModelAttribute("username") String name, @ModelAttribute("password") String password, HttpSession sess) {

        ModelAndView mav = new ModelAndView();

        if (home.equals("login")) {
            Optional<UserModel> userOpt = userSvc.findUserByUsernameSvc(user);
            if (userOpt.isEmpty()) {
                countLogin++;
                mav = new ModelAndView("redirect:/login");
            } else {
                sess.setAttribute("username", name);
                sess.setAttribute("password", password);
                sess.setAttribute("user", user);
                mav = new ModelAndView("redirect:/protected/home");
            }
        } else if (home.equals("signUp")) {
            user.setBmi(user.getHeight(), user.getWeight());
            try {
                userSvc.insertNewUserSvc(user);
            } catch (Exception ex) {
                countSignUp++;
                mav = new ModelAndView("redirect:/signUp");
            }
        }

        return mav;
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession sess) {
        sess.invalidate();
        return "index";
    }

}
