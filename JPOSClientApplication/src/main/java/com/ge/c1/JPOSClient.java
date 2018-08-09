package com.ge.c1;

import com.ge.DelayFilter;
import org.jpos.iso.FilteredChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.util.LogSource;
import org.jpos.util.Logger;
import org.jpos.util.SimpleLogListener;

import java.io.IOException;

public class JPOSClient {
    public static void main(String[] args){
        Logger logger = new Logger();
        logger.addListener(new SimpleLogListener(System.out));
        //Change to FilteredChannel
        FilteredChannel channel = new ASCIIChannel("localhost",1800,new ISO87APackager());
        //Initialize DelayFilter to delay message in 5 second
        DelayFilter delayFilter = new DelayFilter(5000);
        //Assign the filter
        channel.addFilter(delayFilter);
        ((LogSource) channel).setLogger(logger, "client-logger");
        try {
            channel.connect();
            ISOMsg message = new ISOMsg ();
            message.setMTI ("0800");
            message.set (3, "000000");
            message.set (41, "00000001");
            message.set (70, "301");
            channel.send(message);
            //Get incoming message
            ISOMsg incoming = channel.receive();
            System.out.println(incoming.pack());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ISOException e) {
            e.printStackTrace();
        }
    }
}
