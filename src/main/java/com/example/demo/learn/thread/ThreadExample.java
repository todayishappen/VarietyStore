package com.example.demo.learn.thread;

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
/*public  class ThreadExample implements Runnable{
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
}*/

//实现Callable
/*import java.util.concurrent.*;

public class ThreadExample implements Callable<Boolean> {
    private  String name;
    public ThreadExample(String name){
        this.name =name;
    }
    @Override
    public Boolean call() {
        for (int i = 0; i < 20; i++) {
            System.out.println(this.name+"在看代码"+i);
        }
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadExample threadExample1 = new ThreadExample("小明");
        ThreadExample threadExample2 = new ThreadExample("老师");
        ThreadExample threadExample3 = new ThreadExample("aa");
        //创建执行服务（线程池）
        ExecutorService ser =Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> r1 = ser.submit(threadExample1);
        Future<Boolean> r2 = ser.submit(threadExample2);
        Future<Boolean> r3 = ser.submit(threadExample3);
        //获取执行结果
        boolean rs1 = r1.get();
        boolean rs2 = r1.get();
        boolean rs3 = r1.get();
        System.out.println(rs1 );
        //关闭服务（销毁线程池）
        ser.shutdownNow();
    }
}*/

//Lambda表达式
/*
必须只有一个抽象方法的接口，函数式接口
//丢失复用性
 */
/*interface ILike{
    void lambda();
}
class Like implements ILike{
    @Override
    public void lambda() {
        System.out.println("测试lambda");
    }
}
public class ThreadExample{

    //静态内部类
    static class Like2 implements ILike{
        @Override
        public void lambda() {
            System.out.println("测试lambda2");
        }
    }

    public static void main(String[] args) {
//        ILike like = new Like();
//        like.lambda();

        //匿名内部类是相对最简化的
*//*        ILike like1= new ILike(){
            @Override
            public void lambda() {
                System.out.println("测试lambda1");
            }
        };
        like1.lambda();*//*

        //局部内部类
*//*        class Like3 implements ILike{
            @Override
            public void lambda() {
                System.out.println("测试lambda3");
            }
        }*//*

        //lambda
        ILike like4 = ()->{
            System.out.println("测试lambda最终");
        }; //参数括号，花括号都可以去掉，前提是一个
        like4.lambda();
    }
}*/

//代理
/*
代理就是1用2去表达内容
 */
interface  A{
    void test();
}
class B implements A{
    @Override
    public void test() {
        System.out.println("我想要查看照片");
    }
}
class C implements A{
    private B b;
    public C(B b){
        this.b = b;
    }
    @Override
    public void test() {
        this.b.test();
        System.out.println("我要代替b看照片");

    }
}
public class ThreadExample{
    public static void main(String[] args) {
        C c = new C(new B());
        c.test();
        new C(new B()).test();
        new Thread(()-> System.out.println("你好")).start();
    }
}

