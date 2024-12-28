import java.net.*; import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        Socket s = null;
        Scanner scanner = new Scanner(System.in);
        try {
            int port = 8001;
            String address = null;
            if (args.length == 2) {
                address = args[0];
                port = Integer.parseInt(args[1]);
            }
            s = new Socket(address, port);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String message;
            while (true) {
                System.out.print("Enter message: ");
                message = scanner.nextLine();
                // void writeUTF(String)
                out.writeUTF(message);
                if (message.toUpperCase().equals("QUIT")) break;
                // String readUTF()
                String data = in.readUTF();
                System.out.println("Received: " + data);
            }
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("client close() failed");
                }
        }
    }
}
