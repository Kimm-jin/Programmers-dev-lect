package report_13_TreeMap;

public class start {
    public static void main(String[] args) {
        MyTreeMap map = new MyTreeMap();
//        map.put("banana", 2);
//        map.put("apple", 1);
//        map.put("cherry", 3);
//        map.put("cherry", 4);
//
//
//        map.printSorted();          // [apple=1] [banana=2] [cherry=3]  ← 넣은 순서와 무관하게 정렬!
//        System.out.println();
//        System.out.println(map.get("banana"));   // 2
//        System.out.println(map.firstKey());      // apple (가장 작은 키)
//        System.out.println(map.lastKey());       // cherry (가장 큰 키)
//        System.out.println(map.size());
//        map.printSorted();

//        System.out.println(map.get("apple"));
//        System.out.println(map.remove("cherry"));

        // 자식 1개 삭제
//        map.put("banana", 2);
//        map.put("apple", 1);
//        map.put("avocado", 10);
//        map.put("cherry", 3);
//        map.printSorted();
//        System.out.println("remove apple = " + map.remove("apple"));
//        map.printSorted();
//        // 자식 0개 삭제
//        System.out.println("remove cherry = " + map.remove("cherry"));
//        map.printSorted();
        // 자식 2개 삭제
        map.put("5", 5);
        map.put("3", 3);
        map.put("7", 7);
        map.put("2", 2);
        map.put("4", 4);
        map.put("6", 6);
        map.put("8", 8);

        map.printSorted();
        System.out.println();
        System.out.println("size : "+map.size());
        System.out.println("remove 5 = " + map.remove("5"));
        map.printSorted();
        System.out.println();
        System.out.println("size : "+map.size());
    }
}
