package practice.algorithm.prog;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Q92341 {

  //시간계산
  //누적 시간에 따라 주차요금 계산
  //자동차별 주차 시간 누적
  int[] solution(int[] fees, String[] records) {
    int[] answer = {};
    FeeTable feeTable = new FeeTable(fees[0], fees[1], fees[2], fees[3]);
    Map<String, Car> cars = new HashMap<>();

    for (String record : records) {
      String[] el = record.split(" ");
      int minute = minute(el[0]);
      String number = el[1];
      boolean in = el[2].equals("IN");

      if (!cars.containsKey(number)) {
        cars.put(number, new Car(number, feeTable));
      }

      Car car = cars.get(number);
      if (in) {
        car.in(minute);
      } else {
        car.out(minute);
      }
    }

    int end = minute("23:59");
    for (Car c : cars.values()) {
      c.out(end);
    }

    return cars.values()
               .stream()
               .sorted(Comparator.comparing(c -> c.number))
               .mapToInt(Car::cost)
               .toArray();
  }

  private int minute(String time) {
    int hour = Integer.parseInt(time.substring(0, 2));
    int minute = Integer.parseInt(time.substring(3));
    return hour * 60 + minute;
  }

  private class Car {

    private final String number;

    private final FeeTable feeTable;

    private int inTime = -1;

    private int totalTime = 0;

    public Car(String number, FeeTable feeTable) {
      this.number = number;
      this.feeTable = feeTable;
    }

    void in(int inTime) {
      this.inTime = inTime;
    }

    void out (int outTime) {
      if (this.inTime == -1) {
        return;
      }

      this.totalTime += outTime - inTime;
      this.inTime = -1;
    }

    public int cost() {
      return feeTable.cost(totalTime);
    }
  }

  private class FeeTable {

    private final int baseTime;

    private final int baseFee;

    private final int unitTime;

    private final int unitFee;

    public FeeTable(int baseTime,
                    int baseFee,
                    int unitTime,
                    int unitFee) {
      this.baseTime = baseTime;
      this.baseFee = baseFee;
      this.unitTime = unitTime;
      this.unitFee = unitFee;
    }

    public int cost(int time) {
      int fee = baseFee;
      time -= baseTime;

      while (time > 0) {
        fee = fee + unitFee;
        time -= unitTime;
      }

      return fee;
    }
  }
}
