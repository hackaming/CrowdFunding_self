package com.crowdfunding.sjtu.dao;

import java.util.List;

import com.crowdfunding.sjtu.model.Orders;

public interface IOrderDao {
	public void saveOrder(Orders order);
	public void saveOrUpdate(Orders order);
	public Orders getOrderById(int orderId);
	public List<Orders> getOrders();
	public void deleteOrder(int orderId);
}
