package dataPersistenceManagement;

import authManagement.LoginPasswordPair;
import roleManagement.Role;
import userManagement.User;

import java.util.List;
import java.util.Map;

public interface ExportData {
    void exportUserList(List<User> userList);
    void exportLoginToUser(Map<String, User> userLoginToUserMap);
    void exportLoginToPassword(List<LoginPasswordPair> loginToPassword);
    void exportLoginToRole(Map<String, Role> userLoginToRoleMap);
}
