package com.crowdfunding.sjtu.dao;

import java.util.ArrayList;
import java.util.List;

import com.crowdfunding.sjtu.model.User;

public interface IUserDao {
	public void saveUser(User user);
	public void deleteUser(User user);
	public void deleteUserById(Integer userId);
	public User getUserById(Integer userId);
	public User getUserByName(String userName);
	public List<User> getUsers();
	public boolean login(String userName, String password);
	public void saveOrUpdate(User user);
}
