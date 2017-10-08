package com.crowdfunding.sjtu.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.crowdfunding.sjtu.utility.IDateService;

@Service
public class RequestSerialServiceImpl implements IRequestSerialService{

	@Override
	public String getRequestSerial(String userUUID) {
		// TODO Auto-generated method stub
		return getRequestSerial("Order",userUUID);
	}

	@Override
	public String getRequestSerial(String strRequestType,String userUUID) {
		// TODO Auto-generated method stub
		String strrequest = strRequestType+userUUID+IDateService.SDF_YYYYMMDDHHMMSS+UUID.randomUUID();
		return strrequest;
	}

}
