package com.visikard.connection;

public interface KyotoConnectionPoolBean {

	public int getMaxPoolSize();
	public int getMaxQueueSize();
	public int getNumberActive();
	public int getMaxIdle();
	public int getNumberIdle();
}
