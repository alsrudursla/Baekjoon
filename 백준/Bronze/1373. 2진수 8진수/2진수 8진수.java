import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();

        // 1. 2진수를 뒤에서부터 3개씩 끊기
        // 2. 3개씩 변환 -> 8진수
        StringBuilder ans = new StringBuilder();
        for (int i = input.length()-1; i >= 0; i-=3) {
            if (i-2 < 0) ans.append(change(input.substring(0, i+1)));
            else ans.append(change(input.substring(i-2, i+1)));
        }
        ans = ans.reverse();

        bw.write(ans.toString());
        bw.flush();
        bw.close();
    }

    private static String change(String number) {
        int tmp = 0;
        int added = 1;
        for (int i = number.length()-1; i >= 0; i--) {
            if (number.charAt(i) == '1') tmp += added;
            added *= 2;
        }
        return String.valueOf(tmp);
    }
}