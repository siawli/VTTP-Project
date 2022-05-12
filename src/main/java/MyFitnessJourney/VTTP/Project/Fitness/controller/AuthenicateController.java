// package MyFitnessJourney.VTTP.Project.Fitness.controller;

// import java.time.DayOfWeek;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.servlet.ModelAndView;

// import MyFitnessJourney.VTTP.Project.Fitness.model.User;

// @Controller
// @RequestMapping("/authenicate")
// public class AuthenicateController {

//     @PostMapping("/home")
//     public ModelAndView homePage(@ModelAttribute User user) {

//         System.out.println(">>>> username: " + user.getUsername());
//         System.out.println(">>>> password: " + user.getPassword());
        
        
        
//         ModelAndView mav = new ModelAndView();
//         mav.setViewName("homePage");
//         mav.addObject("username", user.getUsername());
//         mav.addObject("bmi", user.getBmi());
//         mav.addObject("goals", user.getGoals());

//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//         LocalDate date = LocalDate.now();
//         DayOfWeek day = date.getDayOfWeek();
//         date.format(formatter);
//         mav.addObject("date", day + " " + date);

//         return mav;
//     }

    
// }
