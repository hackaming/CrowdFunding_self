package com.crowdfunding.sjtu.Vo;

import java.io.Serializable;

public class RequestSerialVO implements Serializable{
	private String id;
	private int userid;
	private int projectid;
	private int shares;
	private float price;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}
	/**
	 * @return the projectid
	 */
	public int getProjectid() {
		return projectid;
	}
	/**
	 * @param projectid the projectid to set
	 */
	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}
	/**
	 * @return the shares
	 */
	public int getShares() {
		return shares;
	}
	/**
	 * @param shares the shares to set
	 */
	public void setShares(int shares) {
		this.shares = shares;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

}
