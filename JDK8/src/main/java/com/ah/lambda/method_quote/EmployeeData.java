package com.ah.lambda.method_quote;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Liu hao
 * @version 1.0
 * @date 2022/4/10 0:05
 */
public class EmployeeData {

    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1001, "马化腾", 34, 6000.38));
        employees.add(new Employee(1002, "马云", 12, 222.321));
        employees.add(new Employee(1003, "刘强东", 15, 224.324));
        employees.add(new Employee(1004, "雷军", 19, 60.35));
        employees.add(new Employee(1005, "比尔盖茨", 22, 601.28));
        employees.add(new Employee(1006, "任正非", 28, 332.99));
        return employees;
    }
}
