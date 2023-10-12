/**
 *
 * @author Abenezer Ashenafi
 */
import java.io.*;
import java.net.*;
import javax.net.ssl.*;

public class SSLClient {
    public static void main(String[] args) throws Exception {
        SSLSocketFactory sslsf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) sslsf.createSocket("localhost", 9999);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println("Hello from client");

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received message: " + inputLine);
        }

        in.close();
        out.close();
        socket.close();
    }