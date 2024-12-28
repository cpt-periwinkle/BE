import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientConnection = null;
        int port = 8001;
        try {
            if (args.length == 1) {
                port = Integer.parseInt(args[0]);
            }
            serverSocket = new ServerSocket(port);
            clientConnection = serverSocket.accept();
            handleClient(clientConnection);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientConnection != null) {
                try {
                    clientConnection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void handleClient(Socket clientConnection) throws IOException   {
        Scanner in = new Scanner(clientConnection.getInputStream());
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(clientConnection.getOutputStream())));
        while (true) {
            if (!in.hasNextLine()) {
                break;
            }
            String message = in.nextLine();
            System.out.println("Server received: " + message);
            if (message.equalsIgnoreCase("QUIT")) {
                out.println("QUIT");
                out.flush();
                break;
            }
            out.println("Okay from the server");
            out.flush();
        }
    }
}
