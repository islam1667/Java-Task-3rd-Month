package main;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author islam
 */
public class PracticalTask3rdMonth {

    public static void main(String[] args) {

        String name = getLine("Enter your name & surname: ");
        String path = getLine("Enter desired file location: ");
        String destination = getLine("Enter port number(as 'localhost:xxxx'): ");
        sendData(destination, path, name);
    }

    public static void sendData(String destination, String dataPath, String name) {
        String ip = destination.split(":")[0];
        Integer port = Integer.valueOf(destination.split(":")[1]);

        try (Socket socket = new Socket(ip, port); OutputStream outputStream = socket.getOutputStream()) {
            FileInputStream fileInputStream = new FileInputStream(dataPath);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("Data sent successfully from " + name + " to " + destination + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getLine(String message) {
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
