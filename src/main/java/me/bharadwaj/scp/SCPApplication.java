package me.bharadwaj.scp;

import io.vertx.core.Vertx;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import me.bharadwaj.scp.app.DiscardVerticle;
import me.bharadwaj.scp.verticles.helpers.DeployHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SCPApplication {

	private final DiscardVerticle discardVerticle;

	public SCPApplication(DiscardVerticle discardVerticle) {
		this.discardVerticle = discardVerticle;
	}

	public static void main(String[] args) {
		SpringApplication.run(SCPApplication.class, args);
	}

	@PostConstruct
	public void verticleLauncher() {
		var vertx = Vertx.vertx();
		DeployHelper.deployVerticle(vertx, discardVerticle);
	}

}
