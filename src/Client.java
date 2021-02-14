import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",4999);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        try
        {
            ClientView clientView = new ClientView();

            clientView.getSendButton().addActionListener(e -> {
                try {
                    String msOut = clientView.getSendBox().getText().trim();

                    oos.writeObject(msOut);
                    clientView.getSendBox().setText("");

                    if( msOut.equals("quit")) {
                        socket.close();
                        System.exit(0);
                    }

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

            clientView.getUploadButton().addActionListener(e ->{

                JFileChooser jFileChooser = new JFileChooser(".");
                FileNameExtensionFilter bitMapFilter = new FileNameExtensionFilter("Images", "bmp");
                FileNameExtensionFilter wavFilter = new FileNameExtensionFilter("Audio", "wav");

                jFileChooser.setFileFilter(bitMapFilter);
                jFileChooser.setFileFilter(wavFilter);

                jFileChooser.setDialogTitle("Choose a file");

                if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    try {
                        oos.writeObject(file);

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });

            ClientThread clientThread = new ClientThread(socket,clientView);
            clientThread.start();

        }
        catch (UnknownHostException ex)
        {
            System.out.println("Serwer nieosiągalny: " + ex.getMessage());
        }
        catch (IOException ex)
        {
            System.out.println("Błąd we/wy: " + ex.getMessage());
        }

    }
}
