public class MainTest {

    public static void main(String[] args){

        MultithreadedWebServer multithreadedWebServer = new MultithreadedWebServer(8080);
        Thread serverThread = new Thread(multithreadedWebServer::startServer);
        serverThread.start();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
