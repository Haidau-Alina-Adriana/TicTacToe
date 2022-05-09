package Network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Client {

    public Client() {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter out =
                     new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {

            while (true) {
                String message = in.readLine();
                System.out.println(message);

                Scanner scanner = new Scanner(System.in);
                String request;
                request = scanner.nextLine();
                List<String> params = Arrays.asList(request.split(" "));
                out.println(request);
                out.flush();

                String response = in.readLine();
                System.out.println(response);

                if (params.get(0).equals("exit")) {
                    break;
                }
                if (response.equals("Server stopped")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

