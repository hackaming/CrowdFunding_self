package NetworkRelated;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.crowdfunding.sjtu.AQM.IHSTest;
import com.crowdfunding.sjtu.AQM.IOrderService;
import com.crowdfunding.sjtu.Vo.RequestSerialVO;
import com.crowdfunding.sjtu.model.Orders;

public class HessianClient {
	public static void main(String argv[]) throws MalformedURLException {
		new HessianClient().test2();
	}

	public void test1() throws MalformedURLException {
		HessianProxyFactory factory = new HessianProxyFactory();
		String urltest = "http://10.62.150.16:9090/Crowdfunding/remote/test"; // need
		IHSTest test = (IHSTest) factory.create(IHSTest.class, urltest);
		System.out.println(test.hello());
	}

	public void test2() throws MalformedURLException {
		HessianProxyFactory factory = new HessianProxyFactory();
		String url = "http://10.62.150.16:9090/Crowdfunding/remote/OrderService"; // need
		IOrderService os = (IOrderService) factory.create(IOrderService.class, url);
		os.getOrders();
	}

	public void test() {
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			String url = "http://10.62.150.16:9090/Crowdfunding/remote/OrderService"; // need
			IOrderService os = (IOrderService) factory.create(IOrderService.class, url);
			RequestSerialVO vo = new RequestSerialVO();
			vo.setId("1");
			vo.setPrice(0);
			vo.setProjectid(5);
			vo.setShares(5);
			vo.setUserid(1);
			System.out.println("started to call!");
			// os.saveOrderBasedOnSerial(vo); // process
			Orders o = new Orders();
			os.saveOrder(o);
			System.out.println("RPC call end, in thread!");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
