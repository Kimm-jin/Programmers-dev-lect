package report_13_TreeMap;

import report_12_Tree.MyTree;

/*
    이진 검색 트리(BST)	왼쪽 < 부모 < 오른쪽 구조로 저장
    키 비교	compareTo로 좌/우 방향 결정
    중위 순회 = 정렬	왼→자신→오른 순서로 방문하면 정렬되어 나옴
    O(log n)의 원리	트리 높이만큼만 내려가면 됨
    균형의 중요성	치우치면 O(n)이 되는 이유
 */
public class MyTreeMap {
    static class Node {
        String key;
        Integer value;
        Node left, right;

        public Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size = 0;

    public void put(String key, Integer value) { // add
        root = putNode(root, key, value);
    }

    private Node putNode(Node node, String key, Integer value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = putNode(node.left, key, value); // 왼쪽
        else if (cmp > 0) node.right = putNode(node.right, key, value); // 오른쪽
        else node.value = value; // 같으면 값 갱신
        return node;

    }


    public Integer get(String key) {
        Node n = root;
        while (n != null) {
            int cmp = key.compareTo(n.key);
            if (cmp < 0) n = n.left;
            else if (cmp > 0) n = n.right;
            else return n.value;
        }
        return null;
    }

    public void printSorted() {
        System.out.print("중위 순회 : ");
        inOrder(root);
    }

    public void inOrder(Node node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.print("[" + node.key + ", " + node.value + "] ");
        inOrder(node.right);
    }

    public int size() {
        return size;
    }

    public boolean containsKey(String key) {
        Node n = root;

        while (n != null) {
            int cmp = key.compareTo(n.key);
            if (cmp < 0) n = n.left;
            else if (cmp > 0) n = n.right;
            else return true;
        }
        return false;
    }

    public String firstKey() {
        Node n = root;
        if (n == null) return null;

        while (n.left != null) n = n.left;
        return n.key;
    }

    public String lastKey() {
        Node n = root;
        if (n == null) return null;
        while (n.right != null) n = n.right;
        return n.key;
    }

    public Integer remove(String key) {
        Integer oldVal = get(key);
        if (oldVal == null) return null;
        root = removeNode(root, key);
        size--;
        return oldVal;
    }

    private Node removeNode(Node node, String key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = removeNode(node.left, key);
        else if (cmp > 0) node.right = removeNode(node.right, key);
        else {
            if (node.left==null)return node.right;
            else if(node.right==null)return node.left;
            else{
                Node succ = node.right;
                while(succ.left!=null)succ=succ.left;
                node.key = succ.key;
                node.value= succ.value;
                node.right = removeNode(node.right, succ.key);
            }
        }
        return node;

    }


}
