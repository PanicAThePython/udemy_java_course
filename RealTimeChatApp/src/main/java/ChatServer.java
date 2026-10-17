import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {

    private static final int PORT = 12345;
    private static final Set<ClientHandler> clients = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) {
        System.out.println("Chat server started...");

        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            while (true){
                Socket client = serverSocket.accept();
                System.out.println("New client: " + client.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(client);

                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e){
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public static void broadcast(String message, ClientHandler clientHandler){
        synchronized (clients){
            for (ClientHandler client : clients){
                if (client != clientHandler) client.sendMessage(message);
            }
        }
    }

    public static void removeClient(ClientHandler clientHandler){
        clients.remove(clientHandler);
    }

}
