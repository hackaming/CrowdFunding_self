package com.crowdfunding.sjtu.service;

public interface IRequestSerialService {
	public String getRequestSerial(String strRequestType,String userUUID);
	public String getRequestSerial(String userUUID);
}
