package com.xyz.basicservice;

/**
 * @author Lloyd Dcosta
 * This interface is used to notify service about when the handler thread has its looper ready.
 */
public interface HandlerReadyListener {
    void onHandlerPrepared();
}
