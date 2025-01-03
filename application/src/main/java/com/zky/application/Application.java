package com.zky.application;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zky.application.aop.ExampleService;
import com.zky.library.MyService;


@SpringBootApplication(scanBasePackages = {"com.zky.library", "com.zky.application"})
@RestController
@EnableAsync(proxyTargetClass = true)
public class Application {

	@Autowired
	private MyService myService;

	@Autowired
	private ExampleService exampleService;

	@GetMapping("/")
	public String home() {
		return myService.message();
	}

	@GetMapping("/async")
	public String asyncButStillSyncing() {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			return myService.message();
		}).exceptionally(ex -> {
			return "error";
		});
		return future.join();
	}

	@GetMapping("/asyncPrint")
	public String asyncPrint() {
		CompletableFuture.runAsync(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
			System.out.println(myService.message());
		}).exceptionally(ex -> {
			System.out.println("exception: " + ex);
			return null;
		});
		return myService.message();
	}

	@GetMapping("/aop")
	public void aop() {
		exampleService.doSomething();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
