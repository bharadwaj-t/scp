package me.bharadwaj.scp.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.net.NetServerOptions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class TCPDiscardVerticle extends AbstractVerticle implements SCPVerticle {

    @Getter
    private final String name;
    private final int port;
    private final String host;

    public TCPDiscardVerticle(@Value("${discard.port}") int port, @Value("${discard.host}") String host) {
        this.name = "TCPDiscardVerticle";
        this.port = port;
        this.host = host;
    }
    @Override
    public void start() throws Exception {
        var options = new NetServerOptions()
                .setPort(port)
                .setHost(host);
        vertx.createNetServer(options)
                .connectHandler(onConnect -> {
                    log.info("TCP CLIENT ATTACHED: {}", onConnect.remoteAddress());
                    onConnect.handler(buffer -> {
                        log.info("TCP DATA: \"{}\"; discarding it ;)", buffer.toString(StandardCharsets.UTF_8));
                    });
                    onConnect.closeHandler(close -> log.info("TCP CLIENT DISCONNECT: {}", onConnect.remoteAddress()));
                })
                .listen()
                .onSuccess(succEvent -> {
                    log.info("TCP DISCARD SERVER live at {} on port {}", host, port);
                })
                .onFailure(failEvent -> {
                    log.error("Failed to launch DISCARD server", failEvent);
                });
    }
}
