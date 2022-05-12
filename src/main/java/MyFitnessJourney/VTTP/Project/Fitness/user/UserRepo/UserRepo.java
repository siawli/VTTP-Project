package MyFitnessJourney.VTTP.Project.Fitness.user.UserRepo;

import static MyFitnessJourney.VTTP.Project.Fitness.user.UserRepo.UserQueries.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import MyFitnessJourney.VTTP.Project.Fitness.user.UserModel;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate template;

    public SqlRowSet findUserByUsername(String username, String password) {
        return template.queryForRowSet(SQL_FIND_USER_BY_USERNAME, username, password);
    }

    public boolean insertNewUser(UserModel user) {
        int added = template.update(SQL_INSERT_NEW_USER, 
            user.getUsername(), user.getPassword(), user.getHeight(),
            user.getWeight(), user.getBmi(), user.getGoals());

        return added == 1;
    }
    
}
