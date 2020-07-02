package com.example.springboot_o2o.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

public class JavaUtils {
    public static void main(String[] args) {

//        Runnable r1 = () -> System.out.println("hello lambda");
//        r1.run();
//
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("hello lambda!");

        BinaryOperator<Integer> binaryOperator = (x, y) -> {
            int a = x * 2;
            int b = y + 2;
            return a + b;
        };
        System.out.println(binaryOperator.apply(1, 2));

        List names = new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        names.forEach(System.out::println);
    }
}
