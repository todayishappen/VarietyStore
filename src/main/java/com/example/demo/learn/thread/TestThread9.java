package com.example.demo.learn.thread;



public class TestThread9 {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true); //默认false
        thread.start();//设置守护进程后守护进程会跟随下面的进程一起死亡，正常god是无限true
        new Thread(you).start();
    }
}

class God implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("-----------守护线程");
        }
    }
}

class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("都在或者");

        }
        System.out.println("+++++++++++结束");
    }

}

