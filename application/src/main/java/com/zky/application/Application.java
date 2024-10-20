package com.zky.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;

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

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
