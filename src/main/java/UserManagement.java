import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagerment {
    //TODO: change later to List<User> userList = new ArrayList<>();
    private List<String[]> userToPassword = new ArrayList<>();
    private Map<String, Role> userLoginToRoleMap = new HashMap<>();

    public UserManagerment() {
        Utils.fillUserWithMockData(userToPassword, userLoginToRoleMap);
    }

    public void addUserToPassword (String[] userInputLoginData) {
        userToPassword.add(userInputLoginData);
    }

    public boolean findLoginPassworsPair(String login, String password) {
        boolean isUserFound = false;
        for (String[] user : userToPassword) {
            if (user[0].equals(login) && user[1].equals(password)) {
                isUserFound = true;
            }
        }
        return isUserFound;
    }


}
