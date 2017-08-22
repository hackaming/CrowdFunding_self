package com.crowdfunding.sjtu.model;


public class Mail {
	private String hostName;
	private String hostPort;
	private boolean sslOnConnect;
	private String from;
	private String subject;
	private String message;
	private String to;
	
	public Mail(){
		this.hostName = "smtp.126.com";
		this.hostPort = "25";
		this.sslOnConnect = true;
		this.from = "lichao197808@126.com";
		this.subject = "test";
		this.message = "test email";
		this.to = "xianming.yan@west.com";
	}
	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}
	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	/**
	 * @return the hostPort
	 */
	public String getHostPort() {
		return hostPort;
	}
	/**
	 * @param hostPort the hostPort to set
	 */
	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}
	/**
	 * @return the sslOnConnect
	 */
	public boolean isSslOnConnect() {
		return sslOnConnect;
	}
	/**
	 * @param sslOnConnect the sslOnConnect to set
	 */
	public void setSslOnConnect(boolean sslOnConnect) {
		this.sslOnConnect = sslOnConnect;
	}
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
}
