package com.a.eye.uniqueid.player;

/**
 * This is a delegate to all IDGenerator implementations.
 * <p>
 * Created by wusheng on 2016/12/29.
 */
public class UniqueIDPlayer extends IDGenerator {
    private IDGenerator delegate;

    /**
     * Default constructor, called only by {@link RegisterCenter#find()}
     *
     * @param delegate is the target {@link IDGenerator} implementation.
     */
    UniqueIDPlayer(IDGenerator delegate) {
        this.delegate = delegate;
    }

    /**
     * delegate to get next id in string format.
     *
     * @return next string id
     */
    @Override
    public String nextStringId() {
        return delegate.nextStringId();
    }

    /**
     * delegate to get next id in long format.
     *
     * @return next long id
     */
    @Override
    public long nextLongId() {
        return delegate.nextLongId();
    }

    /**
     * Can not invoke method.
     *
     * @return never done, but throw {@link UnsupportedOperationException}
     */
    @Override
    protected String name() {
        throw new UnsupportedOperationException("Can not invoke UniqueIDPlayer::name.");
    }

    /**
     * Get the {@link RegisterCenter} to create {@link UniqueIDPlayer} instance.
     *
      * @return
     */
    public static RegisterCenter newBuilder() {
        return RegisterCenter.INSTANCE;
    }
}
