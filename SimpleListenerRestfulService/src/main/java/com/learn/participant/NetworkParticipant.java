package com.learn.participant;

import java.io.Serializable;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import com.constant.Constants;

public class NetworkParticipant implements TransactionParticipant {

	@Override
	public int prepare(long length, Serializable serializable) {
		// TODO Auto-generated method stub
		Context context = (Context) serializable;
		ISOMsg respMessage = (ISOMsg) context.get(Constants.RESPONSE_KEY);
		
		try {
			respMessage.set(39, "00");
			context.put(Constants.RESPONSE_KEY, respMessage);
		}
		catch(ISOException p) {
			p.printStackTrace();
		}
		return PREPARED;
	}

	@Override
	public void commit(long length, Serializable serializable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void abort(long length, Serializable serializable) {
		// TODO Auto-generated method stub
		
	}

}
