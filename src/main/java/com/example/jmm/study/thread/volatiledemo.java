package com.example.jmm.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//主内存
class MaData
{
    volatile   int  number=0;

    public void addTO60(){
        this.number=60;
    }

    public  void  addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger=new AtomicInteger();
    public void addatoMic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1验证volatile的可见性
 *  1.1假如number=0，number变量之前没有添加volatile字段修饰,没有可见性
 *  1.2添加了volatile，可以解决可见性
 * 2 验证volatile不保证原子性
 *  2.1 不可分割，完整性 要么同时成功，要么同时失败
 *  2.2如何解决原子性
 *     sync
 *     atomic
 *
 */
public class volatiledemo {

    public static void main(String[] args)
    {
      MaData maData=new MaData();
        for (int i = 1; i <=20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    maData.addPlusPlus();
                    maData.addatoMic();
                }
            },String.valueOf(i)).start();
        }

        //等待20个线程全部计算成功后
        //默认两线程，一个是gc线程，一个是main线程
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() +" int finally number value "+ maData.number);
        System.out.println(Thread.currentThread().getName() +" atoMic finally number value "+ maData.atomicInteger);
    }






    //volatile可以保证可见性，及时通知其他线程主物理内存的值已经修改
    public static void seeOkByVolatile() {
        MaData myData= new MaData(); //资源类
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" come in");
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTO60();
            System.out.println(Thread.currentThread().getName()+" update number value "+  myData.number);
        },"AAA").start();

        //第二个线程是main线程
        while (myData.number==0){
            //main线程一直在这等待循环，直到number值不再等于0
        }

        System.out.println(Thread.currentThread().getName()+" misson is over " + myData.number);
    }
}
