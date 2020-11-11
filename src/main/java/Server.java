import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8079);

        final String name = createAcceptOutAndIn(serverSocket, "Write your name");
        final String isChild = createAcceptOutAndIn(serverSocket, "Are you child? (yes/no)");
        if (isChild.equals("yes")) {
            createAcceptOutAndIn(serverSocket, String.format("Welcome to the kids area, %s ! Let's play!", name));
        } else {
            createAcceptOutAndIn(serverSocket,
                    String.format("Welcome to the adult zone, %s ! Have a good rest, or a good working day!", name));
        }
    }

    private static String createAcceptOutAndIn(ServerSocket serverSocket, String message) {
        String result = null;
        try (Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println(message);

            result = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}