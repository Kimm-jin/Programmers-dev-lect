package report_09_ArrayList;

// - add(E e) : 리스트에 요소를 추가한다.
// - add(int index, E e) : 인덱스에 요소를 추가한다.
// - set(int index, E e) : 인덱스에 요소를 변경한다.
// - get(int index) : 인덱스에 있는 요소를 반환한다.
// - remove(int index) : 인덱스에 있는 요소를 제거합니다.
// - size() : 리스트의 요소 갯수를 반환한다.
// - contains(Object o) : 리스트에 특정 요소가 포함되어 있는지 확인한다.
// - clear() : 리스트의 모든 요소를 제거한다.
// - grow() : 배열 길이를 늘린다. * 2
// - capacity() : 배열 전체크기 반환.

public class start {
    public static void main(String[] args) {
        MyArrayList arrayList = new MyArrayList();
//        arrayList.remove(1);
        arrayList.add("one");
        arrayList.add(2,"two");
        arrayList.add("three");
        arrayList.add("4");
        arrayList.set(1,"2");
        for(int i=0; i<3; i++) System.out.println(arrayList.get(i));
        arrayList.contains("2");
        System.out.println(arrayList.size());
        arrayList.remove(0);
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        System.out.println(arrayList.capacity());
        System.out.println(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("arr[" +i+"] = " + arrayList.get(i));
        }
        System.out.println();
        arrayList.clear();
        System.out.println(arrayList.size());

    }
}
