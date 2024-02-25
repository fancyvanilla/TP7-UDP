package exercice2;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Serveur {

    public static void main(String[] args) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);
        try {
            // Get server listening port
            System.out.println("Listening port: ");
            port = keyb.nextInt();

            // Create DatagramSocket
            DatagramSocket socket = new DatagramSocket(port);

            while (true) {
                // Receive packet from client
                byte[] data = new byte[1024];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                socket.receive(packet);

                // Extract client address and port
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                try {
                    // Deserialize received object
                    ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    voiture car = (voiture) ois.readObject();

                    // Modify car object
                    car.setCarburant(100);

                    // Serialize modified object
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(car);
                    byte[] response = baos.toByteArray();

                    // Create response packet
                    DatagramPacket responsePacket = new DatagramPacket(response, response.length, clientAddress, clientPort);

                    // Send response to client
                    socket.send(responsePacket);
                    ois.close();
                    bais.close();

                } catch (IOException e) {
                    System.err.println("Error: " + e);
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}
