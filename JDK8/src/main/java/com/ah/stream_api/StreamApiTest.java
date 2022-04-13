package com.ah.stream_api;

import com.ah.lambda.method_quote.Employee;
import com.ah.lambda.method_quote.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <p>
 *  1、Stream关注的是对数据的运算，与CPU密切相关，所以Stream操作的效率比较高，但是Stream操作的结果是不可预测的，
 *      集合关注的是数据的存储，与内存打交道
 *  2、
 *      1）、Stream 自己不会存储元素
 *      2）、Stream 不会改变源对象，相反，他们会返回一个持有结果的新对象
 *      3）、Stream 操作是延迟的。这意味着他们会等到需要结果的时候才执行
 *  3、Stream的执行流程
 *      1）、Stream的实例化
 *      2）、中间操作
 *      3）、终止操作
 *  4、说明：
 *      1）、一个中间操作链，对数据源的数据进行处理
 *      2）、一旦执行终止操作，就不能在继续添加中间操作，并且执行中间操作，产生结果，之后不会被使用
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/11 21:45
 */
public class StreamApiTest {

    /**
     * 通过集合创建Stream
     */
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
        // default Stream<E> stream() :返回一个顺序流
        Stream<Employee> stream = employees.stream();
        // default Stream<E> parallelStream() :返回一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();
    }

    /**
     * 通过数组创建Stream
     */
    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // 调用Arrays.stream(T[] array)方法
        IntStream stream = Arrays.stream(arr);
        Employee e = new Employee(1002, "李四", 18, 9999.99);
        Employee e2 = new Employee(1002, "李四", 18, 9999.99);
        Employee[] err = new Employee[]{e, e2};
        Stream<Employee> stream1 = Arrays.stream(err);
    }

    /**
     * 通过Stream的of()方法创建Stream
     */
    @Test
    public void test3() {
        Stream<String> stream = Stream.of("aa", "bb", "cc");
        Employee e = new Employee(1002, "李四", 18, 9999.99);
        Employee e2 = new Employee(1002, "李四", 18, 9999.99);
        Stream<Employee> e1 = Stream.of(e, e2);
    }
    /**
     * 通过Stream的iterate()方法创建无限流
     */
    @Test
    public void test4() {
        // 迭代
        // public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);
        // 生成
        // public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
