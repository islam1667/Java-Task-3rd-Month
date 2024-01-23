package main;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author islam
 */
public class TCPListener {

    public static void main(String[] args) {
        final int PORT = 5678;
        System.out.println("Waiting for connection...");
        try (ServerSocket socket = new ServerSocket(PORT); Socket clientSocket = socket.accept(); InputStream inputStream = clientSocket.getInputStream();) {

            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\islam\\Desktop\\COPYoutput.svg");

            byte[] buffer = new byte[4096];
            int bytesRead;
            System.out.println("Reading Data...");
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("Data read successfully.");
            
            inputStream.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
