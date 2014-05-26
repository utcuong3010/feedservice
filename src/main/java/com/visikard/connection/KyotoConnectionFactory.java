package com.visikard.connection;

import kt4j.KyotoTycoonClient;
import kt4j.binary.KyotoTycoonBinaryClient;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.log4j.Logger;

public class KyotoConnectionFactory implements PoolableObjectFactory {
	
	private Logger logger = Logger.getLogger(KyotoConnectionFactory.class);
	
	

	public void activateObject(Object arg0) throws Exception {
		logger.info("Kyoto active Object");
		
	}

	public void destroyObject(Object arg0) throws Exception {
		KyotoTycoonClient conn = (KyotoTycoonClient)arg0;
		try {
			conn.stop();
		} catch (Exception ex) {
			logger.error("destroy connection object" + ex);
		}
		
	}

	public Object makeObject() throws Exception {
		KyotoTycoonClient conn = null;
		try {
			String hostname = System.getProperty("kyoto.host","localhost");
			int port = Integer.parseInt(System.getProperty("kyoto.port","1978"));
			conn = new KyotoTycoonBinaryClient(hostname, port);
			conn.start();
			
		} catch (Exception ex) {
			logger.error("make connection is fail"+ ex);
		}
		return conn;
	}

	public void passivateObject(Object arg0) throws Exception {
		logger.info("Kyoto passivateObject Object");
		
	}

	public boolean validateObject(Object arg0) {
		KyotoTycoonClient conn = (KyotoTycoonClient)arg0;
		boolean isValid = true;
		try {
			
			
		} catch(Exception ex) {
			isValid = false;
			logger.error("Object invalidated :" +ex);
		}
		return isValid;
	}

}
