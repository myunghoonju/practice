package practice.algorithm;

public class Bsearch {

  public static void main(String[] args) {
    int[] ints = {9};
    System.out.println(search(ints, 9));
  }

  //todo: use recurrence as below
  int search(int[] arr, int target, int start, int end) {
    if (start > end) {
      return -1;
    }

    int mid = start + (end - start) / 2;

    if (arr[mid] == target) {
      return mid;
    }

    if (target < arr[mid]) {
      return search(arr, target, start, mid-1);
    }

    return search(arr, target, mid+1, end);
  }

  // nums are sorted asc, no duplicates
  static int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    if (nums.length == 1) {
      return nums[0] == target ? 0 : -1;
    }

    // find middle value
    int midIdx = (nums.length / 2) - 1;
    if (nums[midIdx] == target) {
      return midIdx;
    }

    // if small then middle find from left
    if (nums[midIdx] > target) {
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] == target) {
          return i;
        }
      }
    }

    // otherwise find from right
    for (int i = nums.length -1; i >= 0; i--) {
      if (nums[i] == target) {
        return i;
      }
    }

    return -1;
  }
}
