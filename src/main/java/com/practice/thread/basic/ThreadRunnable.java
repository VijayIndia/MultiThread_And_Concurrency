package com.practice.thread.basic;

public class ThreadRunnable implements Runnable{

    public void run() {
        System.out.println("Inside ThreadRunnable::run - "+Thread.currentThread().getName());
    }

}
