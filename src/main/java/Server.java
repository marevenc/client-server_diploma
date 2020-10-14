import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static ExecutorService executeIt = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        String s;
        int port = 0;
        try (BufferedReader fileReader = new BufferedReader(new FileReader("C:\\Users\\Mariya Kosheleva\\IdeaProjects\\client-server diploma\\src\\main\\java\\settings.txt"))){
            while ((s = fileReader.readLine()) != null){
                port = Integer.parseInt(s);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        try(ServerSocket server = new ServerSocket(port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Server socket created, command console reader for listen to server commands");

            while(!server.isClosed()){
                if (reader.ready()){
                    System.out.println("Main Server found any messages in channel, let's look at them.");
                    String serverCommand = reader.readLine();
                    if (serverCommand.equalsIgnoreCase("exit")){
                        System.out.println("Main Server initiate exiting...");
                        server.close();
                        break;
                    }
                }
                Socket client = server.accept();
                executeIt.execute(new ClientHandler(client));
                System.out.println("Connection accepted.");
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
