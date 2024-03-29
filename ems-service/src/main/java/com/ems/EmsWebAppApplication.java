package com.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class EmsWebAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		log.info("--------> EmsWebAppApplication started..");
		SpringApplication springApplication = new SpringApplication(EmsWebAppApplication.class);
		//springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmsWebAppApplication.class);
	}

}