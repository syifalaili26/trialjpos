package com.clientsimulator;

import java.io.IOException;
import java.util.Date;

import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISODate;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.Logger;
import org.jpos.util.LogSource;
import org.jpos.util.SimpleLogListener;

public class ClientSimulator {

	public static void main(String[] args) throws IOException, ISOException {
		// TODO Auto-generated method stub
		Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        ISOChannel channel = new ASCIIChannel("localhost",2500,new ISO87APackager());
        ((LogSource) channel).setLogger(logger,"channel");
        channel.connect();

        ISOMsg message = new ISOMsg();

        message.set(0,"0200");
        message.set(3,"389000");
        message.set(4,"000000000000");
        message.set(7, ISODate.getDate(new Date()));
        message.set(11,"143708");
        message.set(12,"143708");
        message.set(13,"1005");
        message.set(15,"1005");
        message.set(32,"123");
        message.set(41,"11110001");
        message.set(42,"111111111100001");
        message.set(48,"DF01053132383032DF020A30323933333630313438DF090431313031DF0A06313233343536");
        message.set(49,"360");

        channel.send(message);

        ISOMsg received = channel.receive ();
	}
}
