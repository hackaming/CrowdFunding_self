package NetworkRelated;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
	public static void main(String argv[]) throws UnknownHostException{
		InetAddress inet1 = InetAddress.getByName("www.163.com");
		System.out.println(inet1);
		
		InetAddress inet2 = InetAddress.getByName("127.0.0.1");
		System.out.println(inet2);
		
		InetAddress inet3 = InetAddress.getLocalHost();
		System.out.println(inet3);
		
		String hostname = inet3.getHostName();
		String ip = inet3.getHostAddress();
		System.out.println(hostname);
		System.out.println(ip);
	}
}
