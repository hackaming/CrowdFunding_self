package com.crowdfunding.sjtu.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
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
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
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
		Serializable i = this.saveOrder(order);
		//add code here to put the data into the redis 2017/10/14
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		HashMap<String,Object> map = new HashMap();
		Orders o = new Orders();
		o.setComment("dd");
		o.setCreateDateTime("fff");
		o.setOrderId(22);
		o.setProjectId(11);
		o.setShares(2);
		o.setStatus(1);
		o.setTotalAmount(5f);
		o.setUserId(3);
		
		map.put("orderId",o.getOrderId());
		map.put("createDateTime",o.getCreateDateTime());
		map.put("projectId",o.getProjectId());
		map.put("userId",o.getUserId());
		map.put("totalAmount",o.getTotalAmount());
		map.put("status",o.getStatus());
		map.put("shares",o.getShares());
		map.put("comment",o.getComment());
		hash.putAll(vo.getId(), map);
		
		return i;
	}

}
