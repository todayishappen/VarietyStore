package com.example.demo.learn;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


public class IOExample{
    public static void main(String []args) throws IOException {
        IOExample.fWriter();
    }

    public static void fWriter() throws IOException{
        FileWriter fw = new FileWriter(new File("src//main//java//com//example//demo//learn//ioexample//a.txt"));
        BufferedWriter bw =new BufferedWriter(fw);
        bw.write("测试");
        System.out.println();
        for(int i=0;i<10;i++){
            bw.write("测试"+(i+1));
        }
        bw.close();
    }
}
