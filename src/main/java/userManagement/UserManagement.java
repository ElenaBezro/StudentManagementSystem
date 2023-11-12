package userManagement;

import roleManagement.Role;
import roleManagement.RoleService;

import java.util.*;

public class UserManagement {
    private RoleService roleService;
    private List<User> userList;
    private Map<String, User> userLoginToUserMap;
    private Scanner sc;

    public UserManagement(RoleService roleService, List<User> userList, Map<String, User> userLoginToUserMap) {
        this.roleService = roleService;
        this.userLoginToUserMap = userLoginToUserMap;
        this.userList = userList;
        this.sc = InputService.getScanner();
    }

    public List<User> getUserList() {
        return userList;
    }

    public Map<String, User> getUserLoginToUserMap() {
        return userLoginToUserMap;
    }

    public int getUsersCount() {
        return userList.size();
    }

    public boolean setCurrentUsersRole() {
        return roleService.setCurrentUserRole();
    }

    public void setUserLoginToUserMap(String login, User user) {
        userLoginToUserMap.put(login, user);
    }

    public Role getRole(String login) {
        return roleService.getRole(login);
    }

    public Role getCurrentRole() {
        return roleService.getCurrentRole();
    }

    public void setLoginToRole(String login, Role role) {
        roleService.setLoginToRole(login, role);
    }

    public void addUser(User user, String login, Role role) {
        userList.add(user);
        setUserLoginToUserMap(login, user);
        roleService.setLoginToRole(login, role);
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

    public void displayCommandsForUser() {
//        switch (roleService.getCurrentRole()) {
//            case ADMIN -> displayAdminOptions();
//            case STUDENT -> displayStudentOptions();
//            case TEACHER -> displayTeacherOptions();
//        }
//
//        handleUserCommand();
//
//        //boolean isUserMenuOpen = StudentManagementSystem.getInstance().isUserMenuOpen();
//        while (isUserMenuOpen) {
//            System.out.println("Enter command: ");
//            String command = sc.nextLine();
//
//            switch (command.toLowerCase()) {
//                case "exit" -> StudentManagementSystem.getInstance().exit();
//                default -> System.out.println("Invalid input");
//            }
//        }
        StudentManagementSystem.getInstance().exit();
    }
}
