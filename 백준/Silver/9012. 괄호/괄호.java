import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testcase; t++) {
            String input = br.readLine();
            Stack<Character> cStack = new Stack<>();

            for (int i = 0; i < input.length(); i++) {
                char now = input.charAt(i);
                if (cStack.isEmpty()) cStack.push(now);
                else {
                    if (now == ')' && cStack.peek() == '(') cStack.pop();
                    else cStack.push(now);
                }
            }

            if (cStack.isEmpty()) bw.write("YES");
            else bw.write("NO");
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}