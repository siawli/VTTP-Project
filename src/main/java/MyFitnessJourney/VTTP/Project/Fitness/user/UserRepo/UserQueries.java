package MyFitnessJourney.VTTP.Project.Fitness.user.UserRepo;

public interface UserQueries {

    public static final String SQL_INSERT_NEW_USER = 
        "insert into user (username, password, height, weight, bmi, goals) values (?, sha1(?), ?, ?, ?, ?)";
    // insert into user (username, password, height, weight, bmi, goals) values ('siawli', sha1('siaw'), 1.70, 60, 23, 'be fitter');

    public static final String SQL_FIND_USER_BY_USERNAME = 
        "select * from user where username = ? and password = sha(?)";
    
}
