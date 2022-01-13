package com.pevent.lib.utils;

public class NotFoundRouteException extends RuntimeException {
    public NotFoundRouteException(String route) {
        super(route);
    }
}
