import java.io.*;

class Main {
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();
        String T = br.readLine();

        answer = 0;
        dfs(S, T); // S->T : 시간초과, T->S : 역방향 탐색
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void dfs(String S, String T) {
        if (T.length() < S.length()) return;
        if (S.length() == T.length()) {
            if (S.equals(T)) answer = 1;
            return;
        }

        // 1. 문자열 뒤에 A 제거
        if (T.charAt(T.length()-1) == 'A') dfs(S, T.substring(0, T.length()-1));

        // 2. 문자열 앞에 B 제거, 문자열 뒤집기
        if (T.charAt(0) == 'B') {
            StringBuffer sb = new StringBuffer(T.substring(1));
            dfs(S, sb.reverse().toString());
        }
    }
}