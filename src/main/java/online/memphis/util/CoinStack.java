package online.memphis.util;

import java.util.Stack;

public class CoinStack {

    private Stack<Integer> stack = new Stack();
    private int sum = 0;

    public void push(int coin) {
        sum += coin;
        stack.push(coin);
    }

    public void pop() {
        sum -= stack.pop();
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "CoinStack{" +
                "stack=" + stack +
                '}';
    }
}
