import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        String[] tmp = br.readLine().split(" ");
        int[] arr = new int[N];
        for (int i = 0; i < arr.length; i++) arr[i] = Integer.parseInt(tmp[i]);

        // 오른쪽에서부터 검사
        Stack<Integer> myStack = new Stack<>();
        int[] ans = new int[N];
        for (int i = arr.length-1; i >= 0; i--) {
            int now = arr[i];
            if (myStack.isEmpty()) {
                ans[i] = -1;
                myStack.push(now);
            } else {
                // 스택 top 이 자기 자신보다 크면 답
                // 작으면 pop
                boolean chk = false;
                while (!myStack.isEmpty()) {
                    int top = myStack.peek();
                    if (top > now) {
                        ans[i] = top;
                        chk = true;
                        break;
                    } else myStack.pop();
                }

                if (!chk) ans[i] = -1;

                // 현재 값 스택에 push
                myStack.push(now);
            }
        }

        for (int i = 0; i < ans.length; i++) {
            bw.write(ans[i] + " ");
        }
        bw.flush();
        bw.close();
    }
}