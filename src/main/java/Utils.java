import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static void fillUserWithMockData(List<String[]> userToPassword, Map<String, Role> userLoginToRoleMap) {
        userToPassword.add(new String[]{"Lena", "0000"});
        userToPassword.add(new String[]{"Mila", "0000"});
        userToPassword.add(new String[]{"Nona", "0000"});

        userLoginToRoleMap.put("Lena", Role.STUDENT);
        userLoginToRoleMap.put("Mila", Role.TEACHER);
        userLoginToRoleMap.put("Nona", Role.ADMIN);
    }
    public static Map<String, Role>  fillSecretWordToRoleMap() {
        Map<String, Role> secretWordToRoleMap = new HashMap<>();
        secretWordToRoleMap.put("student", Role.STUDENT);
        secretWordToRoleMap.put("teacher", Role.TEACHER);
        secretWordToRoleMap.put("admin", Role.ADMIN);
        return  secretWordToRoleMap;
    }
}
