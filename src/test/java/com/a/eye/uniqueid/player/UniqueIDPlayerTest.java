package com.a.eye.uniqueid.player;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wusheng on 2016/12/30.
 */
public class UniqueIDPlayerTest {
    /**
     * Test {@link UniqueIDPlayer}'s delegate mechanism.
     */
    @Test
    public void testUniqueIdPlayerDelegation() {
        UniqueIDPlayer delegate = new UniqueIDPlayer(new SimpleLongIDGenerator());

        Assert.assertEquals(0, delegate.nextLongId());
        Assert.assertEquals("1", delegate.nextStringId());

        Exception ex = new Exception();
        try {
            delegate.name();
        } catch (Exception e) {
            ex = e;
        }

        Assert.assertTrue(ex instanceof UnsupportedOperationException);
    }

    /**
     * {@link UniqueIDPlayer#newBuilder()} always return the {@link RegisterCenter}'s singleton
     */
    @Test
    public void testNewBuilder(){
        Assert.assertEquals(RegisterCenter.INSTANCE, UniqueIDPlayer.newBuilder());
        Assert.assertEquals(RegisterCenter.INSTANCE, UniqueIDPlayer.newBuilder());
    }
}
