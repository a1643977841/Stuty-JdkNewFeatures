package com.ah.stream_api;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * <p>
 *
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/15 22:44
 */
public class ForkJoin {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 多线程计算 pool线程池
        ForkJoinPool pool = new ForkJoinPool();
        SumRecursiveTask task = new SumRecursiveTask(1L, 9999999999L);
        Long invoke = pool.invoke(task);
        long end = System.currentTimeMillis();
        System.out.println(invoke);
        System.out.println("消耗时间: " + (end - start));
        System.out.println("对比for循环计算------------");
        long start1 = System.currentTimeMillis();
        long sum = 0;
        for (long i = 1; i <= 9999999999L; i++) {
            sum += i;
        }
        long end1 = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println("for循环消耗时间: " + (end1 - start1));
    }
}

/**
 * 创建一个求和人物
 */
class SumRecursiveTask extends RecursiveTask<Long> {
    /**
     * 是否拆分的临界值
     */
    private static final long THRESHOLD = 30000L;
    /**
     * 起始值
     */
    private final long start;
    /**
     * 结束值
     */
    private final long end;

    public SumRecursiveTask(long start, long end) {
        this.start = start;
        this.end = end;
    }
    /**
     * 计算
     * @return nick
     */
    @Override
    protected Long compute() {
        long length = end - start;
        if (length < THRESHOLD) {
            // 计算
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum+=i;
            }
            return sum;
        } else {
            // 拆分
            long middle = (start + end) / 2;
            SumRecursiveTask left = new SumRecursiveTask(start, middle);
            left.fork();
            SumRecursiveTask right = new SumRecursiveTask(middle + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }
}


