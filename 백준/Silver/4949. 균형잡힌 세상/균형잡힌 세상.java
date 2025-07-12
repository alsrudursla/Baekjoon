import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<Character> myStack = new Stack<>();
        
        while (true) {
            String now = br.readLine();
            if (now.equals(".")) break;

            boolean chk = true;
            for (int i = 0; i < now.length(); i++) {
                char nowC = now.charAt(i);

                if (nowC != '(' && nowC != ')' && nowC != '[' && nowC != ']') continue;

                if (myStack.isEmpty()) myStack.push(nowC);
                else if (nowC == '(' || nowC == '[') myStack.push(nowC);
                else if (nowC == ')') {
                    if (myStack.peek() == '(') myStack.pop();
                    else myStack.push(nowC);
                } else if (nowC == ']') {
                    if (myStack.peek() == '[') myStack.pop();
                    else myStack.push(nowC);
                }

                if (i == now.length()-1 && nowC != '.') chk = false;
            }

            // 스택 검사
            if (!myStack.isEmpty()) chk = false;
            
            // 출력
            if (chk) bw.write("yes\n");
            else bw.write("no\n");

            // 스택 초기화
            myStack.clear();
        }
        bw.flush();
        bw.close();
    }
}