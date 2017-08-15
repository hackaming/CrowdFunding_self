package com.crowdfunding.sjtu.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crowdfunding.sjtu.model.Orders;

@Repository
public class OrderDaoImpl implements IOrderDao {
	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public Serializable saveOrder(Orders order) {
		// TODO Auto-generated method stub
		/*
		 * int pk =(Integer) sessionfactory.getCurrentSession().save(order);
		 * System.out.println("the pk value's ID returned??:" + pk); return pk;
		 */
		return sessionfactory.getCurrentSession().save(order);
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
		if (orders.size() >= 1) {
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

	@SuppressWarnings("deprecation")
	public Orders saveorupdatecopy(Orders order) {
		// TODO Auto-generated method stub
		return order;
	}

	@Override
	public List<Orders> getOrdersByUserId(int userId) {
		Query query = sessionfactory.getCurrentSession().createQuery("from Orders o where o.userId=:userId");
		query.setInteger("userId", userId);
		return query.list();
	}

}
