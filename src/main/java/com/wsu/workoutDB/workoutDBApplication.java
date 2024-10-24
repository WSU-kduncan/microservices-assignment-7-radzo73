package com.wsu.workoutDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(
	//exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class}
)
public class workoutDBApplication {

	/**
	 * SpringBoot Starter method on embedded tomcat server
	 * @param args - Allow to pass String array JVM arguments to set value dynamically during runtime
	 */
	public static void main(String[] args) {
		SpringApplication.run(workoutDBApplication.class, args);
	}

}
