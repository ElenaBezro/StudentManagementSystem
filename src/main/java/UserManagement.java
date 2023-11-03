import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManagement {
    List<User> userList = new ArrayList<>();

    private List<String[]> userToPassword = new ArrayList<>();
    private Map<String, Role> userLoginToRoleMap = new HashMap<>();
    private static final String USER_DATA_FILE = "users.txt";
    private static final String SEPARATOR = ", ";

    public UserManagement() {
        Utils.fillUserWithMockData(userToPassword, userLoginToRoleMap);
        //readUserDataFromFile();
    }

    public void addUserToPassword(String[] userInputLoginData) {
        userToPassword.add(userInputLoginData);
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void deleteUser(User user) {
        userList.remove(user);
    }

    public void updateUser(User userToUpdate) {
        for (User user : userList) {
            if (user.getId() == userToUpdate.getId()) {
                user.setName(userToUpdate.getName());
            }
        }
    }

    public void displayUsers() {
        for (User user : userList) {
            System.out.println(user);
        }
    }

    public boolean findLoginPasswordsPair(String login, String password) {
        boolean isUserFound = false;
        for (String[] user : userToPassword) {
            if (user[0].equals(login) && user[1].equals(password)) {
                isUserFound = true;
            }
        }
        return isUserFound;
    }

    public void writeUserDataIntoFile() {
        try (FileWriter fileWriter = new FileWriter(USER_DATA_FILE);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (User user : userList) {
                writer.write(user.getName() + SEPARATOR + user.getId());
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<User> readUserDataFromFile() {
        List<User> users  = new ArrayList<>();

        try (FileReader fr = new FileReader(USER_DATA_FILE);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine())!= null) {
                String name = line.split(SEPARATOR)[0];
                int id = Integer.parseInt(line.split(SEPARATOR)[1]);
                User user = new User(name, id);
                users.add(user);
            }
            return users;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
