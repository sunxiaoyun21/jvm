package com.example.jmm.study.thread;

public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+" 我是构造方法 SingletonDemo()");
    }

    //DCL(Dooubel Check Lock 双端检锁机制)
    public static SingletonDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }

        }
        return instance;
    }
    public static void main(String[] args) {
        //单线程
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//
        //并发多线程
        for (int i = 0; i <= 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
