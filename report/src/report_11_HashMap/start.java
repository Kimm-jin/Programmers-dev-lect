package report_11_HashMap;

public class start {
    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
//        myHashMap.put("a",100);
//        myHashMap.put("b",200);
//        myHashMap.put("c",300);
//        myHashMap.put("q",400);
//        myHashMap.put("r",300);
//        myHashMap.put("x",200);
//        myHashMap.put("t",100);

        System.out.println("a : "+myHashMap.get("a"));
        System.out.println("b : "+myHashMap.get("b"));
        System.out.println("c : "+myHashMap.get("b"));
        System.out.println("size : " + myHashMap.size());
        System.out.println();

        System.out.println("d : "+myHashMap.get("d"));
        System.out.println("d : "+myHashMap.containsKey("d"));
        System.out.println();

        System.out.println("containsKey");
        System.out.println(myHashMap.containsKey("a"));
        System.out.println(myHashMap.containsKey("b"));
        System.out.println();

        System.out.println("같은 Key put");
//        myHashMap.put("a",400);
        System.out.println("a : "+myHashMap.get("a"));
        System.out.println("size : "+myHashMap.size());
        System.out.println();

        System.out.println("remove");
        myHashMap.remove("b");
        System.out.println(myHashMap.containsKey("b"));
        System.out.println("size : "+myHashMap.size());

        myHashMap.printBuckets();
        System.out.println();

//        myHashMap.put("d",300);
//        myHashMap.put("e",200);
//        myHashMap.put("f",100);
//        myHashMap.put("g",400);
//        myHashMap.put("h",500);
//        myHashMap.put("i",600);
//        myHashMap.put("j",700);
//        myHashMap.put("k",800);
//        myHashMap.put("l",900);
        myHashMap.printBuckets();

        MyHashMap dict = new MyHashMap();

        dict.put("apple", "사과");
        dict.put("banana", "바나나");
        dict.put("computer", "컴퓨터");
        dict.put("dog", "강아지");

        System.out.println();

        System.out.println("keySet");
        String[] keys = dict.keySet();
        for (String key : keys) {
            System.out.println(key);
        }

        System.out.println();

        System.out.println("=== values ===");
        String[] values = dict.values();
        for (String value : values) {
            System.out.println(value);
        }


    }
}
