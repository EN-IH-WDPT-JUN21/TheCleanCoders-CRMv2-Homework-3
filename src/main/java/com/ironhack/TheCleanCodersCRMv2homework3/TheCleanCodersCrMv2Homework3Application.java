package com.ironhack.TheCleanCodersCRMv2homework3;

import com.ironhack.TheCleanCodersCRMv2homework3.main.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class TheCleanCodersCrMv2Homework3Application implements CommandLineRunner {

	@Autowired
	Main main;

	public static void main(String[] args) {
		SpringApplication.run(TheCleanCodersCrMv2Homework3Application.class, args);
	}

	@Override
	public void run(String... args) throws InterruptedException {

		main.consoleRun();

	}
}
