package com.crowdfunding.sjtu.AQM;

import com.caucho.hessian.client.HessianProxyFactory;
import com.crowdfunding.sjtu.Vo.RequestSerialVO;

public class HessianCall {
	private static String url = "http://192.168.0.106:9090/Crowdfunding/remote/OrderService"; // need
	private static String url1 = "http://192.168.0.106:9090/Crowdfunding/remote/test"; // need

	public static void main(String argv[]) throws Exception {
		RequestSerialVO vo = new RequestSerialVO();
		vo.setId("33");
		vo.setPrice(3.3f);
		vo.setProjectid(222);
		vo.setShares(5);
		vo.setUserid(55);
		HessianProxyFactory factory = new HessianProxyFactory();
		IHSTest is = (IHSTest)factory.create(IHSTest.class, url1);
		System.out.println("RPC call end, in thread!" + is.hello());
		
		IOrderService os = (IOrderService) factory.create(IOrderService.class, url);
		os.saveOrderBasedOnSerial(vo); // process
		
	}
}
