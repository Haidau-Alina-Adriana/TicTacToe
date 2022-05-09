package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientThread extends Thread {
    private final Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            System.out.println("Client connected!");
            while (true) {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                String message = "Please choose a command from the following: -register name -login name -friend name1 name2 ... namek -send message -read -exit -stop Command: ";
                out.println(message);
                out.flush();


                String request = in.readLine();
                StringBuilder response = new StringBuilder();

                if (request.contains("exit")) {
                    response.append("Goodbye");
                } else if (request.contains("stop")) {
                    response.append("Server stopped");
                } else if (!request.contains("exit") && !request.contains("stop") &&
                        !request.contains("register") && !request.contains("login") &&
                        !request.contains("friends") && !request.contains("send") && !request.contains("read")) {
                    response.append("Unrecognized command.");
                } else {
                    response.append("Server received the request: ");
                    response.append(request);
                    response.append(".");
                }
                out.println(response.toString());
                out.flush();

                if (request.equals("exit")) {
                    break;
                } else if (request.equals("stop")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
