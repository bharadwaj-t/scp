package me.bharadwaj.scp.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UDPDiscardVerticle extends AbstractVerticle implements SCPVerticle {

    @Getter
    private final String name;
    private final int port;
    private final String host;

    public UDPDiscardVerticle(@Value("${discard.port}") int port, @Value("${discard.host}") String host) {
        this.name = "UDPDiscardVerticle";
        this.port = port;
        this.host = host;
    }

    @Override
    public void start() throws Exception {
        DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());
        socket.handler(event -> {
                    log.info("UDP Data: \"{}\"; discarding it ;)", event.data());
                })
                .listen(port, host)
                .onSuccess(succEvent -> {
                    log.info("UDP Server live on {} at {}", host, port);
                });
    }
}
