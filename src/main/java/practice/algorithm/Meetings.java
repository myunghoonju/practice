package practice.algorithm;

import java.util.Arrays;

public class Meetings {

  public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    for (int i = 0; i < intervals.length -1; i++) {
      // when prev meeting is longer than next meeting starts
      if (intervals[i][1] > intervals[i+1][0]) {
        return false;
      }
    }

    return true;
  }

  // Approach 1: Brute Force
  private boolean canAttendMeetings2(int[][] intervals) {
    for (int i = 0; i < intervals.length - 1; i++) {
      for (int j = i + 1; j < intervals.length; j++) {
        if (overlap(intervals[i], intervals[j])) {
          return false;
        }
      }
    }
    return true;
  }

  /*
  * case 1. first meeting starts later and second meeting is longer
  * case 2. second meeting starts later and first meeting is longer
  * */
  private boolean overlap(int[] a, int[] b) {
    return a[0] >= b[0] && a[0] < b[1] ||
           a[0] <= b[0] && a[1] > b[1];
  }
}
