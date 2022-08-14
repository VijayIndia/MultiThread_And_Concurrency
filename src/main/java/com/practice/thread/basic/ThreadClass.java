package com.practice.thread.basic;

public class ThreadClass extends Thread{

    public void run(){
        System.out.println("Inside ThreadClass::run() "+Thread.currentThread().getName());
    }
}
