package com.company.annotation_demo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.company.annotation_demo.CompanyStarter");

        Method [] methods = clazz.getMethods();

        Method method = Arrays.stream(methods)
                .filter(x -> x.isAnnotationPresent(Run.class))
                .findFirst()
                .orElse(null);

        if (method != null) {

            Run runAnnotation = method.getAnnotation(Run.class);

            for (int i = 0; i < runAnnotation.callsCount(); i++) {

                Object returnType = method.invoke(clazz.newInstance());

                if (!runAnnotation.returnType().equals(Void.class)) {
                    System.out.println("Printing return value: " + returnType);
                }
            }

        } else {
            System.out.println("Method with annotation @Run is not found!");
        }

    }
}

//Retention -> зона видимости
//Target    -> над чем можно ставить (class/method/field/method parameter)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Run {

    //primitives, Class, enums

    int callsCount() default 1;
    Class returnType() default Void.class;
}

//-------------------------------------------------

class Runner {

    @Run(callsCount = 3)
    public void run() {
        System.out.println("method");
    }

    public void run(String input) {
    }

    public void run(String input, String authorName) {
    }
}

class CompanyStarter {

    @Run(returnType = String.class)
    public String start() {
        System.out.println("Enter your text:");

        return new Scanner(System.in).nextLine();
    }
}