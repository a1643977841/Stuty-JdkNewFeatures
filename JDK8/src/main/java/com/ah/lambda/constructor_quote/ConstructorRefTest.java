package com.ah.lambda.constructor_quote;

import com.ah.lambda.method_quote.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>
 *  构造器引用与数组引用
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/10 22:03
 */
public class ConstructorRefTest {
    /**
     * 构造器引用
     * Supplier中的T get()方法
     */
    @Test
    public void test1() {
        System.out.println("-----普通写法-----");
        Supplier<Employee> sup = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(sup.get());
        System.out.println("-----lambda 写法-----");
        Supplier<Employee> sup1 = () -> new Employee();
        System.out.println(sup1.get());
    }

    /**
     * Function中的T apply(T t)方法
     */
    @Test
    public void test2() {
        System.out.println("-----普通写法-----");
        Function<Integer, Employee> fun1 = new Function<Integer, Employee>() {
            @Override
            public Employee apply(Integer integer) {
                return new Employee(integer);
            }
        };
        System.out.println(fun1.apply(1));
        System.out.println("-----lambda 写法-----");
        Function<Integer, Employee> fun2 = Employee :: new;
        System.out.println(fun2.apply(2));
    }
    /**
     * BiFunction中的R apply(T t, U u)方法
     */
    @Test
    public void test3() {
        System.out.println("-----普通写法-----");
        BiFunction<Integer, String, Employee> fun1 = new BiFunction<Integer, String, Employee>() {
            @Override
            public Employee apply(Integer integer, String s) {
                return new Employee(integer, s);
            }
        };
        System.out.println(fun1.apply(1, "张三"));
        System.out.println("-----lambda 写法-----");
        BiFunction<Integer, String, Employee> fun2 = Employee::new;
        System.out.println(fun2.apply(1, "李四"));
    }

    /**
     * 数组引用
     * Function中的T apply(T t)方法
     */
    @Test
    public void test4() {
        Function<Integer, String[]> func1 = length -> new String[length];
        System.out.println(Arrays.toString(func1.apply(5)));

        Function<Integer, String[]> func2 = String[] :: new;
        System.out.println(Arrays.toString(func2.apply(10)));
    }

    /**
     * 实际
     */
    @Test
    public void test5() {
        List<String> list = new ArrayList<>();
        list.add("!23");
        list.forEach(System.out::println);
    }
}
