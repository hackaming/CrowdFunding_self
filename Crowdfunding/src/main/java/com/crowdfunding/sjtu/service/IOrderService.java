package com.crowdfunding.sjtu.service;


import java.io.Serializable;
import java.util.List;

import com.crowdfunding.sjtu.model.Orders;

public interface IOrderService {
	public Serializable saveOrder(Orders order);
	public void saveOrUpdate(Orders order);
	public Orders getOrderById(int orderId);
	public List<Orders> getOrders();
	public void deleteOrder(int orderId);
}
