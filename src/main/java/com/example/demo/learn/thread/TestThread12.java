package com.example.demo.learn.thread;


//生产者消费者模型——管程法
public class TestThread12{

    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Productor(container).start();
        new Consumer(container).start();
    }
}

class Productor extends Thread{
    SynContainer container;

    public Productor(SynContainer container){
        this.container=container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了"+i+"鸡");
            container.push(new Chicken(i));
        }
    }
}

class Consumer extends  Thread{
    SynContainer container;
    public Consumer(SynContainer container){
        this.container=container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了"+container.pop().id+"只鸡");
        }
    }
}

class Chicken{
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

//缓冲区
class SynContainer{

    //容器大小
    Chicken[] chickens = new Chicken[10];
    //容器计数器
    int count =0;

    //生产这放入产品
    public synchronized void push(Chicken chicken){
        if(count==chickens.length){
            try {
                this.wait();  //通知生产者等待,然后释放锁，继续让消费者消费
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        chickens[count] = chicken;
        count++;
        this.notifyAll();//通知消费者可以消费，唤醒一个等待的进程，即消费者进程
    }

    public synchronized Chicken pop(){
        if(count == 0){
            try {
                this.wait(); //通知消费者等待,然后释放锁，继续让生产者生产
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        Chicken chicken = chickens[count];
        this.notifyAll();  //通知生产者可以生产，唤醒一个等待的进程，即生产者进程
        return chicken;
    }
}