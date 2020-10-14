import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int j = 0;

        while (j < 10){
            j++;
            executorService.execute(new Client("Client" + j));
            Thread.sleep(10);
        }
        executorService.shutdown();
    }
}
