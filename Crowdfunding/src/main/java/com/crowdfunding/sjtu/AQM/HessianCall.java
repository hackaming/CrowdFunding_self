package com.crowdfunding.sjtu.AQM;

import com.caucho.hessian.client.HessianProxyFactory;
import com.crowdfunding.sjtu.Vo.RequestSerialVO;

public class HessianCall {
	private static String url = "http://10.62.150.33:8080/Crowdfunding/remote/OrderService"; // need
	private static String url1 = "http://10.62.150.33:8080/Crowdfunding/remote/test"; // need

	public static void main(String argv[]) throws Exception {
		RequestSerialVO vo = new RequestSerialVO();
		vo.setId("33");
		vo.setPrice(3.3f);
		vo.setProjectid(222);
		vo.setShares(5);
		vo.setUserid(55);
		HessianProxyFactory factory = new HessianProxyFactory();
		IHSTest is = (IHSTest)factory.create(IHSTest.class, url1);
		
/*		IOrderService os = (IOrderService) factory.create(IOrderService.class, url);
		os.saveOrderBasedOnSerial(vo); // process
*/		System.out.println("RPC call end, in thread!" + is.hello());
	}
}
