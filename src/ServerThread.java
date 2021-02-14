import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private final Socket socket;
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;

    public ServerThread(Socket socket) throws IOException {
        this.socket = socket;
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    public void run(){
        try
        {
            while(true) {
                Object clientMessage = ois.readObject();

                if(clientMessage.equals("quit")){
                    oos.writeObject(clientMessage);
                    Server.unregisterObserver(this);
                    break;
                }else{
                    Server.notifyObservers(clientMessage);
                }
            }
        }
        catch ( IOException |ClassNotFoundException e)
        {
            e.printStackTrace();
        }
         finally
        {
            try {
                oos.close();
                ois.close();
                System.out.println("[Serwer] Client has closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public ObjectOutputStream getOos() {
        return oos;
    }
}
