package MyFitnessJourney.VTTP.Project.Fitness.exercise;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import MyFitnessJourney.VTTP.Project.Fitness.exercise.model.Exercise;

import static MyFitnessJourney.VTTP.Project.Fitness.exercise.model.Exercise.*;
import static MyFitnessJourney.VTTP.Project.Fitness.exercise.model.ExcerciseSet.*;

@Controller
@RequestMapping("protected/fitness")
public class ExerciseController {

    @Autowired
    private ExerciseService exSvc;

    @GetMapping
    public String exerciseForm() {
        return "exercise";
    }
    
    @PostMapping("/exercise")
    public ModelAndView logExercise(@RequestBody MultiValueMap<String, String> form, HttpSession sess) {

        System.out.println(">>>> form: " + form.toString());
        ModelAndView mav = new ModelAndView();
        String username = (String)sess.getAttribute("username");

        Exercise ex = createIndivEx(form);
        ex = createEx(ex, form, username);        

        try {
            exSvc.insertNewExercise(username, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional<List<Exercise>> listExOpt = exSvc.getAllEx(username, form.getFirst("date"));
        System.out.println(">>>>> list present? : " + listExOpt.isPresent());

        List<Exercise> listEx = listExOpt.get();
        int totalCalPerDay = 0;
        for (Exercise exe : listEx) {
            totalCalPerDay += exe.getCalories();
        }

        mav.addObject("totalCalories", totalCalPerDay);
        mav.addObject("listOfExercises", listEx);
        mav.addObject("date", form.getFirst("date"));
        mav.addObject("username", username);
        mav.setViewName("exerciseInTheDay");

        return mav;
    }

    @GetMapping("/search/exercises")
    public ModelAndView getAllExByDate(@ModelAttribute("date") String date, HttpSession sess) {
        
        String username = (String)sess.getAttribute("username");
        // System.out.println(">>>>> date: " + date);
        ModelAndView mav = new ModelAndView();

        Optional<List<Exercise>> listExOpt = exSvc.getAllEx(username, date);
        // System.out.println(">>>>> list present?: " + listExOpt.isPresent());

        if (listExOpt.isEmpty()) {
            mav.addObject("message", List.of(1));
            // mav.addObject("totalCalories", "");
            mav.addObject("listOfExercises", List.of());
            // mav.addObject("date", date);
            // mav.addObject("username", username);
             mav.setViewName("searchExByDay");
            
            return mav;
        }

        List<Exercise> listEx = listExOpt.get();
        int totalCalPerDay = 0;
        for (Exercise exe : listEx) {
            totalCalPerDay += exe.getCalories();
        }

        mav.addObject("message", List.of(2));
        mav.addObject("totalCalories", totalCalPerDay);
        mav.addObject("listOfExercises", listEx);
        mav.addObject("date", date);
        mav.addObject("username", username);
        mav.setViewName("searchExByDay");

        return mav;
    }

}
/*
correct:
>>>> form: {title=[EMOM], date=[2022-05-17], exercise-1=[Lunges], count-1=[20], exercise-2=[Running], count-2=[2.4], exercise-3=[], count-3=[1], exercise-4=[], count-4=[1], exercise-5=[], count-5=[1], exercise-6=[], count-6=[1], exercise-7=[], count-7=[1], exercise-8=[], count-8=[1], setCount=[1], restInterval=[0], calories=[0]}

mine: 
{title=[EMOM], date=[2022-05-18], exercise-1=[Lunges], count-1=[20], exercise-2=[Running], count-2=[2.4], exercise-3=[null], count-3=[1], exercise-4=[null], count-4=[1], exercise-5=[null], count-5=[1], exercise-6=[null], count-6=[1], exercise-7=[null], count-7=[1], exercise-8=[null], count-8=[1], setCount=[1], restInterval=[0], calories=[0]}
*/
