package com.crowdfunding.sjtu.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class CEO implements Command{

	@Override
	public boolean execute(Context context) throws Exception {
System.out.println("CEO approved"+context.get("user"));
		return false;
	}

}
