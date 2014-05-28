package com.visikard.connection;

import kt4j.KyotoTycoonClient;

public class ConnectionPoolTest {
	
	public static void main(String[] args) {
		
		KyotoConnectionManager connectionMng = KyotoConnectionManager.getInstance();
		
		try {
			for(int i=0; i< 19; i++) {
			KyotoTycoonClient client = connectionMng.getConnection();
			System.out.println(client.getStatus());
			System.err.println(i);
//			connectionMng.returnConnection(client);
			}
			
			System.err.println("finish");
		
		}catch (Exception e) {
			System.err.println(e);
		}
		
		
	}

}
