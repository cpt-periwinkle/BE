import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket clientSocket = null;
        String address = "localhost";
        int port = 8001;
        try {
            if (args.length == 2) {
                address = args[0];
                port = Integer.parseInt(args[1]);
            }
            clientSocket = new Socket(address, port);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(clientSocket.getOutputStream())));
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String outMessage = "Enter a string with your keyboard: ";
                System.out.println(outMessage);

                String inMessage = scanner.nextLine();
                out.println(inMessage);
                out.flush();
                String serverResponse = in.readLine();
                if (serverResponse.equalsIgnoreCase("QUIT")) {
                    break;
                }
                System.out.println("Server: " + serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
