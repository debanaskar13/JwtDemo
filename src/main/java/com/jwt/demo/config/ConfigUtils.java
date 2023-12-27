package com.jwt.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConfigUtils {
	@Autowired
	private Environment env;
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public String getProperty(String property){
		return env.getProperty(property);
	}

}
