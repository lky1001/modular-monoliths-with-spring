package com.tistory.lky1001.user.domain.users;

import com.tistory.lky1001.buildingblocks.infrastructure.SequenceGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UUIDTest {

    @Test
    public void uuid() {
        for (int i = 0; i < 1000; i++) {
            (new Thread(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    System.out.println(SequenceGenerator.nextId());
                }
            })).start();
        }
    }
}
