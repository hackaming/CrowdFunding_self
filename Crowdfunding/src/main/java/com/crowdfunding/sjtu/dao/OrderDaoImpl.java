package com.crowdfunding.sjtu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crowdfunding.sjtu.model.Orders;
@Repository
public class OrderDaoImpl implements IOrderDao{
	@Autowired
	private SessionFactory sessionfactory;
	
	
	@Override
	public void saveOrder(Orders order) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().save(order);
	}

	@Override
	public void saveOrUpdate(Orders order) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().saveOrUpdate(order);
	}

	@Override
	public Orders getOrderById(int orderId) {
		// TODO Auto-generated method stub
		Query query = sessionfactory.getCurrentSession().createQuery("from Orders o where o.orderId=:orderId");
		query.setInteger("orderId", orderId);
		List<Orders> orders = query.list();
		if (orders.size()>=1){
			return orders.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Orders> getOrders() {
		// TODO Auto-generated method stub
		return sessionfactory.getCurrentSession().createQuery("from Orders").list();
	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().delete(orderId);
	}

}
