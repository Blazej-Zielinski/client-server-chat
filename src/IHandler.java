import java.io.IOException;

public interface IHandler {
    void setNext(IHandler handler);
    void handle(Object request, ClientView clientView) throws IOException;
}
