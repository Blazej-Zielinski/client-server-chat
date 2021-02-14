import java.io.IOException;

public class BaseHandler implements IHandler {
    private IHandler next;


    @Override
    public void setNext(IHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(Object request, ClientView clientView) throws IOException {
        if(request instanceof String){
            String message = (String) request;
            clientView.getChat().append("\n" + "[Wiadomość] " + message.trim());
        }else{
            next.handle(request,clientView);
        }
    }
}
