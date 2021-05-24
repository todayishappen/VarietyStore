package com.example.demo.learn.thread;

public class TestThread8 implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程"+Thread.currentThread().getPriority());
    }

    public static void main(String[] args)  {
        TestThread8 testThread8 = new TestThread8();
        Thread t1 = new Thread(testThread8);
        Thread t2 = new Thread(testThread8);
        Thread t3 = new Thread(testThread8);
        t1.start();
        t2.setPriority(4);
        t2.start();
        t3.setPriority(8);
        t3.start();
    }
}

