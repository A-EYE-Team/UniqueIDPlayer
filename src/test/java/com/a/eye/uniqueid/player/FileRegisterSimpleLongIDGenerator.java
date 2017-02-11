package com.a.eye.uniqueid.player;

/**
 * Nothing different from {@link SimpleLongIDGenerator}.
 * Just inherit to create this new class, which is registered through spi.
 * <p>
 * Created by wusheng on 2016/12/30.
 */
public class FileRegisterSimpleLongIDGenerator extends SimpleLongIDGenerator {
    /**
     * Override and return a new name.
     *
     * @return new register name.
     */
    @Override
    protected String name() {
        return "test-fromfile";
    }
}
