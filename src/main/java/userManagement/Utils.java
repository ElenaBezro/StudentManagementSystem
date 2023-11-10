package userManagement;

import authManagement.LoginPasswordPair;
import roleManagement.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static void fillUserToPassword(List<LoginPasswordPair> userToPassword) {
        userToPassword.add(new LoginPasswordPair("LenaLogin", "0000"));
        userToPassword.add(new LoginPasswordPair("MilaLogin", "0000"));
        userToPassword.add(new LoginPasswordPair("NonaLogin", "0000"));
    }

    public static void fillUserWithMockData(List<User> userList, Map<String, Role> userLoginToRoleMap, Map<String, User> userLoginToUserMap) {

        userLoginToRoleMap.put("LenaLogin", Role.STUDENT);
        userLoginToRoleMap.put("MilaLogin", Role.TEACHER);
        userLoginToRoleMap.put("NonaLogin", Role.ADMIN);

        User user1 = new User("Lena", 1000);
        User user2 = new User("Mala", 1001);
        User user3 = new User("Nona", 1002);
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        userLoginToUserMap.put("LenaLogin", user1);
        userLoginToUserMap.put("MilaLogin", user2);
        userLoginToUserMap.put("NonaLogin", user3);
    }

    public static Map<String, Role> fillSecretWordToRoleMap() {
        Map<String, Role> secretWordToRoleMap = new HashMap<>();
        secretWordToRoleMap.put("student", Role.STUDENT);
        secretWordToRoleMap.put("teacher", Role.TEACHER);
        secretWordToRoleMap.put("admin", Role.ADMIN);
        return secretWordToRoleMap;
    }

    public static void fillSystemInitialState(Map<String, Boolean> systemState) {
        systemState.put("isRegistration", true);
        systemState.put("isExit", false);
        systemState.put("isLoggedIn", false);
        systemState.put("isUserMenuOpen", true);
    }

    public static int generateUserId(int userListSize) {
        return 1000 + userListSize;
    }

    public static User generateUser(String name, int usersCount) {
        int id = Utils.generateUserId(usersCount);
        return new User(name, id);
    }
}
