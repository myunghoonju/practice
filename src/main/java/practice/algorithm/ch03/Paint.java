package practice.algorithm.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Paint {

    public static final int MAX_COLOR_NUMBER = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] data1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int seats = data1[0];
        int colours = data1[1];
        List<Painting> paintings = new ArrayList<>();

        for(int i = 0; i < colours; i++) {
            int[] data2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int left = data2[0];
            int right = data2[1];
            int colour = data2[2];
            paintings.add(new Painting(left, right, colour));
        }

        int[] colouredSeats = paintSeats(seats, colours, paintings);
        getSolution(colouredSeats);
    }

    static int[] paintSeats(int seats, int colours, List<Painting> paintings) {
        int[] seatArr = new int[seats];

        for (int j = 0; j < colours; j++) {
            Painting painting = paintings.get(j);
            int left = painting.left -1;
            int right = painting.right -1;
            for (int k = left; k <= right; k++) {
                seatArr[k] = painting.colour;
            }
        }
        return seatArr;
    }

    static void getSolution(int[] colouredSeats) {
        int[] seatTable = new int[MAX_COLOR_NUMBER];

        for (int index : colouredSeats) {
            seatTable[index] += 1;
        }

        int minC = colouredSeats[0];
        int maxC = colouredSeats[0];
        for (int l = 0; l < seatTable.length; l++) {
            if (seatTable[l] == 0) {
                continue;
            }

            if (seatTable[minC] > seatTable[l]) {
                minC = l;
            }
            if (seatTable[maxC] < seatTable[l]) {
                maxC = l;
            }
        }

        System.out.println(maxC);
        System.out.println(minC);
    }

    static class Painting {
        int left;
        int right;
        int colour;

        public Painting(int left, int right, int colour) {
            this.left = left;
            this.right = right;
            this.colour = colour;
        }
    }
}