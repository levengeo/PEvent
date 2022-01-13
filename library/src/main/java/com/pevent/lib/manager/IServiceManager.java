package com.pevent.lib.manager;

public interface IServiceManager {

    void publish(Object service, Class<?>... interfaces);

    void publish(Object service);

    void unPublish(Object service);

    void unPublish(Object service, Class<?>... interfaces);

}
