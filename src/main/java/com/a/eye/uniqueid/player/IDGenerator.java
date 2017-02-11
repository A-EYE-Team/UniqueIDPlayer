package com.a.eye.uniqueid.player;

/**
 * Abstract ID Generator Definition.
 * Every generator must implement this abstract class,
 * and provide nextId({@link IDGenerator#nextStringId()} and {@link IDGenerator#nextLongId()}) methods.
 * <p>
 * {@link RegisterCenter} will keep all implementations stay in singleton.
 * <p>
 * Created by wusheng on 2016/12/29.
 */
public abstract class IDGenerator{
    /**
     * get next id in string format.
     *
     * @return next string id
     */
    public abstract String nextStringId();

    /**
     * get next id in long format.
     *
     * @return next long id
     */
    public abstract long nextLongId();

    /**
     * Report the registered name of this {@link IDGenerator} implementation.
     * This method stays in 'protected', and only be called inside {@link RegisterCenter#RegisterCenter()}
     *
     * @return registered name.
     */
    protected abstract String name();
}
