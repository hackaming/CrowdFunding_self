package com.crowdfunding.sjtu.MQ;

public interface OrderProduce {
	public void sendDataToQueue(String queueKey, Object object);
}
