package com.ah.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

/**
 * <p>
 *
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/13 21:28
 */
public class MyInterfaceTest implements MyInterface {

    public static void main(String[] args) {
        MyInterface.staticMethod();
        // 钻石操作符的升级 jdk8中Comparator后面的泛型参数必须填写
        Comparator<Object> comparator = new Comparator<>() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        // try 资源会自动的关闭，在jdk8中一般是在finally中关闭
        // 要求自动关闭的资源必须在try的小括号中
        try(InputStreamReader reader = new InputStreamReader(System.in)){
            char[] chars = new char[20];
            int len;
            if((len = reader.read(chars)) != -1){
                String s = new String(chars, 0, len);
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
