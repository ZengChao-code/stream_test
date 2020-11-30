package com.ziyun;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
//        创建六个员工，并将其存放在list集合中
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));

//        筛选员工中薪资高于8000的员工，并形成新的集合
        List<String> list = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("薪资高于8000的员工有："+list);

//        获取薪资最高的员工
        Optional<Person> max = personList.stream().max(Comparator.comparing(Person::getSalary));
        System.out.println("薪资最高的是："+max.get().getName());
        System.out.println("其薪资为："+max.get().getSalary());

//        将所有员工的薪资都增加1000
//        不会改变原来集合的方式
        List<Person> personList1 = personList.stream().map(person -> {
            Person person1 = new Person(person.getName(), 0, 0, null, null);
            person1.setSalary(person.getSalary() + 1000);
            return person1;
        }).collect(Collectors.toList());
        System.out.println("不会改变原来集合，personList:"+personList);
        System.out.println("不会改变原来集合，personList1:"+personList1);
//        会改变原来集合的方式
        List<Object> collect = personList.stream().map(x -> {x.setSalary(x.getSalary() + 1000);return x;})
                .collect(Collectors.toList());
        System.out.println("collect:"+collect);
        System.out.println("personList:"+personList);

//        求所有员工的工资之和和最高工资
        Integer reduce = personList.stream()
                .reduce(0, (sum, p) -> sum += p.getSalary(), (sum1, sum2) -> null);
        Integer reduce1 = personList.stream()
                .reduce(0, (max1, p) -> max1 > p.getSalary() ? max1 : p.getSalary(), (m1,m2)->null);
        System.out.println("所有员工的工资之和为："+reduce);
        System.out.println("最高工资是："+reduce1);
    }
}
