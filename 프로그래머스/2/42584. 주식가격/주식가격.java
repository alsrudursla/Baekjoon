import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> myStack = new Stack<>();
        int[] answer = new int[prices.length];
        
        for (int i = 0; i < prices.length; i++) {
            int num = prices[i];
            if (myStack.isEmpty()) {
                // 스택에 인덱스 저장 -> 인덱스의 차이가 시간
                myStack.push(i);
            } else {
                int topIdx = myStack.peek();
                int top = prices[topIdx];
                while (!myStack.isEmpty() && top > num) {
                    myStack.pop();
                    answer[topIdx] = i - topIdx;
                    if (!myStack.isEmpty()) {
                        topIdx = myStack.peek();
                        top = prices[topIdx];
                    }
                }
                myStack.push(i);
            }
        }
        
        // 스택에 남아있는 값 정리
        while (!myStack.isEmpty()) {
            int topIdx = myStack.pop();
            int top = prices[topIdx];
            answer[topIdx] = prices.length - topIdx - 1;
        }
        
        return answer;
    }
}