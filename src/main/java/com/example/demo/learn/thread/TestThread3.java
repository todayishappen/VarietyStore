package com.example.demo.learn.thread;

public class TestThread3 implements Runnable{

    private  static String winner;
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {

            if(Thread.currentThread().getName().equals("兔子") && i%10 ==0){
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            boolean flag = gameOver(i);
            if(flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"跑了" + i+"步");
        }
    }
    public static boolean gameOver(int steps){
        if(winner!=null){
            return true;
        }{
            if(steps>=100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is" + winner);
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        TestThread3 testThread3 = new TestThread3();
        new Thread(testThread3,"兔子").start();
        new Thread(testThread3,"乌龟").start();
    }
}
