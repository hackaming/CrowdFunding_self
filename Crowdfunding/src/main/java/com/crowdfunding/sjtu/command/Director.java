package com.crowdfunding.sjtu.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class Director implements Command{

	@Override
	public boolean execute(Context context) throws Exception {
		System.out.println("Director approved"+context.get("user"));
		return false;
	}

}
