import java.io.*;
import java.util.*;

class Main {
    static List<String> ans;
    static int L, C;
    static char[] alpha;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); // 암호 길이
        C = Integer.parseInt(st.nextToken()); // 주어진 문자 개수

        String[] line = br.readLine().split(" ");
        alpha = new char[C]; // 주어진 문자 배열
        for (int i = 0; i < line.length; i++) alpha[i] = line[i].charAt(0);
        Arrays.sort(alpha);

        // 서로 다른 L개의 알파벳 소문자들로 구성
        // 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음
        // 증가하는 순서로 배열

        ans = new ArrayList<>();
        makePassword("", 0);

        Collections.sort(ans);
        for (int i = 0; i < ans.size(); i++) {
            bw.write(ans.get(i));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void makePassword(String now, int idx) {
        if (chkVowel(now) && chkConsonant(now) && (now.length() == L)) {
            ans.add(now);
            return;
        }

        for (int i = idx; i < alpha.length; i++) {
            String next = now + String.valueOf(alpha[i]);
            makePassword(next, i+1);
        }
    }

    private static boolean chkVowel(String str) {
        for (int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);
            if (now == 'a' || now == 'e' || now  == 'i' || now == 'o' || now == 'u') return true;
        }
        return false;
    }

    private static boolean chkConsonant(String str) {
        int tmp = 0;
        for (int i = 0; i < str.length(); i++) {
            char now = str.charAt(i);
            if (now != 'a' && now != 'e' && now != 'i' && now != 'o' && now != 'u') tmp++;
        }

        if (tmp >= 2) return true;
        else return false;
    }
}