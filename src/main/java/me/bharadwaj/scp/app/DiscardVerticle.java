package me.bharadwaj.scp.app;

import lombok.Getter;
import me.bharadwaj.scp.verticles.SCPVerticle;
import me.bharadwaj.scp.verticles.TCPDiscardVerticle;
import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;
import me.bharadwaj.scp.verticles.UDPDiscardVerticle;
import me.bharadwaj.scp.verticles.helpers.DeployHelper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DiscardVerticle extends AbstractVerticle implements SCPVerticle{

    @Getter
    private final String name;
    private final TCPDiscardVerticle tcpDiscardVerticle;
    private final UDPDiscardVerticle udpDiscardVerticle;

    public DiscardVerticle(TCPDiscardVerticle tcpDiscardVerticle,
                           UDPDiscardVerticle udpDiscardVerticle) {
        this.name = "DiscardVerticle";
        this.tcpDiscardVerticle = tcpDiscardVerticle;
        this.udpDiscardVerticle = udpDiscardVerticle;
    }

    @Override
    public void start() throws Exception {
        DeployHelper.deployVerticle(vertx, tcpDiscardVerticle,
                udpDiscardVerticle);
    }
}
