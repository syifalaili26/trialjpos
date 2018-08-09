package com.gl.listener;

import org.jpos.core.Configurable;
import org.jpos.core.Configuration;
import org.jpos.core.ConfigurationException;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;
import org.jpos.space.Space;
import org.jpos.space.SpaceFactory;
import org.jpos.transaction.Context;

import com.constant.Constants;

public class ServerApplicationListener implements ISORequestListener, Configurable {

	private Configuration config;

	@Override
	public void setConfiguration(Configuration config) throws ConfigurationException {
		// TODO Auto-generated method stub
		this.config = config;
	}

	@Override
	public boolean process(ISOSource isoSource, ISOMsg isoMsg) {
		// TODO Auto-generated method stub
		String spaceNet = config.get("space");
		Long timeout = config.getLong("spaceTimeout");
		String queueNet = config.get("queue");
		Context context = new Context();
		Space<String, Context> space = SpaceFactory.getSpace(spaceNet);
		
		try {
			ISOMsg respMessage = (ISOMsg) isoMsg.clone();
			respMessage.setResponseMTI();
			respMessage.set(39, "00");
			
			context.put(Constants.REQUEST_KEY, isoMsg);
			context.put(Constants.RESPONSE_KEY, respMessage);
			context.put(Constants.RESOURCE_KEY, isoSource);
		}
		catch (ISOException p) {
			p.printStackTrace();
		}
		space.out(queueNet, context, timeout);
		return false;
	}

}
