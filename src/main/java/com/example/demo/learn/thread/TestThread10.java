package com.example.demo.learn.thread;


public class TestThread10 implements Runnable{
    int ticket=10;
    boolean flag=true;
    public static void main(String[] args) {
        TestThread10 testThread10 = new TestThread10();
        new Thread(testThread10,"a").start();
        new Thread(testThread10,"b").start();
        new Thread(testThread10,"c").start();
        /*ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
//            new Thread(()-> {
//                list.add(Thread.currentThread().getName());
//            }).start();
            new Thread(()-> {//同步代码块
            synchronized (list){//synchronized代码块锁定的应为变化的对象，量，比如银行中锁的应该为账户，不是银行或者run方法
                list.add(Thread.currentThread().getName());
            }
            }).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());*/
    }



    @Override
    public void run() {
        while(flag){
            try {
                pay();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
//锁方法(同步方法)
    public synchronized void pay() throws InterruptedException {
            if(ticket<=0){
                flag = false;
                return ;
            }
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName()+"买到了票"+ticket--);

    }

    //synchronized代码块锁定的应为变化的对象，量，比如银行中锁的应该为账户，不是银行或者run方法
}

/*
死锁条件：
互斥，请求和保持，不剥夺，循环等待
 */


