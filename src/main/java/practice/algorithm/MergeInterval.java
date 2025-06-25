package practice.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeInterval {

  public int[][] insert(int[][] intervals,
                        int[] newInterval) {
    int i = 0;
    int n = intervals.length;
    List<int[]> response = new ArrayList<>();

   Arrays.stream(intervals[0]).forEach(it -> System.out.print(it + " "));
    //Case 1. The current interval ends before the new interval starts.
    while (i < n && intervals[i][1] < newInterval[0]) {
      response.add(intervals[i]);
      i++;
    }

    //Case 2. There is an overlap, and the intervals need merging.
    while (i < n && intervals[i][0] <= newInterval[1]) {
      newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
      newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
      i++;
    }

    response.add(newInterval);

    //Case 3. The current interval starts after the new interval ends.
    while (i < n) {
      response.add(intervals[i]);
      i++;
    }

    return response.toArray(new int[response.size()][]);
  }

  public static void main(String[] args) {
    MergeInterval mergeInterval = new MergeInterval();
    int[][] intervals = new int[][]{{1,3},{6,9}};
    int[] newinterval = {2, 5};
    int[][] insert = mergeInterval.insert(intervals, newinterval);
    for (int i = insert.length - 1; i >= 0; i--) {
      System.out.println(Arrays.toString(insert[i]));
    }
  }
}
