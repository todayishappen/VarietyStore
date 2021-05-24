package com.example.demo.learn.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestThread4 {

    public static void down() throws InterruptedException{
        int num = 10;
        Date date = new Date(System.currentTimeMillis());
        while(true){
            Thread.sleep(500);
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
            date = new Date(System.currentTimeMillis());
            num--;
            if(num <= 0)
                break;
        }
    }




    public static void main(String[] args) {
        try {
            down();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//--产生并发问题，多个抢夺一个资源
    }
}
