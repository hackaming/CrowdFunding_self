package com.sjtu.SpringRelated;

import java.util.List;
import java.util.Map;

public class UserServiceBean {
	private int userId;
	private String userName;
	private UserDao userDao;
	private List<String> hobbies;
	private Map<String,Integer> scores;
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
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
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}
	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/**
	 * @return the hobbies
	 */
	public List<String> getHobbies() {
		return hobbies;
	}
	/**
	 * @param hobbies the hobbies to set
	 */
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	/**
	 * @return the scores
	 */
	public Map<String, Integer> getScores() {
		return scores;
	}
	/**
	 * @param scores the scores to set
	 */
	public void setScores(Map<String, Integer> scores) {
		this.scores = scores;
	}
}
