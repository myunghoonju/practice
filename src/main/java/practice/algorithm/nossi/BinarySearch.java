package practice.algorithm.nossi;

public class BinarySearch {

  public int search1(int[] nums, int target) {
    int l = 0;
    int r = nums.length - 1;
    while (l <= r) {
      int mid = l + (r - l) / 2;
      if (nums[mid] == target) {
        return mid;
      }

      if (nums[mid] < target) {
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }

    return -1;
  }

   public int search2(int[] nums, int target) {
     return binarySearch(nums, 0, nums.length -1, target);
   }

    private int binarySearch(int[] nums, int l, int r, int target) {
     if (l > r) {
       return -1;
     }

     int mid = l + (r - l) / 2;
     if (nums[mid] == target) {
       return mid;
     }

     if (nums[mid] < target) {
       return binarySearch(nums, mid + 1, r, target);
     } else {
       return binarySearch(nums, l, mid - 1, target);
     }
   }
}
