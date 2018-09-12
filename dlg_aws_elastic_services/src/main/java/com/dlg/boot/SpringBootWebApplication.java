package com.dlg.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ComponentScan({"com.dlg.controler","com.dlg.service","com.dlg.dao","com.dlg.dao","com.dlg.aws","com.dlg.elastic"})
@SpringBootApplication
@EnableScheduling
public class SpringBootWebApplication extends SpringBootServletInitializer {

	private final static Logger log = LoggerFactory.getLogger(SpringBootWebApplication.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		log.debug("SpringBootWebApplication Start");
		SpringApplication.run(SpringBootWebApplication.class, args);
		log.debug("SpringBootWebApplication END");
	}

	@RequestMapping("/")
	public String welcome() {
		return "welcome";
	}
}
