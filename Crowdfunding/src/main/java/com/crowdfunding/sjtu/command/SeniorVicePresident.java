package com.crowdfunding.sjtu.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class SeniorVicePresident implements Command{

	@Override
	public boolean execute(Context context) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SeniorVicePresident approved");
		return false;
	}

}
