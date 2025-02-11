import java.util.*;
class Solution {
    public int solution(int[] order) {
        Stack<Integer> myStack = new Stack<>();
        int answer = 0;
        int box_idx = 1;
        int order_idx = 0;
        
        while (order_idx < order.length) {
            int now_order = order[order_idx]; // 현재 박스 순서
            if (now_order == box_idx) { // 컨테이너 벨트 박스 순서와 같음
                answer++;
                box_idx++;
                order_idx++;
            } else if (now_order > box_idx) {
                // 현재 박스보다 순서가 높음
                myStack.push(box_idx++);
            } else {
                // 현재 박스보다 순서가 낮다?
                if (myStack.isEmpty()) break;
                if (myStack.peek() == now_order) {
                    myStack.pop();
                    answer++;
                    order_idx++;
                } else break;
            }
        }
        
        return answer;
    }
}