package MyFitnessJourney.VTTP.Project.Fitness.exercise.ExerciseRepo;

public interface ExerciseQueries {

    public static final String SQL_INSERT_EXERCISE = 
        "insert into exercise (exercise_title, exercise_date, exercise_time, exercise_calories, set_count, set_rest_interval, username) values (?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_INSERT_INDIVIDUAL_EX = 
        "insert into exercise_set (step_description, step_count, exercise_time) values (?, ?, ?)";

    public static final String SQL_FIND_ALL_PER_DAY = 
        "select * from exercise as ex join exercise_set as indivEx on ex.exercise_time = indivEx.exercise_time where ex.username = ? and ex.exercise_date = ?";
    /*
    select * from exercise as ex
    join exercise_set as indivEx
    on ex.exercise_time = indivEx.exercise_time
    where ex.username = 'siawli' and ex.exercise_date = '2022-05-11'
    */ 
}
