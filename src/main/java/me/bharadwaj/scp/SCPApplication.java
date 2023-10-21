package me.bharadwaj.scp;

import me.bharadwaj.scp.config.MainVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SCPApplication {

	public static void main(String[] args) {
		SpringApplication.run(SCPApplication.class, args);
	}

	@Bean
	public Vertx verticleLauncher(MainVerticle mainVerticle) {
		var vertx = Vertx.vertx();
		vertx.deployVerticle(mainVerticle)
				.onFailure(failedEvent -> {
					log.error("error: ", failedEvent);
				})
				.onSuccess(successEvent -> {
					log.info("Launched MainVerticle: {}", successEvent);
				});
		return vertx;
	}

}
