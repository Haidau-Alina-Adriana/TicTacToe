package utils;

import jpa.entity.TictactoeUsersEntity;

public class SessionUtils {
    private static TictactoeUsersEntity user;

    public static TictactoeUsersEntity getUser() {
        return user;
    }

    public static void setUser(TictactoeUsersEntity user) {
        SessionUtils.user = user;
    }

    public static void logout() {
        user = null;
    }

}
