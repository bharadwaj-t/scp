package me.bharadwaj.scp.verticles.helpers;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;
import me.bharadwaj.scp.verticles.SCPVerticle;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeployHelper {

    public static void deployVerticle(Vertx vertx, SCPVerticle... verticles) {
        for (SCPVerticle verticle : verticles) {
            vertx.deployVerticle(verticle)
                    .onFailure(failedEvent -> {
                        log.error("Error: ", failedEvent);
                    })
                    .onSuccess(successEvent -> {
                        log.info("Deployed {} : {}", verticle.getName(), successEvent);
                    });
        }
    }
}
