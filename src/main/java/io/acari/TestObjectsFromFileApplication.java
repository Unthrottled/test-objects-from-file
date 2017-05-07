package io.acari;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.exit;
import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class TestObjectsFromFileApplication {

	public static void main(String[] args) {
		exit(run(TestObjectsFromFileApplication.class, args));
	}
}
