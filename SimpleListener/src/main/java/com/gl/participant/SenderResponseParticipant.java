package com.gl.participant;

import java.io.IOException;
import java.io.Serializable;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOSource;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import com.constant.Constants;

public class SenderResponseParticipant implements TransactionParticipant {

	@Override
	public int prepare(long length, Serializable serializable) {
		// TODO Auto-generated method stub
		Context context = (Context) serializable;
		ISOMsg respMessage = (ISOMsg) context.get(Constants.RESPONSE_KEY);
		String bit39 = respMessage.getString(39);
		if(bit39 == null) {
			try {
				respMessage.set(39, "00");
			}
			catch(ISOException p) {
				p.printStackTrace();
			}
		}
		context.put(Constants.RESPONSE_KEY, respMessage);
		return PREPARED;
	}

	@Override
	public void commit(long length, Serializable serializable) {
		// TODO Auto-generated method stub
		sendMessage((Context) serializable);
	}

	@Override
	public void abort(long length, Serializable serializable) {
		// TODO Auto-generated method stub
		sendMessage((Context) serializable);
	}
	
	private void sendMessage(Context context) {
		ISOSource isoSource = (ISOSource) context.get(Constants.RESOURCE_KEY);
		ISOMsg respMessage = (ISOMsg) context.get(Constants.RESPONSE_KEY);
		try {
			isoSource.send(respMessage);
		}
		catch (IOException p) {
			p.printStackTrace();
		}
		catch (ISOException p) {
			p.printStackTrace();
		}
	}
}
