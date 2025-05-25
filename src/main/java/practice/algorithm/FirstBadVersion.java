package practice.algorithm;

public class FirstBadVersion {

  public int firstBadVersion(int n) {
    int low = 0;
    int high = n;
    // loop while low < high
    while (low <= high) {
      // find median
      int mid = low + (high - low) / 2;
      // less than median is false then return median
      if (isBadVersion(mid) && !isBadVersion(mid - 1)) {
        return mid;
      } else if (!isBadVersion(mid)) {
        // if not then move to right (low = median + 1)
        low = mid + 1;
      } else {
        // else high = median
        high = mid;
      }
    }

    return 0;
  }

  /*
  The isBadVersion API is defined in the parent class VersionControl.
  boolean isBadVersion(int version);
  */
  // sudo code
  public boolean isBadVersion(int n) {
    return true;
  }
}
