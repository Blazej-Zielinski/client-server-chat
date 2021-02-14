import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket server;
    private final ObjectInputStream ois;
    private final ClientView clientView;
    private final IHandler baseHandler;
    private final IHandler fileHandler;

    public ClientThread(Socket server,ClientView clientView) throws IOException {
        this.server = server;
        this.clientView = clientView;
        ois = new ObjectInputStream(server.getInputStream());

        this.baseHandler = new BaseHandler();
        this.fileHandler = new FileHandler();
        baseHandler.setNext(fileHandler);
        fileHandler.setNext(null);
    }

    public void run() {
        try {
            while (true) {
                Object serverResponse = ois.readObject();
                if(serverResponse.equals("quit")) break;

                baseHandler.handle(serverResponse,clientView);

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
