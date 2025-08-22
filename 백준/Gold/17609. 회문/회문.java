import java.util.*;
import java.io.*;

class Main {
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String input = br.readLine();

            int left = 0;
            int right = input.length()-1;
            cnt = 0;
            boolean chk = recursion(input, left, right);

            if (chk && cnt == 0) bw.write(0 + "\n");
            else if (chk && cnt == 1) bw.write(1 + "\n");
            else bw.write(2 + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static boolean recursion(String input, int left, int right) {
        if (left >= right) return true;

        if (input.charAt(right) == input.charAt(left)) return recursion(input, left+1, right-1);

        if (cnt == 1) return false;
        else {
            cnt++;
            return recursion(input, left+1, right) || recursion(input, left, right-1);
        }
    }
}