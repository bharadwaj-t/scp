package me.bharadwaj.scp.verticles;

import io.vertx.core.Verticle;

public interface SCPVerticle extends Verticle {
    public String getName();
}
