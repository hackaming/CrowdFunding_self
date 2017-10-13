package com.crowdfunding.sjtu.Vo;

import java.io.Serializable;

public class NodeInfomation implements Serializable{
	private String nodeName;
	private float CpuUsage;
	private int intConnection;
	/**
	 * @return the nodeName
	 */
	public String getNodeName() {
		return nodeName;
	}
	/**
	 * @param nodeName the nodeName to set
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	/**
	 * @return the cpuUsage
	 */
	public float getCpuUsage() {
		return CpuUsage;
	}
	/**
	 * @param cpuUsage the cpuUsage to set
	 */
	public void setCpuUsage(float cpuUsage) {
		CpuUsage = cpuUsage;
	}
	/**
	 * @return the intConnection
	 */
	public int getIntConnection() {
		return intConnection;
	}
	/**
	 * @param intConnection the intConnection to set
	 */
	public void setIntConnection(int intConnection) {
		this.intConnection = intConnection;
	}
}
