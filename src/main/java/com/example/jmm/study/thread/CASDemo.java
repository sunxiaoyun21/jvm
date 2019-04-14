package com.example.jmm.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS 比较并交换
 * CAS ==>compareAndSet
 */
public class CASDemo
{

    public static void main(String[] args)
    {
        AtomicInteger atomicInteger =new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019)+" current data "+ atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+" current data "+ atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
