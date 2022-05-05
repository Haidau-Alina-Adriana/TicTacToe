package utils;

import com.company.user.UserRepository;
import jpa.entity.TictactoeUsersEntity;

import java.util.Optional;

public class CreateInstanceUtils {
    private static long index = 1;

    public static String createAccount(String username, String email, String password) {
        UserRepository newUserRepo = new UserRepository();
        TictactoeUsersEntity newUser = new TictactoeUsersEntity();
        if (UserRepository.findByEmail(email) != 0 || UserRepository.findByUsername(username) != 0) {
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
}
