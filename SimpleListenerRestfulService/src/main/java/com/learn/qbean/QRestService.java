package com.learn.qbean;

import org.jpos.q2.QBeanSupport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com")
public class QRestService extends QBeanSupport {

	@Override
	protected void startService() throws Exception {
		// TODO Auto-generated method stub
		//SpringApplication app = new SpringApplication();
		SpringApplication.run(QRestService.class);
	}

}
