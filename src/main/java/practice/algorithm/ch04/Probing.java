package practice.algorithm.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Probing {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxTicketNo = nm[0];
        int ticketCnt = nm[1];

        List<Integer> memberIds = new ArrayList<>();
        for (int i = 0; i < ticketCnt; i++) {
            int id = Integer.parseInt(br.readLine());
            memberIds.add(id);
        }

        List<Integer> numbers = getNumbers(maxTicketNo, ticketCnt, memberIds);
        for (int num : numbers) {
            System.out.println(num);
        }
    }

    private static List<Integer> getNumbers(int maxTicketNo, int ticketCnt, List<Integer> memberIds) {
        List<Integer> tickets = new ArrayList<>();
        TicketTable ticketTable = new TicketTable(maxTicketNo);
        for (int i = 0; i < ticketCnt; i++) {
            int id = memberIds.get(i);
            int number = ticketTable.findByMemberId(id);
            tickets.add(number);
            ticketTable.used[number] = 1;
        }
        return tickets;
    }

    static class TicketTable {
        private int[] used;
        private int length;

        public TicketTable(int maxTicketNo) {
            this.used = new int[maxTicketNo];
            this.length = maxTicketNo;
        }

        public int findByMemberId(int id) {
            int index = id % length;
            while (isUsed(index)) {
                index = (index + 1) % length;
            }
            return index;
        }

        public boolean isUsed(int index) {
            return (used[index] > 0);
        }
    }
}