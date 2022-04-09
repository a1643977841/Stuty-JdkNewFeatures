package com.ah.lambda.method_quote;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>
 *  方法引用的使用
 *  1、使用情景：当要传递给lambda体的操作，已经有实现的方法了，可以使用方法引用
 *  2、方法引用：本质上就是lambda表达式，而lambda表达式作为函数式接口的实例，所以方法引用也是函数式接口的实例
 *  3、使用：对象(类)::实例方法名
 *  4、具体分为以下三种情况：
 *      对象::非静态方法
 *      类::静态方法
 *      类::非静态方法
 *  5、方法引用的使用要求：
 *      要求接口中的抽象方法的参数列表和返回值类型与方法引用的方法的参数列表和返回值类型保持一致(针对于情况一和情况二)
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/10 0:09
 */
public class MethodRefTest {
    /**
     * 情况一：对象::实例方法名
     * Consumer中的void accept方法
     * PrintStream中的void println方法
     */
    @Test
    public void test1() {
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("Hello Lambda!");
        System.out.println("----------------------------");
        Consumer<String> con2 = System.out::println;
        con2.accept("Hello Lambda----!");
    }

    /**
     * Supplier中的T get方法
     * Employee中的String getName方法
     */
    @Test
    public void test2() {
        Employee emp = new Employee(1001,"张三", 18, 9999.99);
        Supplier<String> sup = () -> emp.getName();
        System.out.println(sup.get());
        System.out.println("----------------------------");
        Supplier<String> sup1 = emp::getName;
        System.out.println(sup1.get());
    }

    /**
     * 情况二：类::静态方法
     * Comparator中的int compare(T t1, T t2)方法
     * Integer中的int compare(T t1, T t2)方法
     */
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println(com.compare(3, 1));
        System.out.println("----------------------------");
        Comparator<Integer> com1 = Integer::compare;
        System.out.println(com1.compare(3, 1));

    }

    /**
     * Function中的R apply(T t)方法
     * Math中的Long round(Double d)方法
     */
    @Test
    public void test4() {
        System.out.println("-----普通写法-----");
        Function<Double, Long> fun = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        System.out.println(fun.apply(3.1415926));
        System.out.println("-----lambda写法-----");
        Function<Double, Long> fun1 = d -> Math.round(d);
        System.out.println(fun1.apply(2.1415926));
        System.out.println("-----lambda方法引用写法-----");
        Function<Double, Long> fun2 = Math::round;
        System.out.println(fun2.apply(1.1415926));
    }

    /**
     * 情况三：类::实例方法名
     * Comparator中的int compare(T t1, T t2)方法
     * String中的int t1.compareTo(t2)方法
     */
    @Test
    public void test5() {
        Comparator<String> com1 = (x, y) -> x.compareTo(y);
        System.out.println(com1.compare("aaa", "bbb"));
        System.out.println("----------------------------");
        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("aaa", "bbb"));
    }
    /**
     * BiPredicate中的boolean test(T t1, T t2)方法
     * String中的boolean t1.equals(t2)方法
     */
    @Test
    public void test6() {
        BiPredicate<String, String> pre1 = (x, y) -> x.equals(y);
        System.out.println(pre1.test("aaa", "aaa"));
        System.out.println("----------------------------");
        BiPredicate<String, String> pre2 = String::equals;
        System.out.println(pre2.test("aaa", "bbb"));
    }
    /**
     * Function中的R apply(T t)方法
     * Element中的String getName()方法
     */
    @Test
    public void test7() {
        Employee emp = new Employee(1001,"张三", 18, 9999.99);
        Function<Employee, String> fun1 = e -> e.getName();
        System.out.println(fun1.apply(emp));
        System.out.println("----------------------------");
        Function<Employee, String> fun2 = Employee::getName;
        System.out.println(fun2.apply(emp));
    }

}
