import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagement {
    List<User> userList = new ArrayList<>();

    private List<String[]> userToPassword = new ArrayList<>();
    private Map<String, Role> userLoginToRoleMap = new HashMap<>();

    public UserManagement() {
        Utils.fillUserWithMockData(userToPassword, userLoginToRoleMap);
    }

    public void addUserToPassword (String[] userInputLoginData) {
        userToPassword.add(userInputLoginData);
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void deleteUser(User user) {
        userList.remove(user);
    }

    public void updateUser(User userToUpdate) {
        for (User user: userList) {
            if (user.getId() == userToUpdate.getId()) {
                user.setName(userToUpdate.getName());
            }
        }
    }

    public void displayUsers() {
        for (User user: userList) {
            System.out.println(user);
        }
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
