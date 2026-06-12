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


public class MyArrayList {
    private String[] arr;
    private int size;       // 현재 저장된 개수
    MyArrayList(){arr = new String[5]; size=0;}

    public void add(String data) {
//        if(size==capacity()) grow();
//        chkAddIndex(size);
//        arr[size++]=data;
        add(size,data);
    }
    // index는 0부터
    public void add(int index, String data) {
        if(size==capacity()) grow();
        chkAddIndex(index);
        for(int i=size; i>index; i--)arr[i]=arr[i-1];
        arr[index]=data;
        size++;
    }

    public String get(int index) {
        chkIndex(index);
        return arr[index];
    }
    public void set(int index, String data) {
        chkIndex(index);
        arr[index]=data;
    }

    public void remove(int index) {
        chkIndex(index);
        for(int i=index; i<size-1; i++) arr[i]=arr[i+1];
        arr[--size]=null;
    }

    public int size() {
        return size;
    }

    public int capacity(){
        return arr.length;
    }

    public void clear() {
        arr=null; size=0;
    }

    public void grow() {
        String[] tmp = new String[arr.length*2];
        for(int i=0; i< arr.length; i++) tmp[i]=arr[i];
        arr = tmp;
    }

    public boolean contains(String data){
        for(int i=0; i<size; i++){
            if(arr[i].equals(data))return true;
        }return false;
    }

    public void chkIndex(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("index = +" +index + ", size = "+size);
        }
    }
    public void chkAddIndex(int index){ // capacity==size
        if(index<0||index<size){
            throw new IndexOutOfBoundsException("index = +" +index + ", size = "+size);
        }
    }


}
