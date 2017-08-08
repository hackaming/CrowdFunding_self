package com.crowdfunding.sjtu.service;

import java.util.List;

import com.crowdfunding.sjtu.model.Orders;

public interface IOrderService {
	public void saveOrder(Orders order);
	public void saveOrUpdate(Orders order);
	public Orders getOrderById(int orderId);
	public List<Orders> getOrders();
	public void deleteOrder(int orderId);
}
