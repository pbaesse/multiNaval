package main.resources;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author luann
 */
public class KnockNetwork {

    private final int timeout;
    private final String subnet;
    private final ArrayList<String> servers;
    
    public KnockNetwork(String subnet, int timeout) throws UnknownHostException, IOException {
        this.timeout = timeout;
        this.subnet = subnet;
        servers = new ArrayList<>();
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    public ArrayList<String> run() throws IOException {
        servers.clear();
        
        for (int i = 1; i < 255; i++) {
            String host = subnet + "." + i;
            
            SocketAddress sockaddr = new InetSocketAddress(host, 3128);
            Socket socket = new Socket();
            boolean connected = false;
            
            try {
                socket.connect(sockaddr, timeout);
                connected = true;
            } catch (SocketTimeoutException ex) {
                System.out.println(host + " isn't reachable");
            } catch (ConnectException ex) {
                System.out.println(host + " is reachable, but hasn't server on port 3128");
            }
            
            if (connected) {
                System.out.println(host + " has a server on port 3128!!!");
                servers.add(host);
            }
            
            socket.close();
        }
        
        return servers;
    }
}
