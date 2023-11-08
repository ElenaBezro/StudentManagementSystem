import java.util.*;

public class UserManagement {
    private DataPersistenceService dataPersistenceService = new DataPersistenceService();
    private List<User> userList = new ArrayList<>();
    private Map<String, User> userLoginToUserMap = new HashMap<>();

    //TODO: move role logic to a separate file
    private Map<String, Role> userLoginToRoleMap = new HashMap<>();
    public Role currentUserRole;

    public UserManagement() {
        Utils.fillUserWithMockData(userList, userLoginToRoleMap, userLoginToUserMap);
        //TODO: save in other files userLoginToUserMap, userLoginToRoleMap and restore here
        //userList = dataPersistenceService.readUserDataFromFile();
    }

    public int getUsersCount() {
        return userList.size();
    }

    public boolean setUsersRole(Scanner sc) {
        boolean isSet = true;
        System.out.println("Enter a SECRET WORD for your role. Tip: student/teacher/admin");
        String role = sc.nextLine();
        switch (role.toLowerCase()) {
            case "student" -> currentUserRole = Role.STUDENT;
            case "teacher" -> currentUserRole = Role.TEACHER;
            case "admin" -> currentUserRole = Role.ADMIN;
            default -> {
                System.out.println("Invalid input");
                isSet = false;
            }
        }
        return isSet;
    }

    public void setUserLoginToUserMap(String login, User user) {
        userLoginToUserMap.put(login, user);
    }

    public Role getRole(String login) {
        return userLoginToRoleMap.get(login);
    }

    public void setUserRole(String login) {
        userLoginToRoleMap.put(login, currentUserRole);
    }

    public void addUser(User user, String login, String password) {
        userList.add(user);
        setUserLoginToUserMap(login, user);
        setUserRole(login);
    }

    public void deleteUser(User userToDelete) {
        userList.remove(userToDelete);
        String loginToDelete = findUserLogin(userToDelete);
        userLoginToUserMap.remove(loginToDelete);
        userLoginToRoleMap.remove(loginToDelete);

    }

    public String findUserLogin(User userToFind) {
        return userLoginToUserMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getId() == userToFind.getId())
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(null);
    }

    public void updateUser(User userToUpdate) {
        for (User user : userList) {
            if (user.getId() == userToUpdate.getId()) {
                user.setName(userToUpdate.getName());
            }
        }

        String login = findUserLogin(userToUpdate);
        userLoginToUserMap.put(login, userToUpdate);
    }

    public void displayUsers() {
        for (User user : userList) {
            System.out.println(user);
        }
    }

    public void writeUserDataIntoFile() {
        dataPersistenceService.writeUserDataIntoFile(userList);
    }

    public void displayCommandsForUser(Scanner sc) {
        //TODO: display different commands for different roles
//        while (isUserMenuOpen) {
//            System.out.println("Enter command");
//            String command = sc.nextLine();

//            switch (command.toLowerCase()) {
//                case "exit" -> exit();
//                default -> System.out.println("Invalid input");
//            }
//        }
        exit(sc);
    }

    public void exit(Scanner sc) {
        dataPersistenceService.writeUserDataIntoFile(userList);
        sc.close();
    }
}
