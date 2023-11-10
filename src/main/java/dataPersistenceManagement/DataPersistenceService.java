package dataPersistenceManagement;

import java.io.*;
import userManagement.User;
import java.util.ArrayList;
import java.util.List;

public class DataPersistenceService {
    private static final String USER_DATA_FILE = "users.txt";
    private static final String SEPARATOR = ", ";

    public void writeUserDataIntoFile(List<User> userList) {
        try (FileWriter fileWriter = new FileWriter(USER_DATA_FILE);
             BufferedWriter writer = new BufferedWriter(fileWriter)) {
            for (User user : userList) {
                writer.write(user.getName() + SEPARATOR + user.getId());
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<User> readUserDataFromFile() {
        List<User> users  = new ArrayList<>();

        try (FileReader fr = new FileReader(USER_DATA_FILE);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine())!= null) {
                String name = line.split(SEPARATOR)[0];
                int id = Integer.parseInt(line.split(SEPARATOR)[1]);
                User user = new User(name, id);
                users.add(user);
            }
            return users;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
