package practice.algorithm.prog;

public class Q68936 {
/**
 * 재귀 함수: solve(x, y, size) → (uniform, value) 반환
 *
 *   흐름:
 *   1. size == 1이면 → (true, arr[x][y]) 반환. 여기서는 카운트하지 않습니다.
 *   2. size > 1이면 → 4등분해서 재귀 호출 (half = size/2):
 *     - solve(x, y, half), solve(x, y+half, half), solve(x+half, y, half), solve(x+half, y+half, half)
 *     - 4개 결과가 전부 압축가능(uniform)이고 값도 전부 같다 → 이 블록도 압축가능 → (true, value) 반환하고 여기서도 카운트 안 함 (한 단계 위로 넘김)
 *     - 그렇지 않다 (4개 중 하나라도 압축불가, 또는 값이 다름) → 이 블록은 압축불가 → 4개 자식 중 압축가능(uniform)했던 애들만 count[value]++ 해줌 → (false, 0) 반환
 *   3. 최상위 호출(solve(0,0,N))이 끝났을 때 결과가 (true, value)라면 → 부모가 없으므로 이때 한 번 count[value]++ 해줘야 합니다.
 *
 *   핵심은: "압축가능(true)"은 위로 전달만 되고, "압축불가(false)"가 되는 그 순간, 그 직전에 압축가능했던 자식들이 카운트된다는 것입니다.

 * */

  private record Result (boolean uniform, int value) {};

  private int countZero = 0;
  private int countOne = 0;

  private Result result(int[][] arr, int x, int y, int size) {
    if (size == 1) {
      return new Result(true, arr[x][y]);
    }

    int half = size / 2;
    Result r1 = result(arr, x, y, half);
    Result r2 = result(arr, x, y + half, half);
    Result r3 = result(arr, x + half, y, half);
    Result r4 = result(arr, x + half, y + half, half);

    // TODO: r1~r4가 전부 isUniform이고 value가 같으면 → 이 블록도 uniform, 그대로 return
    if (r1.uniform() &&
        r2.uniform() &&
        r3.uniform() &&
        r4.uniform()) {
      int aValue = r1.value();
      if (aValue == r2.value() && aValue == r3.value() && aValue == r4.value()) {
        return r1;
      }
    }

    // TODO: 아니라면 → r1~r4 중 isUniform인 것들만 count[value]++ 해주고 return new Result(false, -1)
    Result[] children = {r1, r2, r3, r4};
    for (Result child : children) {
      if (child.uniform()) {
        if (child.value() == 0) {
          countZero++;
        } else {
          countOne++;
        }
      }
    }

    return new Result(false, -1);
  }

  public int[] solution(int[][] arr) {
    Result result = result(arr, 0, 0, arr.length);
    if (result.uniform()) {
      if (result.value() == 0) {
        countZero++;
      } else {
        countOne++;
      }
    }

    return new int[] {countZero, countOne};
  }
}
