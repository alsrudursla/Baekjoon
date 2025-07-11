import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine().toUpperCase();

        // 1. 알파벳 별 개수 구하기
        Map<Character, Integer> alphabet = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char now = input.charAt(i);
            alphabet.put(now, alphabet.getOrDefault(now, 0) + 1);
        }

        // 2. 가장 많이 쓰인 알파벳 구하기
        char biggest_alphabet = ' ';
        int biggest_cnt = 0;
        for (char a : alphabet.keySet()) {
            char alpha = a;
            int cnt = alphabet.get(alpha);
            if (cnt > biggest_cnt) {
                biggest_cnt = cnt;
                biggest_alphabet = alpha;
            }
        }
        alphabet.remove(biggest_alphabet);

        // 3. 여러 개 존재하는지 확인
        for (char a : alphabet.keySet()) {
            if (alphabet.get(a) == biggest_cnt) {
                biggest_alphabet = '?';
                break;
            }
        }

        bw.write(String.valueOf(biggest_alphabet));
        bw.flush();
        bw.close();
    }
}