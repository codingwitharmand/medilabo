package com.cwa.medialabogateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MedialaboGatewayApplication {

	@Value("${microservices.backend.server-url}")
	private String serverUrl;

	@Value("${microservices.backend.port}")
	private int port;

	@Value("${microservices.backend.api-path}")
	private String apiPath;

	public static void main(String[] args) {
		SpringApplication.run(MedialaboGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRoutes(RouteLocatorBuilder builder) {
		String uri = String.format("%s:%d", serverUrl, port);
		final String apiPathId = apiPath + "/${id}";
		return builder.routes()
				.route(p -> p
						.path("/getPatients")
						.filters(f -> f.rewritePath("/getPatients", apiPath))
						.uri(uri)
				)
				.route(p -> p
						.path("/createPatient")
						.filters(f -> f.rewritePath("/createPatient", apiPath))
						.uri(uri)
				)
				.route(p -> p
						.path("/updatePatient/{id}")
						.filters(f -> f.rewritePath("/updatePatient/(?<id>.*)", apiPathId))
						.uri(uri)
				)
				.route(p -> p
						.path("/getPatient/{id}")
						.filters(f -> f.rewritePath("/getPatient/(?<id>.*)", apiPathId))
						.uri(uri)
				)
				.route(p -> p
						.path("/deletePatient/{id}")
						.filters(f -> f.rewritePath("/deletePatient/(?<id>.*)", apiPathId))
						.uri(uri)
				)
				.build();
	}

}
