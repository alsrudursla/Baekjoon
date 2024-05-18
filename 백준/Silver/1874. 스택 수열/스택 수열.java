import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        boolean result = true;

        int stackNum = 1;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num >= stackNum) {
                while (num >= stackNum) {
                    stack.push(stackNum++);
                    sb.append("+\n");
                }
                stack.pop();
                sb.append("-\n");
            } else {
                if (num > stack.pop()) {
                    bw.write("NO");
                    result = false;
                    break;
                } else {
                    sb.append("-\n");
                }
            }
        }

        if (result) {
            bw.write(sb.toString());
        }
        bw.newLine();
        bw.flush();
        bw.close();
    }
}