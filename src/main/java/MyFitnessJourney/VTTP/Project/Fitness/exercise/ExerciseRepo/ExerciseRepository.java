package MyFitnessJourney.VTTP.Project.Fitness.exercise.ExerciseRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import MyFitnessJourney.VTTP.Project.Fitness.exercise.model.ExcerciseSet;
import MyFitnessJourney.VTTP.Project.Fitness.exercise.model.Exercise;


import static MyFitnessJourney.VTTP.Project.Fitness.exercise.ExerciseRepo.ExerciseQueries.*;

@Repository
public class ExerciseRepository {

    @Autowired
    private JdbcTemplate template;

    public void insertExercise(Exercise ex, String username) {

        int added = template.update(SQL_INSERT_EXERCISE,
            ex.getTitle(), ex.getDate(), ex.getTimestamp(),
            ex.getCalories(), username);
    }

    public void insertIndividualEx(Exercise ex, String username) {

        List<ExcerciseSet> indivExList = ex.getIndividualEx();
        for (ExcerciseSet indivEx : indivExList) {
            int added = template.update(SQL_INSERT_INDIVIDUAL_EX,
                indivEx.getDescription(), indivEx.getCount()
                , indivEx.getSetCount(), indivEx.getRestInterval()
                , ex.getTimestamp());
        }
    }

    public Optional<SqlRowSet> getAllExForTheDay(String username, String date) {
        
        SqlRowSet result = template.queryForRowSet(SQL_FIND_ALL_PER_DAY,
            username, date);

        if (result.next()) {
            result.beforeFirst();
            return Optional.of(result);
        }

        return Optional.empty();
    }
    
}
