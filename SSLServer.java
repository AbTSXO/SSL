/**
 *
 * @author Abenezer Ashenfi
 */
import java.io.*;
import java.net.*;
import javax.net.ssl.*;

public class SSLServer {
    public static void main(String[] args) throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        KeyStore ks = KeyStore.getInstance("JKS");
        char[] password = "password".toCharArray();
        ks.load(new FileInputStream("C:/Users/EBENZ/Documents/NetBeansProjects/FinalSSL/src/keystore.jks"), password);
        kmf.init(ks, password);
        sslContext.init(kmf.getKeyManagers(), null, null);

        SSLServerSocketFactory sslssf = sslContext.getServerSocketFactory();
        SSLServerSocket serverSocket = (SSLServerSocket) sslssf.createServerSocket(9999);
        System.out.println("Server started on port 9999");

        while (true) {
            SSLSocket socket = (SSLSocket) serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received message: " + inputLine);
                out.println("Server received message: " + inputLine);
            }

            in.close();
            out.close();
            socket.close();
        }
    }
}