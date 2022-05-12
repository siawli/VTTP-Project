package MyFitnessJourney.VTTP.Project.Fitness.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import MyFitnessJourney.VTTP.Project.Fitness.user.UserRepo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Optional<UserModel> findUserByUsernameSvc(UserModel user) {
        SqlRowSet result = userRepo.findUserByUsername(user.getUsername(), user.getPassword());

        if (result.next()) {
            user.setHeight(result.getFloat("height"));
            user.setWeight(result.getFloat("weight"));
            user.setBmi(result.getFloat("bmi"));
            user.setGoals(result.getString("goals"));
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public boolean insertNewUserSvc(UserModel user) {
        return userRepo.insertNewUser(user);
    }    
    
}
