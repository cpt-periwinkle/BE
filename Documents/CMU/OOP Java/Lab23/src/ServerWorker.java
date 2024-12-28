import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerWorker extends Thread{
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Socket socket;
    Scanner scanner;
    static int clientCounter = 0;

    ServerWorker (Socket socket)   {
        this.socket = socket;
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.scanner = new Scanner(System.in);
        this.start();
    }

    public void run() {
        clientCounter++;
        String message;
        String reply;
        System.out.println("Starting new connection for " + clientCounter);

        try {
            while (true) {
                try {
                    message = dataInputStream.readUTF();
                    if (message.equals("QUIT")) {
                        break;
                    }
                    System.out.println(clientCounter + ") Server received: " + message);
                    System.out.println(clientCounter + ") Enter a reply: ");
                    reply = scanner.nextLine();
                    dataOutputStream.writeUTF(reply);
                } catch (EOFException e) {
                    System.out.println("End of input stream reached");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("IO error occurred: " + e.getMessage());
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing socket: " + e.getMessage());
            }
        }
    }


}
