package practice.algorithm;

import java.util.Arrays;

public class Meetings {

  public static void main(String[] args) {
    int[][] a = {{0,30},{5,10},{15,20}};
    int[][] b = {{7,10},{2,4}};
    new Meetings().canAttendMeetings2(b);
  }

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
  public boolean canAttendMeetings2(int[][] intervals) {
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
  private boolean overlap(int[] interval1, int[] interval2) {
    return (interval1[0] >= interval2[0] && interval1[0] < interval2[1])
            || (interval2[0] >= interval1[0] && interval2[0] < interval1[1]);
  }
}
