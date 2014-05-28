package com.visikard.connection;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import kt4j.KyotoTycoonClient;

import org.apache.log4j.Logger;

public class KyotoConnectionManager {
	
	private static final Lock _createLock = new ReentrantLock();
	private static KyotoConnectionManager _instance = null;
	private KyotoConnectionPool _connectionImpl = null;
	
	private Logger _logger = Logger.getLogger(KyotoConnectionManager.class);
	
	/***
	 * get connection
	 * @return
	 */
	public static KyotoConnectionManager getInstance() {
		if(_instance == null) {
			_createLock.lock();
			try {
				if(_instance == null) _instance = new KyotoConnectionManager();
			} finally {
				_createLock.unlock();
			}
		}
		return _instance;
	}
	
	public KyotoConnectionManager() {
		int count = 10;
		while(count >0 && _connectionImpl == null) {
			_connectionImpl = new KyotoConnectionPool();
			--count;
		}
		if(_connectionImpl == null) {
			_logger.error("Kyoto connection manager is null");
		}
	}
	
	public synchronized KyotoTycoonClient  getConnection() throws Exception{
		return _connectionImpl.getConnection();
	}
	
	public synchronized void returnConnection(KyotoTycoonClient conn) {
		if(conn != null) {
			_connectionImpl.returnConnection(conn);
		}
	}
	public synchronized void invalidateConnection(KyotoTycoonClient conn) {
		if(conn != null) {
			_connectionImpl.invalidateConnection(conn);
		}
	}
	
	
	

}
