import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    private static final String HOST = "netology.homework";
    private static final int PORT = 8079;

    public static void main(String[] args) {
        while (true) {
            try (Socket clientSocket = new Socket(HOST, PORT);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                String response = in.readLine();
                if (response.equals("Write your name")) {
                    out.println("Ivan");
                } else if (response.equals("Are you child? (yes/no)")) {
                    out.println("yes");
                } else {
                    System.out.println(response);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
