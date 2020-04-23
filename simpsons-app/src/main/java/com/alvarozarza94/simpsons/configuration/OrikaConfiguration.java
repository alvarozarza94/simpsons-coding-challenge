package com.alvarozarza94.simpsons.configuration;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfiguration {

	@Bean
	public MapperFacade defaultMapper() {

		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		return factory.getMapperFacade();
	}
}
