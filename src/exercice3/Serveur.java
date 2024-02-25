package exercice3;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Serveur {

    public static void main(String[] args) throws IOException {

        // Create a DatagramSocket on port 5000
        DatagramSocket socket = new DatagramSocket(5000);

        while (true) {
            // Create a byte array to store the date and time
            byte[] data = new byte[1024];

            // Create a DatagramPacket to receive the data from the client
            DatagramPacket packet = new DatagramPacket(data, data.length);

            // Receive the data from the client
            socket.receive(packet);

            // Get the address and port of the client
            InetAddress address = packet.getAddress();
            int port = packet.getPort();

            // Get the current date and time
            Date date = new Date();

            // Convert the date and time to a string
            String dateTime = date.toString();

            // Convert the string to a byte array
            byte[] dateTimeBytes = dateTime.getBytes();

            // Create a DatagramPacket to send the date and time to the client
            DatagramPacket responsePacket = new DatagramPacket(dateTimeBytes, dateTimeBytes.length, address, port);

            // Send the date and time to the client
            socket.send(responsePacket);
        }
    }
}
