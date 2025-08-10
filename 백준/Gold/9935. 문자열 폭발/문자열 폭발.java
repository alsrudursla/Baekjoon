import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();
        String burst = br.readLine();

        Stack<Character> cStack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            // 1. 스택에 문자 넣기
            char now = input.charAt(i);
            cStack.push(now);

            // 2. 스택의 마지막 문자열이 폭발 문자열일 경우 삭제
            if (cStack.size() < burst.length()) continue;

            boolean chk = true;
            int bidx = burst.length()-1;
            int cidx = cStack.size()-1;
            for (int j = 0; j < burst.length(); j++) {
                if (cStack.get(cidx--) != burst.charAt(bidx--)) {
                    chk = false;
                    break;
                }
            }

            // 같으면 그대로 삭제
            if (chk) {
                for (int j = 0; j < burst.length(); j++) cStack.pop();
            }
        }

        StringBuffer ans = new StringBuffer();
        while (!cStack.isEmpty()) ans.append(cStack.pop());

        if (ans.length() == 0) bw.write("FRULA");
        else bw.write(ans.reverse().toString());
        bw.flush();
        bw.close();
    }
}