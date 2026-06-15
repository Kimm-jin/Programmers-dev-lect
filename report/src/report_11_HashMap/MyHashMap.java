package report_11_HashMap;
/*
    해시 함수	키를 배열 인덱스로 변환
    버킷 배열	데이터를 담는 배열(bucket)
    충돌(collision)과 체이닝	같은 인덱스에 여러 키 → 연결 리스트로 해결
    O(1)의 원리	인덱스로 바로 접근하는 빠른 저장/조회
    순서가 없는 이유	인덱스가 해시값으로 정해지기 때문
 */


public class MyHashMap {
    static class Node {
        String key;
        String value;
        Node next;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] buckets;
    private int capacity = 16;
    private int size = 0;
    private static final double LOAD_FACTOR = 0.75;

    public MyHashMap() {
        buckets = new Node[capacity];
    }

    private int getIndex(String key) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    public void resize() {
        Node[] curBuckets = buckets;
        capacity *= 2;
        buckets = new Node[capacity];
        for (int i = 0; i < curBuckets.length; i++) {
            Node cur = curBuckets[i];
            while (cur != null) {
                Node n = cur.next;
                int idx = getIndex(cur.key);
                cur.next = buckets[idx];
                buckets[idx] = cur;

                cur = n;
            }
        }
    }

    public String[] keySet(){
        String[] keys = new String[size];
        int index=0;
        for (int i = 0; i < buckets.length; i++) {
            for(Node n = buckets[i]; n!=null; n=n.next){
                keys[index++]=n.key;
            }
        }
        return keys;
    }
    public String[] values(){
        String[] values = new String[size];
        int index=0;
        for (int i = 0; i < buckets.length; i++) {
            for(Node n = buckets[i]; n!=null; n=n.next){
                values[index++]=n.value;
            }
        }
        return values;
    }

    public void put(String key, String  value) {
        // 인덱스 계산
        int idx = getIndex(key);

        for (Node n = buckets[idx]; n != null; n = n.next) {
            if (n.key.equals(key)) { // 같은 키 확인
                n.value = value; // 값 갱신
                return;
            }
        }
        if (capacity * LOAD_FACTOR <= size) {
            resize();
            idx = getIndex(key); // 리사이즈 됐으면 다시 계산
        }


        Node newNode = new Node(key, value);
        newNode.next = buckets[idx];
        buckets[idx] = newNode;
        size++;
        return;
    }

    public String get(String key) {
        int idx = getIndex(key);

        for (Node n = buckets[idx]; n != null; n = n.next) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(String key) {
        int idx = getIndex(key);
        for (Node n = buckets[idx]; n != null; n = n.next) {
            if (n.key.equals(key)) return true;
        }
        return false;
    }

    public String  remove(String key) {
        int idx = getIndex(key);
        Node cur = buckets[idx];
        Node prev = null;
        while (cur != null) {
            if (cur.key.equals(key)) {
                if (prev == null) buckets[idx] = cur.next;
                else {
                    prev.next = cur.next;
                }
                size--;
                return cur.value;
            }
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    public void printBuckets() {
        for (int i = 0; i < buckets.length; i++) {
            System.out.printf("buckets[" + i + "] : ");
            Node n = buckets[i];
            if (n == null) {
                System.out.println("null");
                continue;
            }
            while (n != null) {
                System.out.print(n.key + " : " + n.value);
                if (n.next != null) System.out.printf(" -> ");
                n = n.next;
            }

            System.out.println(" -> null");
        }
    }

}
