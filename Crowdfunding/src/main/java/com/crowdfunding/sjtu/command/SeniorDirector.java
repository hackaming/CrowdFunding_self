package com.crowdfunding.sjtu.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

public class SeniorDirector implements Command {

	@Override
	public boolean execute(Context context) throws Exception {
		System.out.println("SeniorDirector approved");
		return false;
	}

}
