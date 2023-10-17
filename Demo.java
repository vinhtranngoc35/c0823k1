import model.*;

public class Demo {


    public static void main(String[] args) {
      MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

      myLinkedList.addFirst(3);
      myLinkedList.addFirst(5);
      myLinkedList.addFirst(7);
      myLinkedList.printList();
    }

    public static long maximumTripletValue(int[] nums) {
        long max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    max = Math.max(max, (long) (nums[i] - nums[j]) * nums[k]);
                }
            }
        }
        return max;
    }


}