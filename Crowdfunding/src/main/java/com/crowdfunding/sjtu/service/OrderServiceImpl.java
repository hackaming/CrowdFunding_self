package com.crowdfunding.sjtu.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowdfunding.sjtu.Vo.RequestSerialVO;
import com.crowdfunding.sjtu.dao.IOrderDao;
import com.crowdfunding.sjtu.model.Orders;
import com.crowdfunding.sjtu.utility.IDateService;

@Transactional
@Service
public class OrderServiceImpl implements IOrderService{
	@Autowired
	private IOrderDao orderdao;
	@Autowired
	private IDateService dateservice;
	
	@Override
	public Serializable saveOrder(Orders order) {
		// TODO Auto-generated method stub
		return orderdao.saveOrder(order);
		
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

	@Override
	public Orders saveorupdatecopy(Orders order) {
		// TODO Auto-generated method stub
		return orderdao.saveorupdatecopy(order);
	}

	@Override
	public List<Orders> getOrdersByUserId(int userId) {
		// TODO Auto-generated method stub
		return orderdao.getOrdersByUserId(userId);
	}

	@Override
	public Serializable saveOrderBasedOnSerial(RequestSerialVO vo) {
		// TODO Auto-generated method stub
		Orders order = new Orders();
		order.setProjectId(vo.getProjectid());
		order.setShares(vo.getShares());
		order.setTotalAmount(vo.getPrice()*vo.getShares());
		order.setCreateDateTime(dateservice.getFullDate());
		order.setStatus(0);
		order.setComment("set status to 0, initial status.");
		return this.saveOrder(order);
	}

}
