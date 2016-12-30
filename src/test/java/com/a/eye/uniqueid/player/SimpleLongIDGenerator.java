package com.a.eye.uniqueid.player;

/**
 * Just a simple implementation of {@link IDGenerator}
 * Use a auto-increment {@link Long} to simulate a generator.
 * <p>
 * It's major purpose is only testing, do not consider about not fitting of get nextId concurrency.
 * <p>
 * Created by wusheng on 2016/12/30.
 */
public class SimpleLongIDGenerator extends IDGenerator {
    /**
     * Current id
     */
    private Long currentId = 0L;

    /**
     * Get {@link SimpleLongIDGenerator#nextLongId()}, and parse it to {@link String}.
     *
     * @return next string id, actually it's a long.
     */
    @Override
    public String nextStringId() {
        return nextLongId() + "";
    }

    /**
     * Get {@link SimpleLongIDGenerator#currentId}, and auto plus one. ( {@link SimpleLongIDGenerator#currentId}++ )
     *
     * @return current long id.
     */
    @Override
    public long nextLongId() {
        return currentId++;
    }

    /**
     * Register this implementation as a 'test' generator.
     *
     * @return const "test".
     */
    @Override
    protected String name() {
        return "test";
    }
}
