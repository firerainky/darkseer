package com.zky.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zky.library.MyService;


@SpringBootApplication(scanBasePackages = "com.zky.library")
@RestController
public class Application {

	private final MyService myService;

	public Application(MyService myService) {
		this.myService = myService;
	}

	@GetMapping("/")
	public String home() {
		return myService.message();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
