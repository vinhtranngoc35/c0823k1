package model;

import java.util.Arrays;

public class MyArrayList<E> {
    private static int DEFAULT_CAPACITY = 10;
    private E[] array;

    private int size;

    public MyArrayList() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }
    // size = 0
    //
    public void ensureCapacity(){
        if(size == array.length){
            array = Arrays.copyOf(array, array.length * 2);
        }
    }
    public void add(E e){
        ensureCapacity();
        array[size] = e;
        size++;
    }

    public void add(E e, int index){
        ensureCapacity();
//        if(index > size || index < 0) throw new IndexOutOfBoundsException(index + " index khong hop le");
        // 1 ,3,4
        // 1, 2,
        for (int i =  size; i > index  ; i++) {
            array[i + 1] = array[i];
        }
        array[index] = e;
        size++;
    }

    public E get(int i){
        if(i >= size || i < 0) throw new IndexOutOfBoundsException(i + " index khong hop le");
        return array[i];
    }

    public int size(){
        return size;
    }


}