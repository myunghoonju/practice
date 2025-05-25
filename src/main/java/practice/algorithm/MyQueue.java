package practice.algorithm;


import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 * ---
 * e.g...,
 * ["MyQueue","push","push","peek","pop","empty"]
 */
public class MyQueue {

  private Queue<Integer> in = new ArrayDeque<>();
  private Queue<Integer> out = new ArrayDeque<>();

  public MyQueue() {}

  public void push(int x) {
    in.add(x);
  }

  public int pop() {
    peek();
    return out.remove();
  }

  public int peek() {
    if (out.isEmpty()) {
      while (!in.isEmpty()) {
        out.add(in.remove());
      }
    }

    return out.peek();
  }

  public boolean empty() {
    return in.isEmpty() && out.isEmpty();
  }
}