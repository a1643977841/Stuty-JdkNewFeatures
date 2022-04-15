package com.ah.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * <p>
 *  Lambda表达式的使用<br><br>
 *      1、举例:(o1, o2) 箭头 Integer.compare(o1, o2);<br>
 *      2、格式:<br>
 *          箭头: lambda操作符或箭头操作符<br>
 *          箭头左边: lambda形参列表(其实就是接口中抽象方法的形参列表)<br>
 *          箭头右边: lambda体(其实就是接口中抽象方法的方法体)<br><br>
 *      3、lambda表达式的使用分6种情况<br>
 *          总结：<br>
 *              1、箭头左边：lambda形参列表参数类型可以省略，因为编译器可以通过上下文推断出来，如果lambda形参列表只有一个参数，其一对()可以省略<br>
 *              2、箭头右边：lambda体应该使用一对{}包裹，如果只有一条执行语句(可以是return)，可以省略{},如果是return,可以省略return<br><br>
 *      4、lambda表达式的本质是作为函数式接口的实例(如果一个接口中，之声明了一个抽象方法，那么这个接口成为函数式接口)<br>
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/9 22:26
 */
public class LambdaTest {
    /**
     * 语法格式一: 无参，无返回值
     */
    @Test
    public void test1(){
        System.out.println("-----普通写法-----");
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();
        System.out.println("-----lambda写法-----");
        Runnable r2 = () -> System.out.println("lambda--我爱北京天安门");
        r2.run();
    }

    /**
     * 语法格式二: lambda需要一个参数，无返回值
     */
    @Test
    public void test2() {
        System.out.println("-----普通写法-----");
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("我爱北京天安门");
        System.out.println("-----lambda写法-----");
        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("lambda--我爱北京天安门");
    }

    /**
     * 语法格式三 : 数据类型可以省略，因为可以由编译器推断得出，成为“类型推断”
     */
    @Test
    public void test3() {
        System.out.println("-----lambda写法-----");
        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("lambda--我爱北京天安门");
        System.out.println("-----lambda 优化写法-----");
        Consumer<String> con2 = (s) -> {
            System.out.println(s);
        };
        con1.accept("lambda--我爱北京天安门");
    }
    /**
     * 语法格式四 : lambda若需要一个参数，参数的小括号可以省略
     */
    @Test
    public void test4() {
        System.out.println("-----lambda写法-----");
        Consumer<String> con1 = (s) -> {
            System.out.println(s);
        };
        con1.accept("lambda--我爱北京天安门");
        System.out.println("-----lambda 优化写法-----");
        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con1.accept("lambda--我爱北京天安门");
    }
    /**
     * 语法格式五 : lambda需要两个或以上参数，多条执行语句，并且可以有返回值
     */
    @Test
    public void test5() {
        System.out.println("-----普通写法-----");
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(5, 3));
        System.out.println("-----lambda写法-----");
        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        System.out.println(com2.compare(2, 4));
    }
    /**
     * 语法格式六 : 当lambda体中只有一条语句时，return和大括号都可以省略
     */
    @Test
    public void test6() {
        System.out.println("-----lambda写法-----");
        Comparator<Integer> com1 = (o1, o2) -> {
            return o1.compareTo(o2);
        };
        System.out.println(com1.compare(2, 4));
        System.out.println("-----lambda 优化写法-----");
        Comparator<Integer> com2 = (o1, o2) -> o1.compareTo(o2);
        System.out.println(com2.compare(6, 3));
    }


}
