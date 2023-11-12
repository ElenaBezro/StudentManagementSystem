package roleManagement;

import userManagement.InputService;

import java.util.HashMap;
import java.util.Map;

public class RoleService {
    private final Map<String, Role> userLoginToRoleMap;
    private Role currentUserRole;

    public RoleService(Map<String, Role> userLoginToRoleMap) {
        this.userLoginToRoleMap = userLoginToRoleMap;
    }

    public Map<String, Role> getUserLoginToRoleMap() {
        return userLoginToRoleMap;
    }

    public boolean setCurrentUserRole() {
        boolean isSet = true;
        System.out.println("Enter a SECRET WORD for your role. Tip: student/teacher/admin");
        String role = InputService.getScanner().nextLine();
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

    public Role getCurrentRole() {
        return currentUserRole;
    }

    public Role getRole(String login) {
        return userLoginToRoleMap.get(login);
    }

    public void removeLoginToRole(String loginToDelete) {
        userLoginToRoleMap.remove(loginToDelete);
    }

    public void setLoginToRole(String login, Role role) {
        userLoginToRoleMap.put(login, role);
    }
}
