package com.a.eye.uniqueid.player;

/**
 * Throws this exception, when {@link RegisterCenter} can not find the registered implementation.
 * <p>
 * The registered name is from {@link IDGenerator#name()}
 * <p>
 * Created by wusheng on 2016/12/29.
 */
public class UnregisteredGeneratorException extends Exception {
    /**
     * construction with only message
     *
     * @param message exception description
     */
    UnregisteredGeneratorException(String message) {
        super(message);
    }

    /**
     * construction with only message and cause exception
     * @param message exception description
     * @param cause case exception
     */
    UnregisteredGeneratorException(String message, Exception cause) {
        super(message, cause);
    }
}
