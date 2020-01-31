package com.tistory.lky1001.buildingblocks.infrastructure;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

/**
 * Distributed Sequence Generator.
 * Inspired by Twitter snowflake: https://github.com/twitter/snowflake/tree/snowflake-2010
 * <p>
 * This class should be used as a Singleton.
 * Make sure that you create and reuse a Single instance of SequenceGenerator per node in your distributed system cluster.
 * <p>
 * from https://www.callicoder.com/distributed-unique-id-sequence-number-generator/
 */
public class SequenceGenerator {

    private static final int UNUSED_BITS = 1; // Sign bit, Unused (always set to 0)
    private static final int EPOCH_BITS = 41;
    private static final int NODE_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;

    private static final int maxNodeId = (int) (Math.pow(2, NODE_ID_BITS) - 1);
    private static final int maxSequence = (int) (Math.pow(2, SEQUENCE_BITS) - 1);

    // Custom Epoch (January 1, 2020 Midnight UTC = 2020-01-01T00:00:00Z)
    private static final long CUSTOM_EPOCH = 1577804400000L;

    private final int nodeId;

    private long lastTimestamp = -1L;
    private long sequence = 0L;

    private static SequenceGenerator instance;

    // Create SequenceGenerator with a nodeId
    private SequenceGenerator(int nodeId) {
        if (nodeId < 0 || nodeId > maxNodeId) {
            throw new IllegalArgumentException(String.format("NodeId must be between %d and %d", 0, maxNodeId));
        }
        this.nodeId = nodeId;
    }

    // Let SequenceGenerator generate a nodeId
    private SequenceGenerator() {
        this.nodeId = createNodeId();
    }

    public static synchronized long nextId() {
        if (instance == null) {
            instance = new SequenceGenerator();
        }

        long currentTimestamp = timestamp();

        if (currentTimestamp < instance.lastTimestamp) {
            currentTimestamp = instance.lastTimestamp;
        }

        if (currentTimestamp == instance.lastTimestamp) {
            instance.sequence = (instance.sequence + 1) & maxSequence;

            if (instance.sequence == 0) {
                // Sequence Exhausted, wait till next millisecond.
                currentTimestamp = instance.waitNextMillis(currentTimestamp);
            }
        } else {
            // reset sequence to start with zero for the next millisecond
            instance.sequence = 0;
        }

        instance.lastTimestamp = currentTimestamp;

        long id = currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS);
        id |= (instance.nodeId << SEQUENCE_BITS);
        id |= instance.sequence;
        return id;
    }


    // Get current timestamp in milliseconds, adjust for the custom epoch.
    private static long timestamp() {
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
    }

    // Block and wait till next millisecond
    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }

    private int createNodeId() {
        int nodeId;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X", mac[i]));
                    }
                }
            }
            nodeId = sb.toString().hashCode();
        } catch (Exception ex) {
            nodeId = (new SecureRandom().nextInt());
        }
        nodeId = nodeId & maxNodeId;
        return nodeId;
    }
}
