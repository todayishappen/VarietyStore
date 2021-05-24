package com.example.demo.learn.thread;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestThread1 extends Thread{

    private String url;//图片地址
    private String name;


    public TestThread1(String url, String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url, name);
        System.out.println("下载了文件名为："+name);
    }

    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1("http://www.baidu.com/img/flexible/logo/pc/result.png","1.jpg");
        TestThread1 testThread2 = new TestThread1("http://www.baidu.com/img/flexible/logo/pc/result.png","2.jpg");
        TestThread1 testThread3 = new TestThread1("http://www.baidu.com/img/flexible/logo/pc/result.png","3.jpg");

        new Thread(testThread1).start();
        new Thread(testThread2).start();
        new Thread(testThread3).start();
    }
}

class WebDownloader{
    public void downloader(String url, String name){
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();

}         System.out.println("IO异常，downloder方法出问题");
        }
        }


