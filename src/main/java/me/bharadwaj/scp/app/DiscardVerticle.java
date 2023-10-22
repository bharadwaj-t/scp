package me.bharadwaj.scp.app;

import me.bharadwaj.scp.verticles.SCPVerticle;
import me.bharadwaj.scp.verticles.TCPDiscardVerticle;
import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;
import me.bharadwaj.scp.verticles.UDPDiscardVerticle;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DiscardVerticle extends AbstractVerticle {

    private final TCPDiscardVerticle tcpDiscardVerticle;
    private final UDPDiscardVerticle udpDiscardVerticle;

    public DiscardVerticle(TCPDiscardVerticle tcpDiscardVerticle,
                           UDPDiscardVerticle udpDiscardVerticle) {
        this.tcpDiscardVerticle = tcpDiscardVerticle;
        this.udpDiscardVerticle = udpDiscardVerticle;
    }

    @Override
    public void start() throws Exception {
        deployVerticle(tcpDiscardVerticle,
                udpDiscardVerticle);
    }

    private void deployVerticle(SCPVerticle... verticles) {
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
