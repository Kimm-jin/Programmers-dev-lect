package org.example.springboot.report_28_스트림;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static void main() {
        System.out.println("===== 1. 스트림 만들고 전체 출력 (forEach) =====");
        List<Product> products =new ArrayList<>(Arrays.asList(
                new Product("상품1",2500),
                new Product("상품2",1500),
                new Product("상품3",900),
                new Product("상품4",2000),
                new Product("상품5",500)
        ));
        products.stream().forEach(a -> System.out.println(a.getName() + " : "+a.getPrice()+"원"));
        System.out.println();

        System.out.println("===== 2. filter: 1000원 이상만 =====");
        products.stream().filter(a->a.getPrice()>=1000).forEach(a-> System.out.println(a.getName() + " : "+a.getPrice()+"원"));
        System.out.println();

        System.out.println("===== 3. map: 이름만 뽑기 =====");
        products.stream().map(a->a.getName()).forEach(a -> System.out.println(a));
        System.out.println();

        System.out.println("===== 4. map vs flatMap (주문 속 상품 목록) =====");
        List<Order> orders = new ArrayList<>(Arrays.asList(
                new Order(1, Arrays.asList("상품1","상품2","상품3")),
                new Order(2, Arrays.asList("상품4","상품5")),
                new Order(3, Arrays.asList("상품6"))
        ));
        List<List<String>> Map = orders.stream().map(a ->a.getItems())
                        .collect(Collectors.toList());
        System.out.println("Map : "+Map);
        System.out.println();

        List<String> FlatMap = orders.stream().flatMap(a->a.getItems().stream())
                        .collect(Collectors.toList());
        System.out.println("FlatMap : "+FlatMap);
        System.out.println();

        System.out.println("===== 5. filter + map + collect 1000원 이상 상품 이름 리스트 =====");
        List<String> list = products.stream().filter(a -> a.getPrice() >= 1000)
                        .map(a -> a.getName()).collect(Collectors.toList());
        System.out.println(list);
        System.out.println();

        System.out.println("===== 6. 통계 =====");
        long count = products.stream().filter(a->a.getPrice()>=1000)
                .count();
        int sum = products.stream().mapToInt(a->a.getPrice()).sum();
        double avg = products.stream().mapToInt(a-> a.getPrice()).average().getAsDouble();
        List<String> sort = products.stream().sorted((a, b)->a.getPrice() - b.getPrice())
                .map(a->a.getName()).collect(Collectors.toList());

        System.out.println("count : "+count);
        System.out.println("sum : "+sum);
        System.out.println("avg(double) : "+avg);
        System.out.println("sort : "+sort); // 5 3 2 4 1


        List<String> one = products.stream().filter(a->a.getPrice()<=500)
                .map(a->a.getName()).collect(Collectors.toList());
        System.out.println("500이하"+one);

        List<String > two = products.stream().map(a-> a.getName() + ": " + a.getPrice()+"원")
                .collect(Collectors.toList());
        System.out.println(two);

        List<String> sort2 = products.stream().sorted((a, b)->b.getPrice() - a.getPrice()).limit(1)
                .map(a->a.getName()).collect(Collectors.toList());
        System.out.println(sort2);

    }
}
