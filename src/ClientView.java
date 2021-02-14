import javax.swing.*;
import java.awt.*;

public class ClientView extends JFrame {
    private final JButton uploadButton = new JButton("Upload");
    private final JButton sendButton = new JButton("Send");
    private final JTextField sendBox = new JTextField(25);
    private final JTextArea chat = new JTextArea(20, 40);

    public ClientView() {

        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JScrollPane scrollPane = new JScrollPane(chat);
        chat.setEditable(false);


        bottomPanel.add(sendBox);
        bottomPanel.add(sendButton);
        bottomPanel.add(uploadButton);

        mainContainer.add(scrollPane);
        mainContainer.add(bottomPanel);


        this.add(mainContainer);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Client");
        this.pack();
    }


    public JTextArea getChat() {
        return chat;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JButton getUploadButton() {
        return uploadButton;
    }

    public JTextField getSendBox() {
        return sendBox;
    }
}
