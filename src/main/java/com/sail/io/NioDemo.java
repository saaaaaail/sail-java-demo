package com.sail.io;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;

/**
 * @program: sail-java-demo
 * @description: TODO
 * @author: sail
 * @create: 2020/7/28 22:28
 */

public class NioDemo {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("iloveu".getBytes());
        try {
            Selector s = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
