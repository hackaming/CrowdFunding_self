package com.sjtu.SpringRelated;

import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao{

	public String showUser() {
		// TODO Auto-generated method stub
		return "user";
	}

	public void setUser(String userName) {
		// TODO Auto-generated method stub
		System.out.println("user 's set!");
	}

}
