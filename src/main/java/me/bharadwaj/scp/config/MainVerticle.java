package me.bharadwaj.scp.config;

import com.sun.tools.javac.Main;
import me.bharadwaj.scp.verticles.DiscardVerticle;
import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MainVerticle extends AbstractVerticle {

    private final DiscardVerticle discardVerticle;

    public MainVerticle(DiscardVerticle discardVerticle) {
        this.discardVerticle = discardVerticle;
    }

    @Override
    public void start() throws Exception {
        vertx.deployVerticle(discardVerticle)
                .onFailure(failedEvent -> {
                    log.error("Error: ", failedEvent);
                })
                .onSuccess(successEvent -> {
                    log.info("Deployed DiscardVerticle: {}", successEvent);
                });
    }
}
