package com.company.interface_demo;

import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {

        Class clazz = Class.forName("com.company.Company");

        Object runnerInstance = clazz.newInstance();
        //     class    methods
        //Map<String, List<String>
        Method method = clazz.getMethod("start");
        method.invoke(runnerInstance);


    }
}

//interface RunnerInterface {
//    String run();
//}
//
//
//class Runner implements Runnable, RunnerInterface {
//
//    //String run()
//    //void run()
//    public void run() {
//        System.out.println("Method called");
//    }
//}
//
//class Company {
//
//    public void start() {
//        System.out.println("Method called (com.company.Company)");
//    }
//}