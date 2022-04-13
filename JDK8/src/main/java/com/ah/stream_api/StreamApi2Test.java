package com.ah.stream_api;

import com.ah.lambda.method_quote.Employee;
import com.ah.lambda.method_quote.EmployeeData;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  Stream的终止操作
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/11 22:05
 */
public class StreamApi2Test {
    /**
     * 匹配与查找
     */
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
        // allMatch(Predicate p) 检查是否匹配所有元素
            // 员工年龄是否都大于1岁
        System.out.println(employees.stream().allMatch(e -> e.getAge() > 1));
        System.out.println("----------------------");
        // anyMatch(Predicate p) 检查是否至少匹配一个元素
            // 是否存在员工工资大于1万
        System.out.println(employees.stream().anyMatch(e -> e.getSalary() > 2000));
        System.out.println("----------------------");
        // noneMatch(Predicate p) 检查是否没有匹配所有元素
            // 是否存在员工姓名姓"比"
        System.out.println(employees.stream().noneMatch(e -> e.getName().startsWith("比")));
        System.out.println("----------------------");
        // findFirst() 返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        System.out.println(first);
        System.out.println("----------------------");
        // findAny() 返回当前流中的任意元素
        Optional<Employee> any = employees.parallelStream().findAny();
        System.out.println(any);
        System.out.println("----------------------");
        // count() 返回流中元素的总个数
        long count = employees.stream().filter(e -> e.getSalary() > 2000).count();
        System.out.println(count);
        System.out.println("----------------------");
        // max(Comparator c) 返回流中最大值
            // 返回最高的工资
        System.out.println(employees.stream().max(Comparator.comparing(Employee::getSalary)));
        System.out.println("----------------------");
        // min(Comparator c) 返回流中最小值
            // 返回最低的工资
        System.out.println(employees.stream().min(Comparator.comparing(Employee::getSalary)));
        System.out.println("----------------------");
        // forEach(Consumer c) 依次对流中的每个元素执行操作
        employees.stream().filter(e -> e.getSalary() > 2000).forEach(System.out::println);
    }

    /**
     * 规约
     */
    @Test
    public void test2() {
        // reduce(T identity, BinaryOperator op) 将流中元素反复结合起来，得到一个值
            // 计算1-10的自然数之和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer reduce = list.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
        // reduce(BinaryOperator op) 将流中元素反复结合起来，得到一个值
            // 计算公司所有员工的工资之和
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
        System.out.println(salaryStream.reduce(0.0, Double::sum));
    }



    /**
     * 收集
     */
    @Test
    public void test3() {
        // collect(Collector c) 将流转换为其他形式，接受一个Collector接口实现，用于给Stream中的元素作汇总
            // 查找工资大于6000的员工，结果返回一个list或set
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> collect = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
