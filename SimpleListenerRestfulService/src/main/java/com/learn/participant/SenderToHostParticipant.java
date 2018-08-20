package com.learn.participant;

import java.io.Serializable;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.MUX;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;
import org.jpos.util.NameRegistrar;

import com.constant.Constants;

public class SenderToHostParticipant implements TransactionParticipant{

    @Override
    public int prepare(long l, Serializable serializable) {
        String test = "Masuk";
        return PREPARED;
    }

    @Override
    public void commit(long l, Serializable serializable) {
        try {
            sendToRemoteHost((Context)serializable);
        } catch (NameRegistrar.NotFoundException e) {
            e.printStackTrace();
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void abort(long l, Serializable serializable) {
        try {
            sendToRemoteHost((Context)serializable);
        } catch (NameRegistrar.NotFoundException e) {
            e.printStackTrace();
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }

    private void sendToRemoteHost(Context context) throws NameRegistrar.NotFoundException, ISOException {
        MUX mux = (MUX) NameRegistrar.get("mux.mymux");
        ISOMsg resMsg = (ISOMsg)context.get(Constants.RESPONSE_KEY);
        ISOMsg buffMsg = (ISOMsg)mux.request(resMsg,1000);
        if(buffMsg!=null){
            System.out.println("========================= "+buffMsg.pack());
        }
    }
}
