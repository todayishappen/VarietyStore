package com.example.demo.learn;

//继承Thread
/*public class ThreadExample extends Thread {

    @Override
    public void run(){
        super.run();
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看");
        }
    }
    public static void main(String[] args){
        ThreadExample test1 = new ThreadExample();
        test1.start();//start()方法是和主方法同步运行，run方法是正常的方法，CPU调度执行
        for (int i = 0; i < 20; i++) {
            System.out.println("线程");
        }
    }
}*/

//实现Runnable
public  class ThreadExample implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("我在看代码"+i);
        }
    }

    public static void main(String[] args) {
        ThreadExample threadExample = new ThreadExample();
//        Thread thread = new Thread(threadExample);
////        thread.start();
        new Thread(threadExample).start();
        for (int i = 0; i < 20; i++) {
            System.out.println("多线程" + i);
        }
    }
}

