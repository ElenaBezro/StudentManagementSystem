package userManagement;

import dataPersistenceManagement.DataPersistenceService;
import roleManagement.Role;
import roleManagement.RoleService;

import java.util.*;

public class UserManagement {
    private DataPersistenceService dataPersistenceService;
    private RoleService roleService;
    private List<User> userList = new ArrayList<>();
    private Map<String, User> userLoginToUserMap = new HashMap<>();
    private Scanner sc;

    public UserManagement() {
        dataPersistenceService = new DataPersistenceService();
        roleService = new RoleService();
        Utils.fillUserWithMockData(userList, userLoginToUserMap);
        //TODO: save in other files userLoginToUserMap, userLoginToRoleMap and restore here
        //userList = dataPersistenceService.readUserDataFromFile();
        this.sc = InputService.getScanner();
    }

    public int getUsersCount() {
        return userList.size();
    }

    public boolean setUsersRole() {
        return roleService.setUserRole();
    }

    public void setUserLoginToUserMap(String login, User user) {
        userLoginToUserMap.put(login, user);
    }

    public Role getRole(String login) {
        return roleService.getRole(login);
    }

    public void setLoginToRole(String login) {
        roleService.setLoginToRole(login);
    }

    public void addUser(User user, String login) {
        userList.add(user);
        setUserLoginToUserMap(login, user);
        roleService.setLoginToRole(login);
    }

    public void deleteUser(User userToDelete) {
        userList.remove(userToDelete);
        String loginToDelete = findUserLogin(userToDelete);
        userLoginToUserMap.remove(loginToDelete);
        roleService.removeLoginToRole(loginToDelete);

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

    public void displayCommandsForUser() {
        boolean isUserMenuOpen = StudentManagementSystem.getInstance().isUserMenuOpen();
        //TODO: display different commands for different roles
        while (isUserMenuOpen) {
            System.out.println("Enter command: ");
            String command = sc.nextLine();

            switch (command.toLowerCase()) {
                case "exit" -> StudentManagementSystem.getInstance().exit();
                default -> System.out.println("Invalid input");
            }
        }
        exit();
    }

    public void exit() {
        dataPersistenceService.writeUserDataIntoFile(userList);
        StudentManagementSystem.getInstance().exit();
    }
}
