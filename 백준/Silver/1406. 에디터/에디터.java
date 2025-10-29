import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();
        
        StringBuffer sb = new StringBuffer();
        sb.append(input);

        int testcase = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int cursor = input.length();
        for (int i = 0; i < testcase; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("L")) {
                if (cursor > 0) cursor--;
            } else if (command.equals("D")) {
                if (cursor < sb.length()) cursor++;
            } else if (command.equals("B")) {
                if (cursor > 0) {
                    sb.deleteCharAt(cursor-1);
                    cursor--;
                }
            } else {
                String input2 = st.nextToken();
                sb.insert(cursor, input2);
                cursor += input2.length();
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }
}