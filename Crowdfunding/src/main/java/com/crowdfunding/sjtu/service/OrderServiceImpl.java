package com.crowdfunding.sjtu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowdfunding.sjtu.dao.IOrderDao;
import com.crowdfunding.sjtu.model.Orders;

@Transactional
@Service
public class OrderServiceImpl implements IOrderService{
	@Autowired
	private IOrderDao orderdao;
	
	@Override
	public void saveOrder(Orders order) {
		// TODO Auto-generated method stub
		orderdao.saveOrder(order);
	}

	@Override
	public void saveOrUpdate(Orders order) {
		// TODO Auto-generated method stub
		orderdao.saveOrUpdate(order);
	}

	@Override
	public Orders getOrderById(int orderId) {
		// TODO Auto-generated method stub
		return orderdao.getOrderById(orderId);
	}

	@Override
	public List<Orders> getOrders() {
		// TODO Auto-generated method stub
		return orderdao.getOrders();
	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		orderdao.deleteOrder(orderId);
	}

}