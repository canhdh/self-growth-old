package com.k001.selfgrowth.zipkinserverfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinserverfinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinserverfinalApplication.class, args);
	}

}
