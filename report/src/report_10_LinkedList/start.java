package report_10_LinkedList;

import java.util.LinkedList;
import java.util.List;



public class start {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add("1");
//        System.out.println(list.get(0));
//        list.remove(0);
        list.add("2");
        list.add("4");
        list.add("5");
        list.printLinks();
        list.insert(2,"3");
        list.printLinks();
        list.remove(2);
        list.remove(0);
        list.printLinks();
        System.out.println("get = "+list.get(0));
        list.set(1,"3");
        list.printLinks();
        System.out.println(list.size());
        list.clear();
        System.out.println(list.size());
        list.printLinks();
//
//            System.out.println(list.get(i));
//        }
    }
}
