package io.pivotal.cf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * catch point for enabling all aspects of the spring app
 * @author wschipp
 *
 */
@Configuration
@ComponentScan
@EnableWebMvc
public class Application {

}
