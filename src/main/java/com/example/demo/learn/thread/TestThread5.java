package com.example.demo.learn.thread;

public class TestThread5 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"礼让开始");
        Thread.yield();
        System.out.println(Thread.currentThread().getName()+"礼让结束");
    }

    public static void main(String[] args) {
        TestThread5 TestThread5 = new TestThread5();
        new Thread(TestThread5,"a").start();
        new Thread(TestThread5,"b").start();
    }
}

