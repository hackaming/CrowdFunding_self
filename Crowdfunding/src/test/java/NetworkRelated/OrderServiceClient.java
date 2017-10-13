package NetworkRelated;
import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.crowdfunding.sjtu.AQM.IOrderService;
import com.crowdfunding.sjtu.AQM.IHSTest;

public class OrderServiceClient {
	private String url = "http://localhost:8080/Crowdfunding/remote/OrderService"; //need to change according;
	private String url1 = "http://localhost:8080/Crowdfunding/us"; //need to change according;
	
	public OrderServiceClient (String strNodeName){
		this.url = url.replace("localhost", strNodeName);
		
	}
	public void calltest(){
	       HessianProxyFactory factory = new HessianProxyFactory();
	       IHSTest os;
		try {
			System.out.println("Begin to call!");
			os = (IHSTest)factory.create(IHSTest.class, url);
	       System.out.println(os.hello()); // process
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void call(){
		
	       HessianProxyFactory factory = new HessianProxyFactory();
	       IOrderService os;
		try {
			os = (IOrderService)factory.create(IOrderService.class, url1);
	        //os.saveOrder(n); // process
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url1;
	}

}
