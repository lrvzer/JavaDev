package per.study.juc.design.chapter16;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppServer extends Thread {

    private final int port;
    private static final int DEFAULT_PORT = 12722;
    private volatile boolean start = true;
    private List<ClientHandler> clientHandlers = new ArrayList<>();
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(10);

    private ServerSocket server;
    private Socket socket;

    public AppServer() {
        this(DEFAULT_PORT);
    }

    public AppServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            while (start) {
                socket = server.accept();
                ClientHandler handler = new ClientHandler(socket);
                EXECUTOR.submit(handler);
                this.clientHandlers.add(handler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.dispose();
        }
    }

    private void dispose() {
        clientHandlers.forEach(ClientHandler::stop);
        EXECUTOR.shutdown();
    }

    public void shutdown() throws IOException {
        this.start = false;
        this.interrupt();
        socket.close();
        server.close();
    }
}
