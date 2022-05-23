package utils;

import com.company.user.UserRepository;
import jpa.entity.TictactoeUsersEntity;
import java.util.List;
import java.util.Optional;

public class ConnectToAppUtils {

    public static String createAccount(String username, String email, String password) {
        UserRepository newUserRepo = new UserRepository();
        TictactoeUsersEntity newUser = new TictactoeUsersEntity();
        if (!newUserRepo.findByEmail(email).isEmpty() || newUserRepo.findByUsername(username) != 0) {
            return "User already exists!";
        }
        newUser.setId(UserRepository.getNumberOfUsersFromDatabase() + 1);
        newUser.setUsername(username);
        newUser.setEmail(email);
        String salt = PasswordUtils.generateSalt();
        newUser.setPassword(PasswordUtils.generateHashedPassword(password, salt));
        newUser.setScore(0L);
        newUser.setSalt(salt);
        newUserRepo.create(newUser);
        return "Account created!";
    }

    public static String loginIntoAccount(String email, String password) {
        UserRepository userRepo = new UserRepository();
        List<TictactoeUsersEntity> user = userRepo.findByEmail(email);
        if (userRepo.findByEmail(email).isEmpty()) {
            return "User not found!";
        }
        if (!PasswordUtils.checkPassword(password, user.get(0).getPassword(), user.get(0).getSalt())) {
            return "Wrong password";
        }
        SessionUtils.setUser(user.get(0));
        return "Succes!";
    }
}
