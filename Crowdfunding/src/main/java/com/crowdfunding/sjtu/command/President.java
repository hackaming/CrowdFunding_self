package com.crowdfunding.sjtu.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class President implements Command{

	@Override
	public boolean execute(Context context) throws Exception {
		System.out.println("President approved");
		return false;
	}

}
