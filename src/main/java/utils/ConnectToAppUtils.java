package utils;

import com.company.user.UserRepository;
import jpa.entity.TictactoeUsersEntity;

import java.util.List;
import java.util.Optional;

public class ConnectToAppUtils {
    private static long index = 1;

    public static String createAccount(String username, String email, String password) {
        UserRepository newUserRepo = new UserRepository();
        TictactoeUsersEntity newUser = new TictactoeUsersEntity();
        if (!newUserRepo.findByEmail(email).isEmpty() || newUserRepo.findByUsername(username) != 0) {
            return "User already exists!";
        }
        newUser.setId(index);
        index++;
        newUser.setUsername(username);
        newUser.setEmail(email);
        Optional<String> salt = PasswordUtils.generateSalt(50);
        newUser.setPassword(PasswordUtils.generateHashedPassword(password, salt.toString()).toString());
        newUser.setSalt(salt.toString());
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
        return "Succes!";
    }
}
