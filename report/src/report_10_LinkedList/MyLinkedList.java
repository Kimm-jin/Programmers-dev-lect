package report_10_LinkedList;

// add(

public class MyLinkedList {
    static class Node{
        Node prev;
        String data;
        Node next;
        public Node(String data){this.data=data; }
    }
    private Node head,tail;
    private int size;
// head tail은 처음과 끝current
// 그 다음 노드인지?
    public void add(String data){
        Node newNode = new Node(data);
        if(head==null){ // 첫노드
            head=newNode;
            tail=newNode;
        }else{
            tail.next=newNode;
            newNode.prev=tail;
            tail=newNode;
        }
        size++;
    }
    public void insert(int index, String data){ // 삽입
        chkIndexInsert(index);

        if(index==0){ // head에 삽입
            add(data);
            return;
        }
        if(index==size){// tail에 삽입
            add(data);
            return;
        }


        Node nextNode = getNode(index);
        Node prevNode = nextNode.prev;
        Node newNode = new Node(data);

        newNode.prev = prevNode;
        newNode.next = nextNode;
        prevNode.next = newNode;
        nextNode.prev = newNode;

        size++;
    }
    public String get(int index){return getNode(index).data;}
    public void set(int index, String data){ getNode(index).data=data; }
    public void remove(int index){
        Node target = getNode(index); // 삭제할 노드
        //Node prevNode = target.prev;
        //Node nextNode = target.next;

        // 노드가 1개
        // head, tail
        // 중간노드
        if(size==1){
            head=tail=null;
        }else if(target==head){ // head
             head=head.next;
             head.prev=null;
        }else if(target==tail){ // tail
            tail=tail.prev;
            tail.next=null;
        }else{ // 중간노드
            Node prevNode = target.prev;
            Node nextNode = target.next;
            prevNode.next=nextNode;
            nextNode.prev=prevNode;
        }
        target.prev=null;
        target.next=null;
        size--;

    }
    public boolean contains(String data){
        Node current = head;
        while (current!=null){
            if(current.data.equals(data))return true;
            current=current.next;
        }
        return false;
    }
    public void clear(){
        head=tail=null;
        size=0;
    }
    public int size(){return size;}

    private void chkIndex(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("index = +" +index + ", size = "+size);
        }
    }
    private void chkIndexInsert(int index){
        if(index<0||index>size){
            throw new IndexOutOfBoundsException("index = +" +index + ", size = "+size);
        }
    }
    private Node getNode(int index){
        chkIndex(index);
        Node current = head;
        for(int i=0; i<index; i++){
            current = current.next;
        }
        return current;
    }

    public void printLinks() {
        Node cur = head;

        while (cur != null) {
            String p = (cur.prev == null) ? "null" : cur.prev.data;
            String n = (cur.next == null) ? "null" : cur.next.data;

            System.out.print("[" + p + " <- " + cur.data + " -> " + n + "] ");
            cur = cur.next;
        }

        System.out.println();
    }
}
