package roleManagement;

import userManagement.InputService;
import userManagement.Utils;

import java.util.HashMap;
import java.util.Map;

public class RoleService {
    private Map<String, Role> userLoginToRoleMap = new HashMap<>();
    private Role currentUserRole;

    public RoleService() {
        Utils.fillLoginToRoleWithMockData(userLoginToRoleMap);
    }

    public boolean setUserRole() {
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

    public Role getRole(String login) {
        return userLoginToRoleMap.get(login);
    }

    public void removeLoginToRole(String loginToDelete) {
        userLoginToRoleMap.remove(loginToDelete);
    }

    public void setLoginToRole(String login) {
        userLoginToRoleMap.put(login, currentUserRole);
    }


}
