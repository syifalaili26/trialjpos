package com.learn.participant;

import java.io.Serializable;

import org.jpos.iso.ISOMsg;
import org.jpos.transaction.Context;
import org.jpos.transaction.TransactionParticipant;

import com.constant.Constants;
import com.learn.repository.Repository;

public class SendRestfulParticipant implements TransactionParticipant{
    @Override
    public int prepare(long l, Serializable serializable) {
        String test = "Masuk 2";
        return PREPARED;
    }

    @Override
    public void commit(long l, Serializable serializable) {
        putRestResponse((Context)serializable);
    }

    @Override
    public void abort(long l, Serializable serializable) {

    }
    private void putRestResponse(Context context){
        Repository repository = Repository.getInstance();
        ISOMsg respMessage = (ISOMsg)context.get(Constants.RESPONSE_KEY);
        repository.putMessage(respMessage);
    }
}
