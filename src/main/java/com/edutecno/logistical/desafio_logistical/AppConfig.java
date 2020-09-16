package com.edutecno.logistical.desafio_logistical;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan("com.edutecno.desafio_logistical")
public class AppConfig {

	@Autowired
	Environment environment;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dsm = new DriverManagerDataSource();
		dsm.setDriverClassName(environment.getProperty("driver"));
		dsm.setUrl(environment.getProperty("url"));
		dsm.setUsername(environment.getProperty("usuario"));
		dsm.setPassword(environment.getProperty("password"));
		return dsm;
	}
	
}

