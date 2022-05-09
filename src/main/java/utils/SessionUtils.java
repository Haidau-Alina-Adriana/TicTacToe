package utils;

import com.company.GameWithOnePlayer;
import jpa.entity.TictactoeUsersEntity;

public class SessionUtils {
    private static TictactoeUsersEntity user;

    private static GameWithOnePlayer game;

    public static TictactoeUsersEntity getUser() {
        return user;
    }

    public static void setUser(TictactoeUsersEntity user) {
        SessionUtils.user = user;
    }
}
