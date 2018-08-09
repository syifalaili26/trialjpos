package com.learn.selector;

import java.io.Serializable;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.GroupSelector;

import com.constant.Constants;

public class Selector implements GroupSelector, Configurable {
	private Configuration config;

	@Override
	public void setConfiguration(Configuration config) throws ConfigurationException {
		// TODO Auto-generated method stub
		this.config = config;
 	}

	@Override
	public String select(long length, Serializable serializable) {
		// TODO Auto-generated method stub
		Context context = (Context) serializable;
		ISOMsg reqMessage = (ISOMsg) context.get(Constants.REQUEST_KEY);
		String selector = "";
		try {
			selector = config.get(reqMessage.getMTI());
		}
		catch (ISOException p) {
			p.printStackTrace();
		}
		return selector;
	}
	
	@Override
	public int prepare(long id, Serializable context) {
		// TODO Auto-generated method stub
		return PREPARED|ABORTED|NO_JOIN;
	}

	@Override
	public void commit(long id, Serializable context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void abort(long id, Serializable context) {
		// TODO Auto-generated method stub
		
	}
}
