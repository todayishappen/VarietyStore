package com.example.demo.learn.thread;

public class TestThread2 implements Runnable{

    private int ticketNums = 10;


    @Override
    public void run() {
        while(true){
            if(ticketNums<=0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNums--+"票");
        }
    }

    public static void main(String[] args) {
        TestThread2 testThread2 = new TestThread2();
        new Thread(testThread2, "小明").start();
        new Thread(testThread2, "老师").start();
        new Thread(testThread2, "aa").start();
//--产生并发问题，多个抢夺一个资源
    }
}
