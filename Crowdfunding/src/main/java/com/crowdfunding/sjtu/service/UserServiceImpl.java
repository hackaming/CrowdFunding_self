package com.crowdfunding.sjtu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdfunding.sjtu.dao.IUserDao;
import com.crowdfunding.sjtu.dao.UserDaoImpl;
import com.crowdfunding.sjtu.model.User;

@Service
public class UserServiceImpl implements IUserService{
	//2017/8/5 目前是直接调用
	
	@Autowired
	public IUserDao userdao;

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userdao.saveUser(user);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userdao.deleteUser(user);
		
	}

	@Override
	public void deleteUserById(Integer userId) {
		// TODO Auto-generated method stub
		userdao.deleteUserById(userId);
	}

	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userdao.getUserById(userId);
	}

	@Override
	public User getUserByName(String userName) {
		// TODO Auto-generated method stub
		return userdao.getUserByName(userName);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userdao.getUsers();
	}

	@Override
	public boolean login(String userName, String password) {
		// TODO Auto-generated method stub
		return userdao.login(userName, password);
	}

	@Override
	public void saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		userdao.saveOrUpdate(user);
	}

}
