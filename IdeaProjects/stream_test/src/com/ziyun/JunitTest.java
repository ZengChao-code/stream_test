package com.ziyun;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JunitTest {

    @Test
    public void Test1(){
        /**
         * 获取String集合中的最长元素
         */
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println("其中最长的字符串是："+max.get());
    }

    @Test
    public void Test2(){
        /**
         * 获取Integer集合中的最大元素（排序）
         */
        List<Integer> list = Arrays.asList(7, 6, 9, 4, 11, 6);
//        自然排序
        Optional<Integer> ziran = list.stream().max(Integer::compareTo);
//        自定义排序
        Optional<Integer> zidingyi = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("自然排序下，最大值为："+ziran);
        System.out.println("自定义排序下，最大值为："+zidingyi);
    }

    @Test
    public void Test3(){
        /**
         * 计算Integer集合中大于6的元素的个数。
         */
        List<Integer> list = Arrays.asList(7, 6, 4, 8, 2, 11, 9);
        long count = list.stream().filter((x) -> x > 6).count();
        System.out.println("集合中大于6的元素的个数为："+count);
    }

    @Test
    public void Test4(){
        /**
         * 英文字符串数组的元素全部改为大写。整数数组每个元素+3
         */
        String[] strArr = { "abcd", "bcdd", "defde", "fTr" };
        List<String> stringList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("字符串每个元素都大写："+stringList);

        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> integerList = intList.stream().map(x->x+3).collect(Collectors.toList());
        System.out.println("每个元素都加三："+integerList);
    }

    @Test
    public void Test5(){
        /**
         * 将两个字符数组合并成一个新的字符数组
         */
        List<String> list = Arrays.asList("m,k,l,a","1,3,5,7");
        List<String> collect = list.stream().flatMap(s -> {
//            将每个元素转换成一个流
            String[] split = s.split(",");
            Stream<String> stream = Arrays.stream(split);
            return stream;
        }).collect(Collectors.toList());
        System.out.println("经过处理前的集合"+list);
        System.out.println("经过处理后的集合"+collect);
    }

    @Test
    public void Test6(){
        /**
         * 求Integer集合的元素之和、乘积和最大值
         */
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
//        求和的三种方式
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        Integer sum3 = list.stream().reduce(0, Integer::sum);
//        求乘积
        Optional<Integer> reduce = list.stream().reduce((x, y) -> x * y);
//        求最大值的两种方式
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        Integer max2 = list.stream().reduce(1, Integer::max);
        System.out.println("求和："+sum.get()+","+sum2.get()+","+sum3);
        System.out.println("求乘积："+reduce.get());
        System.out.println("最大值："+max.get()+","+max2);
    }

    @Test
    public void Test7(){
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i->i%2==0).distinct().forEach(System.out::println);
        System.out.println("---------");
        numbers.stream().limit(3).forEach(System.out::println);
        System.out.println("---------");
        numbers.stream().skip(3).forEach(System.out::println);
        System.out.println("---------");
    }
}
