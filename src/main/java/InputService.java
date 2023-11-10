import java.util.Scanner;

public class InputService {
    private static Scanner instance = null;

    private InputService() {
    }

    public static Scanner getScanner() {
        if (instance == null) {
            instance = new Scanner(System.in);
        }
        return instance;
    }
}
