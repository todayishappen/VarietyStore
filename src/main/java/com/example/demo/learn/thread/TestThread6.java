package com.example.demo.learn.thread;

public class TestThread6 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"礼让开始");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        TestThread6 testThread6 = new TestThread6();
        Thread thread = new Thread(testThread6,"a");
        thread.start();

        for (int i = 0; i < 1000; i++) {
            if(i == 200){
                thread.join();
            }
            System.out.println("main"+i);
        }
    }
}

