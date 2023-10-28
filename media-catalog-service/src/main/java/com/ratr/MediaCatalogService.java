package com.ratr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.js.lib")
public class MediaCatalogService {

	public static void main(String[] args) {
		SpringApplication.run(MediaCatalogService.class, args);
	}

}
