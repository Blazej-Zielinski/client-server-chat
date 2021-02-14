import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileHandler implements IHandler {
    private IHandler next;

    @Override
    public void setNext(IHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(Object request, ClientView clientView) throws IOException {
        if(request instanceof File){
            File file = (File) request;
            Desktop d = Desktop.getDesktop();
            d.open(file);
            clientView.getChat().append("\n" + "[Wysłano plik] " + file.getName());
        }
    }
}
