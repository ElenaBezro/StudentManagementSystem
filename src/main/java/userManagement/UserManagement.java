package userManagement;

import roleManagement.Role;
import roleManagement.RoleService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserManagement {
    private final RoleService roleService;
    private final List<User> userList;
    private final Map<String, User> userLoginToUserMap;
    private final Scanner sc;

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

    public boolean setCurrentUserRole() {
        return roleService.setCurrentUserRole();
    }

    public void setCurrentUserRole(Role role) {
        roleService.setCurrentUserRole(role);
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

    public void updateUser() {
        System.out.println("Enter user id: ");
        int id = Integer.parseInt(sc.nextLine());
        User userToUpdate;
        for (User user : userList) {
            if (user.getId() == id) {
                System.out.println("Enter new user name: ");
                String name = sc.nextLine();
                userToUpdate = new User(name, id);
                updateUser(userToUpdate);
                return;
            }
        }
        System.out.println("Invalid id");
    }

    public void displayUsers() {
        for (User user : userList) {
            System.out.println(user);
        }
    }

    public void displayCommandsForUser() {
        switch (roleService.getCurrentRole()) {
            case ADMIN -> displayAdminOptions();
            //case STUDENT -> displayStudentOptions();
            //case TEACHER -> displayTeacherOptions();
        }
    }

    public void displayAdminOptions() {
        while (StudentManagementSystem.getInstance().isUserMenuOpen()) {
            System.out.println("Enter 'display' to display all users");
            System.out.println("Enter 'add' to add user");
            System.out.println("Enter 'update' to update user");
            System.out.println("Enter 'delete' to delete user");
            System.out.println("Enter 'exit' to exit system");
            String command = sc.nextLine();
            handleUserCommand(command);
        }
    }

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
    public void handleUserCommand(String command) {
        if (roleService.getCurrentRole().equals(Role.ADMIN)) {
            switch (command.toLowerCase()) {
                case "display" -> displayUsers();
                //case "add" -> addUser();
                case "update" -> updateUser();
                //case "delete" -> deleteUser();
                case "exit" -> StudentManagementSystem.getInstance().exit();
                default -> System.out.println("Invalid input");
            }
        }
    }


}
