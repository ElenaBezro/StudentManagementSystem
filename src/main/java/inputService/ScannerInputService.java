package inputService;

import java.util.Scanner;

public class ScannerInputService implements InputService {
    private static ScannerInputService instance = null;
    private Scanner sc;

    private ScannerInputService() {
        sc = new Scanner(System.in);
    }

    public static ScannerInputService getInstance() {
        if (instance == null) {
            instance = new ScannerInputService();
        }
        return instance;
    }

    @Override
    public String getUserInput(String requestMessage) {
        System.out.println(requestMessage);
        return sc.nextLine();
    }

    @Override
    public void close(){
        if (sc != null) {
            sc.close();
        }
    }
}
