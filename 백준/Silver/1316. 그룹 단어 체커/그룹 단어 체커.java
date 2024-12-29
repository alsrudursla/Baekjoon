import java.io.*;
import java.util.*;

class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 단어 개수
        int answer = 0;

        char prev = '0';
        boolean[] tmp = new boolean[N];
        for (int i = 0; i < N; i++) {
            HashSet<Character> chk = new HashSet<>();
            String word = br.readLine();
            prev = '0';

            for (int j = 0; j < word.length(); j++) {
                if (word.charAt(j)==prev) {
                    // 같은 문자 입력됨
                    continue;
                } else { // 이어지는 문자가 다른 문자
                    // 이미 전에 입력되었던 문자
                    if (chk.contains(word.charAt(j))) {
                        tmp[i] = true;
                        break;
                    } else {
                        chk.add(word.charAt(j));
                    }
                }

                prev = word.charAt(j);
            }
        }

        for (int t = 0; t < N; t++) {
            if (!tmp[t]) answer++;
        }

        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}