package com.visikard.connection;

import kt4j.KyotoTycoonClient;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;

public class KyotoConnectionPool implements KyotoConnectionPoolBean {
	
	
	private Logger logger = Logger.getLogger(KyotoConnectionPool.class);
	
	private static GenericObjectPool _connPool = null;
	
	/***
	 * contructor for connection pool
	 */
	public KyotoConnectionPool() {
		
		GenericObjectPool.Config conf = new GenericObjectPool.Config();
		conf.maxIdle = Integer.parseInt(System.getProperty("connection.maxIdle","8"));
		conf.minIdle = 1;
		conf.maxActive = -1;// Integer.parseInt(System.getProperty("connection.maxActive","8"));
		conf.maxWait = -1;
		conf.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_FAIL;
		conf.testOnBorrow = true;
		conf.testOnReturn = false;
		conf.testWhileIdle = true;
		conf.timeBetweenEvictionRunsMillis = 10000;//10 secs
		conf.numTestsPerEvictionRun = -1;
		conf.minEvictableIdleTimeMillis = 600000;//10 mins
		conf.softMinEvictableIdleTimeMillis = -1;
		conf.lifo = false;
		
		_connPool = new GenericObjectPool(new KyotoConnectionFactory(), conf);
		
		
	}
	
	public KyotoTycoonClient getConnection() throws Exception {
		KyotoTycoonClient object = (KyotoTycoonClient)_connPool.borrowObject();
		
		return object;
	}
	
	public void returnConnection(KyotoTycoonClient conn) {
		try {
			_connPool.returnObject(conn);
			
		}catch (Exception ex) {
			logger.error("return connection with error:" + ex);
		}
	}
	
	public void invalidateConnection(KyotoTycoonClient conn) {
		try {
			_connPool.invalidateObject(conn);
			
		}catch (Exception ex) {
			logger.error("invalidate connection with error:" + ex);
		}
	}
	
	
	

	public int getMaxPoolSize() {
		return _connPool.getMaxActive();
	}

	public long getMaxQueueSize() {
		return _connPool.getMaxWait();
	}

	public int getNumberActive() {
		return _connPool.getNumActive();
	}

	public int getMaxIdle() {
		// TODO Auto-generated method stub
		return _connPool.getMaxIdle();
	}

	public int getNumberIdle() {
		// TODO Auto-generated method stub
		return _connPool.getNumIdle();
	}

}
