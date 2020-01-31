package com.tistory.lky1001.buildingblocks.infrastructure;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class SequenceGeneratorTest {

    @Test
    public void successGenerateUniqueId() throws InterruptedException {
        ConcurrentHashMap<Long, Boolean> map = new ConcurrentHashMap<>();

        CountDownLatch countDownLatch = new CountDownLatch(10000);

        for (int i = 0; i < 10000; i++) {
            (new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    long id = SequenceGenerator.nextId();
                    Assert.assertFalse(map.containsKey(id));
                    map.put(id, true);
                }

                countDownLatch.countDown();
            })).start();
        }

        countDownLatch.await();

        Assert.assertEquals(10000 * 1000, map.size());
    }
}
