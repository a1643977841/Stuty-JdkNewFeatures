package com.ah.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * <p>
 *  java内置的4大核心函数式接口
 *  消费型接口：Consumer<T> void accept(T t);
 *  生产型接口：Supplier<T> T get();
 *  函数型接口：Function<T, R> R apply(T t);
 *  断言型接口：Predicate<T> boolean test(T t);
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/9 23:38
 */
public class Lambda1Test {
    /**
     * 消费型接口
     */
    @Test
    public void test1() {
        System.out.println("-----普通写法-----");
        happyTime(1000.0, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("happy time: " + aDouble);
            }
        });
        System.out.println("-----lambda写法-----");
        happyTime(400, money -> System.out.println("lambda---happy time: " + money));
    }


    public void happyTime(double money, Consumer<Double> consumer) {
            consumer.accept(money);
    }

    /**
     *  断言型接口
     */
    @Test
    public void test2() {
        List<String> list = Arrays.asList("hello", "world", "java", "java8");
        System.out.println("-----普通写法-----");
        List<String> l1 = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("l");
            }
        });
        System.out.println(l1);
        System.out.println("-----lambda写法-----");
        List<String> l = filterString(list, s -> s.length() >= 5);
        System.out.println(l);
    }

    /**
     * 根据给定的规则，过滤集合中的字符串。此规则由Predicate的方法
     * @param list nick
     * @param predicate nick
     * @return nick
     */
    public List<String> filterString(List<String> list, Predicate<String> predicate) {
        List<String> filterList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                filterList.add(s);
            }
        }
        return filterList;
    }
}
