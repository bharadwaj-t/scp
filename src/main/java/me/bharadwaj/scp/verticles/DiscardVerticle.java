package me.bharadwaj.scp.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServerOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class DiscardVerticle extends AbstractVerticle {
    private final int port;
    private final String host;

    public DiscardVerticle(@Value("${discard.port}") int port, @Value("${discard.host}") String host) {
        this.port = port;
        this.host = host;
    }
    @Override
    public void start() throws Exception {
        log.info("HERE: {} {}", host, port);
        var options = new NetServerOptions()
                .setPort(port)
                .setHost(host);
        vertx.createNetServer(options)
                .connectHandler(onConnect -> {
                    log.info("CLIENT ATTACHED: {}", onConnect.remoteAddress());
                    onConnect.handler(buffer -> {
                        log.info("RECIEVED \"{}\", but discarding it ;)", buffer.toString(StandardCharsets.UTF_8));
                    });
                    onConnect.closeHandler(close -> log.info("CLIENT DISCONNECT: {}", onConnect.remoteAddress()));
                })
                .listen()
                .onSuccess(succEvent -> {
                    log.info("DISCARD SERVER live at {} on port {}", host, port);
                })
                .onFailure(failEvent -> {
                    log.error("Failed to launch DISCARD server", failEvent);
                });
    }
}
