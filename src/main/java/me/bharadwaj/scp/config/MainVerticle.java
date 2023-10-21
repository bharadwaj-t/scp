package me.bharadwaj.scp.config;

import me.bharadwaj.scp.verticles.DiscardVerticle;
import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.deployVerticle(new DiscardVerticle())
                .onFailure(failedEvent -> {
                    log.error("Error: ", failedEvent);
                })
                .onSuccess(successEvent -> {
                    log.info("Deployed DiscardVerticle: {}", successEvent);
                });
    }
}
