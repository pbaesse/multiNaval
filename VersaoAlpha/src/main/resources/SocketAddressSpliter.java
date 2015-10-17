package main.resources;

import java.net.SocketAddress;

/**
 *
 * @author luann
 */
public class SocketAddressSpliter {

    public String IP;
    
    public SocketAddressSpliter(SocketAddress address) {
        String socketAddress = String.valueOf(address).replace("/", "");
        String[] datas = socketAddress.split(":");
        String IP = datas[0];
        this.IP = IP;
    }    
}
