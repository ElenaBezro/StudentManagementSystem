import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static Map<String, Role>  fillSecretWordToRoleMap() {
        Map<String, Role> secretWordToRoleMap = new HashMap<>();
        secretWordToRoleMap.put("student", Role.STUDENT);
        secretWordToRoleMap.put("teacher", Role.TEACHER);
        secretWordToRoleMap.put("admin", Role.ADMIN);
        return  secretWordToRoleMap;
    }
}
