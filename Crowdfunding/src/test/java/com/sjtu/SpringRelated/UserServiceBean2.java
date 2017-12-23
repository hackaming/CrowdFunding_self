package com.sjtu.SpringRelated;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserServiceBean2 {
	private String userId;
	private String userName;
	@Resource(name="userDaoImpl")
	private UserDao userDao1;
	private UserDao userDao2;
	@Autowired(required=false)
	@Qualifier("userDaoImpl")
	private UserDao userDao3;
	
	@Autowired(required=false)
	@Qualifier("userDaoImpl3")
	private UserDao userDao4;
	
	
	
	
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userDao1
	 */
	public UserDao getUserDao1() {
		return userDao1;
	}
	/**
	 * @param userDao1 the userDao1 to set
	 */
	public void setUserDao1(UserDao userDao1) {
		this.userDao1 = userDao1;
	}
	/**
	 * @return the userDao2
	 */
	public UserDao getUserDao2() {
		return userDao2;
	}
	/**
	 * @param userDao2 the userDao2 to set
	 */
	@Resource
	public void setUserDao2(UserDao userDao2) {
		this.userDao2 = userDao2;
	}
	/**
	 * @return the userDao3
	 */
	public UserDao getUserDao3() {
		return userDao3;
	}
	/**
	 * @param userDao3 the userDao3 to set
	 */
	public void setUserDao3(UserDao userDao3) {
		this.userDao3 = userDao3;
	}
	
	public void test(){
		userDao1.showUser();
		userDao2.showUser();
		System.out.println(userDao3.getClass().getName());
		userDao3.showUser();
	}

}
