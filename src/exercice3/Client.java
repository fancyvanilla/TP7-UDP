package exercice3;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);
        try {
            // Get the server address and port
            System.out.println("Server address: ");
            host = keyb.next();
            System.out.println("Server port: ");
            port = keyb.nextInt();

            // Create an InetAddress object for the server
            InetAddress address = InetAddress.getByName(host);

            // Create a DatagramPacket to send data to the server
            byte[] data = (new String("Hello Server!")).getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);

            // Create a DatagramSocket to send and receive datagrams
            DatagramSocket socket = new DatagramSocket();

            // Send the packet to the server
            socket.send(packet);

            // Create a byte array to receive data from the server
            byte[] responseBytes = new byte[100];

            // Create a new DatagramPacket to receive the response
            packet = new DatagramPacket(responseBytes, responseBytes.length);

            // Receive the response from the server
            socket.receive(packet);

            // Create a new String object from the received bytes
            String response = new String(packet.getData(), 0, packet.getLength());

            // Print the response
            System.out.println("Received from server: " + response);

            // Close the socket
            socket.close();

        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}
