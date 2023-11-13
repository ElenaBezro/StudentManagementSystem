package inputService;

import java.util.Scanner;

public interface InputService {
    String getUserInput(String requestMessage);
    void  close();
}
