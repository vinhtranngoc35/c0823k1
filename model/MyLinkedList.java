package model;

public class MyLinkedList<T> {
    private Node<T> head;



    public void addFirst(T value){
        if(head == null){
            head = new Node<>(value);
            return;
        }
        var temp = new Node<>(value);
        temp.next = head;
        head = temp;
    }

    public void printList(){
        var temp = head;
        while (temp != null){
            System.out.print(temp.value + ", ");
            temp = temp.next;
        }
    }


    public static class Node<T>{
        public Node<T> next;

        public T value;

        public Node(T value) {
            this.value = value;
        }
    }
}