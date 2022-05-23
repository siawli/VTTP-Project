package MyFitnessJourney.VTTP.Project.Fitness.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import MyFitnessJourney.VTTP.Project.Fitness.user.UserModel;
import MyFitnessJourney.VTTP.Project.Fitness.user.UserService;

@Controller
@RequestMapping("/protected/{view}")
public class AuthenicateController {

    @Autowired
    private UserService userSvc;
    
    @GetMapping
    @PostMapping
    public ModelAndView post(@PathVariable String view, HttpSession sess) {
        
        ModelAndView mav = new ModelAndView();

        UserModel user = (UserModel)sess.getAttribute("user");
        // System.out.println("AuthenicateControllerUser: " + user.getUsername());
        String username = (String)sess.getAttribute("username");
        String password = (String)sess.getAttribute("password");
        
        user = userSvc.findUserByUsernameSvc(user).get();

        mav.setViewName("homePage");
        mav.addObject("username", username);
        mav.addObject("password", password);
        mav.addObject("bmi", user.getBmi());
        mav.addObject("goals", user.getGoals());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.now();
        DayOfWeek day = date.getDayOfWeek();
        date.format(formatter);
        mav.addObject("date", day + " " + date);

        mav.setStatus(HttpStatus.OK);
        mav.setViewName("homePage");

        return mav;

    }

    
}
