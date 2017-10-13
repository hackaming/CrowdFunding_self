package NetworkRelated;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.crowdfunding.sjtu.AQM.IHSTest;
import com.crowdfunding.sjtu.AQM.IOrderService;

public class Test {
	public void test1() {
/*		HessianProxyFactory factory = new HessianProxyFactory();
		IOrderService os;
		try {
			os = (IOrderService) factory.create(IOrderService.class,
					"http://shg076cr33723hh:8080/Crowdfunding/remote/OrderService");
			//os.saveOrder(null); // process
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}*/

	}

	public static void main(String argv[]) {
		new Test().test1();
	}


	public void test() {
/*		String url = "http://localhost:8080/Crowdfunding/us";
		HessianProxyFactory factory = new HessianProxyFactory();
		IHSTest hello;
		try {
			hello = (IHSTest) factory.create(IHSTest.class, url);
			String h = hello.hello();
			System.out.println(h);
			// System.out.println(hello.hello());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
/*
	public void testBasicAPI() {
		String url = "http://localhost:8080/Crowdfunding/remote/test";
		HessianProxyFactory factory = new HessianProxyFactory();
		IHSTest hello;
		try {
			hello = (IHSTest) factory.create(IHSTest.class, url);
			String h = hello.hello();
			System.out.println(h);
			// System.out.println(hello.hello());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
