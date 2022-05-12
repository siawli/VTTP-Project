package MyFitnessJourney.VTTP.Project.Fitness.exercise;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import MyFitnessJourney.VTTP.Project.Fitness.exercise.model.ExcerciseSet;
import MyFitnessJourney.VTTP.Project.Fitness.exercise.model.Exercise;

import static MyFitnessJourney.VTTP.Project.Fitness.exercise.model.Exercise.*;
import static MyFitnessJourney.VTTP.Project.Fitness.exercise.model.ExcerciseSet.*;

@Controller
@RequestMapping("/fitness")
public class ExerciseController {

    @Autowired
    private ExerciseService exSvc;

    private static String usernameOri;
    private static String passwordOri;
    
    @PostMapping
    public String exerciseForm(Model model, @ModelAttribute("username") String username, @ModelAttribute("password") String password) {
        usernameOri = username;
        passwordOri = password;
        model.addAttribute("errorMessage", "");
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "exercise";
    }

    // @GetMapping
    // public ModelAndView exerciseFormError() {
    //     ModelAndView mav = new ModelAndView();
    //     mav.setViewName("exercise");
    //     mav.addObject("errorMessage", "Please add at least one exercise!");
    //     mav.addObject("username", username);
    //     return mav;
    // }
    
    @PostMapping("/exercise")
    public ModelAndView logExercise(@RequestBody MultiValueMap<String, String> form) {
        //System.out.println("form: " + form.toString());
        ModelAndView mav = new ModelAndView();

        Exercise ex = createIndivEx(form);
        ex = createEx(ex, form, usernameOri);        

        try {
            exSvc.insertNewExercise(usernameOri, ex);
        } catch (Exception e) {
            e.printStackTrace();
            mav.setViewName("test");
        }

        Optional<List<Exercise>> listExOpt = exSvc.getAllEx(usernameOri, form.getFirst("date"));

        // I definitely know I will get a list because I just entered in an exercise
        // Optional is required if I want to query using date only (KIV add other methods; no time!!!)
        List<Exercise> listEx = listExOpt.get();
        int totalCalPerDay = 0;
        for (Exercise exe : listEx) {
            totalCalPerDay += exe.getCalories();
        }

        mav.addObject("totalCalories", totalCalPerDay);
        mav.addObject("listOfExercises", listEx);
        mav.addObject("date", form.getFirst("date"));
        mav.addObject("username", usernameOri);
        mav.addObject("password", passwordOri);
        mav.setViewName("exerciseInTheDay");

        return mav;
    }

}
