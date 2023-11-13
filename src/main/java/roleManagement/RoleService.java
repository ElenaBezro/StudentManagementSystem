package roleManagement;

import inputService.InputService;
import inputService.ScannerInputService;

import java.util.Map;

public class RoleService {
    private final Map<String, Role> userLoginToRoleMap;
    private Role currentUserRole;
    private final InputService inputService;

    public RoleService(Map<String, Role> userLoginToRoleMap) {
        this.userLoginToRoleMap = userLoginToRoleMap;
        this.inputService = ScannerInputService.getInstance();
    }

    public Map<String, Role> getUserLoginToRoleMap() {
        return userLoginToRoleMap;
    }

    public boolean setCurrentUserRole() {
        boolean isSet = true;
        String role = inputService.getUserInput("Enter a SECRET WORD for your role. Tip: student/teacher/admin");

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

    public void setCurrentUserRole(Role role) {
        System.out.println("User role: " + role);
        currentUserRole = role;
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
