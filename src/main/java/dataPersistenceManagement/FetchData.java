package dataPersistenceManagement;

import authManagement.LoginPasswordPair;
import roleManagement.Role;
import userManagement.User;

import java.util.List;
import java.util.Map;

public interface FetchData {
    List<User> getUserList();
    Map<String, User> getLoginToUser();
    List<LoginPasswordPair> getLoginToPassword();
    Map<String, Role> getLoginToRole();
}
