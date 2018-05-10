package com.liuqh.NioDemo01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AddTest {
	    static CountDownLatch cdl=new CountDownLatch(1000);;  
	    static AtomicInteger ai=new AtomicInteger(0);
	  //  static List<Integer> list=new ArrayList<Integer>(1000);
	    static Vector<Integer> list=new Vector<Integer>();
	    public static void main(String[] args) throws InterruptedException{   
	        ExecutorService exec=Executors.newFixedThreadPool(100);  
	          for (int i = 0; i < 1000; i++) {  
	              exec.execute(new Runnable() {  
	                @Override  
	                public void run() { 
	                	list.add(ai.getAndIncrement());
	                   // System.out.println(Thread.currentThread().getName()+":"+ai.getAndIncrement());  
	                    cdl.countDown();  
	                }  
	            });  
	          }  
	        cdl.await();
	        System.out.println("sise="+list.size());  
	        exec.shutdown();
	        Collections.sort(list);
	        for(int i=0;i<list.size();i++) {
	        	System.out.println(list.get(i));
	        }
	        
	       
	    }   
}
