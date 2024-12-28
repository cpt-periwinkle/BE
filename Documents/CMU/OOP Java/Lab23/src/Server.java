//TCP server makes a connection for each client and then echoes the client’s request
import java.net.*; import java.io.*;
public class Server {
    public static void main (String args[]) {
        try {
            int port = 8001;
            if (args.length == 1) {
                port = Integer.parseInt(args[0]);
            }
            ServerSocket listenSocket = new ServerSocket(port);
            while(true) {
                Socket clientSocket = listenSocket.accept();
                ServerWorker c = new ServerWorker(clientSocket);
            }
        } catch(IOException e) {
            System.out.println("Listen :"+e.getMessage());
        }
    }
}