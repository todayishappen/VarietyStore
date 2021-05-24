package com.example.demo.learn.thread;


import java.util.concurrent.locks.ReentrantLock;

//synchronize属于隐式锁
//还有显示同步锁，lock，可重用锁，ReentrantLock
public class TestThread11{

    public static void main(String[] args) {
        TestLock testLock = new TestLock();

        new Thread(testLock).start();
        new Thread(testLock).start();
        new Thread(testLock).start();
    }
}

class TestLock implements Runnable{
    int ticketNums =10;

    //定义Lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while(true){
            try {
                lock.lock();//加锁
                if(ticketNums>0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNums--);
                }else
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();  //解锁
            }
        }
    }
}


/*
lock显式，得手动关
lock只有代码块锁，调度时间短
 */