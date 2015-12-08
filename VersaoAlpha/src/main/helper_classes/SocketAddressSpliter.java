package main.helper_classes;

import java.net.SocketAddress;

/**
 * @author luann
 * Class that converts an SocketAddress to an host address (IP)
 */
public class SocketAddressSpliter {

    public String IP;
    
    public SocketAddressSpliter(SocketAddress address) {
        String socketAddress = String.valueOf(address).replace("/", "");
        String[] datas = socketAddress.split(":");
        this.IP = datas[0];
    }    
}