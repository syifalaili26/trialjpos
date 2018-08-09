package com.learn.controller;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.entity.MessageEntity;
import com.learn.repository.Repository;

@RestController
public class ISORestController {
	@RequestMapping(value = "isomsg", method = RequestMethod.GET)
	public MessageEntity getMessageRest() {
		Repository repository = Repository.getInstance();
		ISOMsg message = repository.getMessage();
		MessageEntity entity = new MessageEntity();
		try {
			entity.setField0(message.getMTI());
			entity.setField3(message.getString(3));
			entity.setField4(message.getString(4));
			entity.setField11(message.getString(11));
        }
		catch (ISOException p) {
            p.printStackTrace();
        }
        return entity;
	}
}
