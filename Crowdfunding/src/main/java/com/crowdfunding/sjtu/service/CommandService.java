package com.crowdfunding.sjtu.service;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.impl.ChainBase;
import org.apache.commons.chain.impl.ContextBase;
import org.springframework.stereotype.Service;

import com.crowdfunding.sjtu.command.CEO;
import com.crowdfunding.sjtu.command.Director;
import com.crowdfunding.sjtu.command.FirstLineManager;
import com.crowdfunding.sjtu.command.President;
import com.crowdfunding.sjtu.command.SecondLineManager;
import com.crowdfunding.sjtu.command.SeniorDirector;
import com.crowdfunding.sjtu.command.SeniorVicePresident;
import com.crowdfunding.sjtu.command.Supervisor;
import com.crowdfunding.sjtu.command.VicePresident;

@Service
public class CommandService extends ChainBase{
	public CommandService(){
		addCommand(new CEO());
		addCommand(new Director());
		addCommand(new FirstLineManager());
		addCommand(new President());
		addCommand(new SecondLineManager());
		addCommand(new SeniorDirector());
		addCommand(new SeniorVicePresident());
		addCommand(new Supervisor());
		addCommand(new VicePresident());
	}
	public void approve() throws Exception{
		Command command = new CommandService();
		Context ctx = new ContextBase();
		ctx.put("user", "xianming");
		command.execute(ctx);
	}
}
