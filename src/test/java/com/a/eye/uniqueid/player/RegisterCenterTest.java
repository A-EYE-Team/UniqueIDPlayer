package com.a.eye.uniqueid.player;

import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.support.membermodification.MemberModifier;

/**
 * Created by wusheng on 2016/12/30.
 */
public class RegisterCenterTest {
    /**
     * Use {@link RegisterCenter}'s manual register mechanism.
     * The registered {@link IDGenerator} implementation stay on singleton status.
     */
    @Test
    public void testManualRegister() throws UnregisteredGeneratorException, IllegalAccessException {
        SimpleLongIDGenerator generator = new SimpleLongIDGenerator();
        UniqueIDPlayer player = UniqueIDPlayer.newBuilder().register(generator).find("test");

        Assert.assertEquals(0, player.nextLongId());
        Assert.assertEquals(1, player.nextLongId());

        Object delegateTarget = MemberModifier.field(UniqueIDPlayer.class, "delegate").get(player);
        Assert.assertEquals(generator, delegateTarget);
    }

    /**
     * Use {@link RegisterCenter}'s spi auto register mechanism.
     */
    @Test
    public void testSPIRegister() throws UnregisteredGeneratorException, IllegalAccessException {
        UniqueIDPlayer player = UniqueIDPlayer.newBuilder().find("test-fromfile");

        Assert.assertEquals(0, player.nextLongId());
        Assert.assertEquals(1, player.nextLongId());

        player = UniqueIDPlayer.newBuilder().find("test-fromfile");
        Assert.assertEquals(2, player.nextLongId());

        Object delegateTarget = MemberModifier.field(UniqueIDPlayer.class, "delegate").get(player);
        Assert.assertTrue(delegateTarget instanceof FileRegisterSimpleLongIDGenerator);
    }

    /**
     * Use {@link RegisterCenter}'s spi auto register mechanism.
     * Plus, use '/uniqueid.player.config' file to config the default {@link IDGenerator}
     */
    @Test
    public void testAutoFind() throws UnregisteredGeneratorException, IllegalAccessException {
        UniqueIDPlayer player = UniqueIDPlayer.newBuilder().find();

        Object delegateTarget = MemberModifier.field(UniqueIDPlayer.class, "delegate").get(player);
        Assert.assertTrue(delegateTarget instanceof FileRegisterSimpleLongIDGenerator);
    }

    /**
     * try to find an un-registered {@link IDGenerator}
     */
    @Test
    public void testUnRegisteredGenerator(){
        Exception ex = new Exception();
        try {
            UniqueIDPlayer player = UniqueIDPlayer.newBuilder().find("un-registered");
        } catch (Exception e) {
            ex = e;
        }

        Assert.assertTrue(ex instanceof UnregisteredGeneratorException);
    }
}
