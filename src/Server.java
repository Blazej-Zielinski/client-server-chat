import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static ArrayList<ServerThread> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4999)) {
            System.out.println("[Server] Waiting for client connection...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("[Server] Connected to client!");
                registerObserver(socket);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void registerObserver(Socket socket) throws IOException {
        ServerThread serverThread = new ServerThread(socket);
        clients.add(serverThread);

        pool.execute(serverThread);
    }

    public static void unregisterObserver(ServerThread client){
        clients.remove(client);
    }

    public static void notifyObservers(Object message) throws IOException {
        for(ServerThread client: clients){
            client.getOos().writeObject(message);
        }
    }

}
