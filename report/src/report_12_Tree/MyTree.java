package report_12_Tree;

public class MyTree {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    private Node root;

//    public void insert(int data) {
////        루트가 비었으면 새 노드가 루트.
////        작으면 왼쪽, 크면 오른쪽으로 내려가 빈 자리에 단다.
//        Node newNode = new Node(data);
//        if (root == null) {
//            root = newNode;
//            return;
//        }
//        Node cur = root;
//
//        while (true) {
//            if (cur.data > data) {
//                if (cur.left == null) {
//                    cur.left = newNode;
//                    return;
//                }
//                cur = cur.left;
//            } else if(cur.data < data) {
//                if(cur.right==null){
//                    cur.right=newNode;
//                    return;
//                }
//                cur=cur.right;
//            }else return;
//        }
//    }

    public void insert(int value){
        root = insertNode(root, value);
    }
    private Node insertNode(Node node, int value){
        if(node==null)return new Node(value);
        if(node.data>value) node.left = insertNode(node.left,value);
        else if(node.data<value) node.right = insertNode(node.right,value);
        return node;
    }

    public void preOrder(){
        System.out.print("전위 순회 : ");
        preOrder(root);
    }
    private void preOrder(Node node){
        if(node==null)return;
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(){
        System.out.print("중위 순회 : ");
        inOrder(root);
    }
    private void inOrder(Node node){
        if(node==null)return;
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public void postOrder(){
        System.out.print("후위 순회 : ");
        postOrder(root);
    }
    private void postOrder(Node node){
        if(node==null)return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }



}
