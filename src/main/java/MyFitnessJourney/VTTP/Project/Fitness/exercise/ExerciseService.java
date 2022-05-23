package MyFitnessJourney.VTTP.Project.Fitness.exercise;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import MyFitnessJourney.VTTP.Project.Fitness.exercise.ExerciseRepo.ExerciseRepository;
import MyFitnessJourney.VTTP.Project.Fitness.exercise.model.ExcerciseSet;
import MyFitnessJourney.VTTP.Project.Fitness.exercise.model.Exercise;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exRepo;

    @Transactional(rollbackFor = Exception.class)
    public void insertNewExercise(String username, Exercise ex) {

        try {
            exRepo.insertExercise(ex, username);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        try {
            exRepo.insertIndividualEx(ex, username);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Optional<List<Exercise>> getAllEx(String username, String date) {

        String timestamp = null;
        List<Exercise> listEx = new LinkedList<>();

        Optional<SqlRowSet> exercisesOpt = exRepo.getAllExForTheDay(username, date);

        if (exercisesOpt.isPresent()) {
            SqlRowSet result = exercisesOpt.get();

            while (result.next()) {
                // System.out.println(">>>> result.next() 1 == true; " + result.getString("exercise_time"));
                timestamp = result.getString("exercise_time");

                Exercise ex = new Exercise();
                ex.setCalories(result.getInt("exercise_calories"));
                ex.setDate(result.getString("exercise_date"));
                ex.setTimestamp(timestamp);
                ex.setTitle(result.getString("exercise_title"));
                ex.setRestInterval(result.getString("set_rest_interval"));
                ex.setSetCount(result.getInt("set_count"));

                
                int count = 1;
                while (timestamp.equals(result.getString("exercise_time"))) {
                    // System.out.println("count: " + count);
                    ExcerciseSet exSet = new ExcerciseSet();
                    exSet.setCount(result.getString("step_count"));
                    exSet.setDescription(result.getString("step_description"));
                    exSet.setRestInterval(result.getFloat("set_rest_interval"));
                    exSet.setTimestamp(timestamp);

                    ex.getIndividualEx().add(exSet);
                    
                    count++;
                    if (!result.next()) {
                        break;
                    }
                }

                listEx.add(ex);
                result.previous();
                
            }

            return Optional.of(listEx);
        }

        return Optional.empty();
    }
    
}
