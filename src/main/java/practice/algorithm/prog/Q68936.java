package practice.algorithm.prog;

public class Q68936 {

  // 압축이 "확정"될 때마다 여기에 1씩 더한다 (count[0] = 0의 개수, count[1] = 1의 개수)
  private final int[] count = new int[2];

  // 한 블록을 재귀 처리한 결과: 이 블록 전체가 단일 값으로 압축 가능한지, 가능하다면 그 값은 무엇인지
  private record Result(boolean uniform, int value) {}

  public int[] solution(int[][] arr) {
    Result root = compress(0, 0, arr.length, arr);

    // TODO: 루트는 부모가 없어서 누구도 대신 카운트해주지 않는다.
    // root.uniform()이 true라면 여기서 직접 count[root.value()]++ 해줘야 한다.
    if (root.uniform()) {
      count[root.value()]++;
    }

    return count;
  }

  private Result compress(int x, int y, int size, int[][] arr) {
    // base case: 1x1 블록은 항상 압축 가능 (자기 자신 값으로)
    if (size == 1) {
      return new Result(true, arr[x][y]);
    }

    int half = size / 2;
    Result topLeft = compress(x, y, half, arr);
    Result topRight = compress(x, y + half, half, arr);
    Result bottomLeft = compress(x + half, y, half, arr);
    Result bottomRight = compress(x + half, y + half, half, arr);

    // TODO 1: 4개 자식이 전부 uniform이고, 값도 전부 동일하다면?
    //   -> 이 블록 전체가 압축 가능하다는 뜻. 아직 카운트하면 안 된다 (부모가 한 번 더 합쳐질 수도 있으니까).
    //   -> new Result(true, 그 공통 값) 을 리턴.
    if (topLeft.uniform() && topRight.uniform() &&
        bottomLeft.uniform() && bottomRight.uniform() &&
        topLeft.value() == topRight.value() &&
        bottomLeft.value() == bottomRight.value() &&
        topLeft.value() == bottomLeft.value()) {

      return new Result(true, topLeft.value());
    }

    // TODO 2: 그렇지 않다면 (자식 중 하나라도 uniform이 아니거나, 값이 다르다면)?
    //   -> 이 블록은 압축 불가. 압축이 여기서 "멈춘" 자식들을 지금 카운트해야 한다.
    //   -> 4개 자식(topLeft, topRight, bottomLeft, bottomRight) 중 uniform() == true인 것만
    //      count[해당 value]++ 해준다. (uniform이 아닌 자식은 이미 더 깊은 재귀에서 스스로 카운트를 마쳤다)
    //   -> new Result(false, 아무 값) 을 리턴.
    if (topLeft.uniform()) {
      count[topLeft.value()]++;
    }

    if (topRight.uniform()) {
      count[topRight.value()]++;
    }

    if (bottomLeft.uniform()) {
      count[bottomLeft.value()]++;
    }

    if (bottomRight.uniform()) {
      count[bottomRight.value()]++;
    }

    return new Result(false, arr[x][y]);
  }
}
