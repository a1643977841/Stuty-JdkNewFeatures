package com.ah.test;

/**
 * <p>
 *  在jdk9中接口可以定义私有方法，但是在jdk8中不能定义私有方法
 *  在jdk9中接口可以定义默认方法，但是在jdk8中不能定义默认方法
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/13 21:24
 */
public interface MyInterface {
    /**
     * 默认方法
     */
    default void defaultMethod() {
        System.out.println("defaultMethod");
    }

    /**
     * 私有方法
     */
    private void privateMethod() {
        System.out.println("privateMethod");
    }

    /**
     * 静态方法
     * 接口中的静态方法，只能用这个接口去调用，不能用实现类去调用
     */
    static void staticMethod() {
        System.out.println("staticMethod");
    }
}
