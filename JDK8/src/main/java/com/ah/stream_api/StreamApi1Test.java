package com.ah.stream_api;

import com.ah.lambda.method_quote.Employee;
import com.ah.lambda.method_quote.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 *  Stream的中间操作
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/11 22:05
 */
public class StreamApi1Test {
    /**
     * 筛选与切片
     */
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
        // filter(Predicate p): 接受lambda，从流中排除某些元素
        Stream<Employee> stream = employees.stream();
        stream.filter((e) -> e.getAge() > 25).forEach(System.out::println);
        // limit(n): 截断流，使其元素不超过给定数量
        stream = employees.stream();
        stream.limit(3).forEach(System.out::println);
        // skip(n): 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
        stream = employees.stream();
        stream.skip(3).forEach(System.out::println);
        // distinct(): 筛选，通过流所生成元素的hashCode()和equals()去除重复元素
        stream = employees.stream();
        stream.distinct().forEach(System.out::println);
    }

    /**
     * 映射
     */
    @Test
    public void test2() {
        // map(Function f): 接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
        List<String> list = Arrays.asList("aa", "bb", "cc");
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        // 练习1
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().map(Employee::getName).filter(name -> name.length() > 3).forEach(System.out::println);
        System.out.println("----------");
        // 练习2
        Stream<Stream<Character>> streamStream = list.stream().map(StreamApi1Test::fromStringToStream);
        streamStream.forEach(s -> s.forEach(System.out::println));
        // flatMap(Function f): 接受一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
        System.out.println("-----flatMap-----");
        list.stream().flatMap(StreamApi1Test::fromStringToStream).forEach(System.out::println);
    }

    public static Stream<Character> fromStringToStream(String str) {
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    /**
     * 排序
     */
    @Test
    public void test3() {
        // sorted(): 自然排序
        List<Integer> list = Arrays.asList(12, 44, 65, 34, 87, 0, -98, 7);
        Stream<Integer> stream = list.stream();
        stream.sorted().forEach(System.out::println);
        System.out.println("--------------------");
        // sorted(Comparator c): 自定义排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted(Comparator.comparingInt(Employee::getAge)).forEach(System.out::println);
        System.out.println("--------------------");
        employees.stream().sorted((e1, e2) -> {
            int ageValue = Integer.compare(e1.getAge(), e2.getAge());
            if (ageValue == 0) {
                return ageValue;
            } else {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);
    }
}
