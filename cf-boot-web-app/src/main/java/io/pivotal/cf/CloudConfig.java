package io.pivotal.cf;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * cloud specific configuration for PCF 1.1
 * 
 * this leverages the services bound in PCF to lookup the datasource
 * 
 * @author wschipp
 *
 */
@Configuration
@Profile("default")
public class CloudConfig extends AbstractCloudConfig {

	@Bean
	public DataSource dataSource() {
		return connectionFactory().dataSource();
	}
	
}
