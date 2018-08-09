package com.learn.repository;

import org.jpos.iso.ISOMsg;

public class Repository {
	private ISOMsg respMessage;
	private static Repository instance;
	
	private Repository() {
		
	}
	
	public static Repository getInstance() {
		if(instance == null) {
			instance = new Repository();
		}
		return instance;
	}
	
	public void putMessage(ISOMsg message) {
		this.respMessage = message;
	}
	
	public ISOMsg getMessage() {
		return respMessage;
	}
}
