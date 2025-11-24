import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String input = br.readLine();
        char[] duck = {'k', 'c', 'a', 'u', 'q'};
        List<Character> duckList = new ArrayList<>(); // 마지막 문자 저장
        int answer = -1;
        
        for (int i = 0; i < input.length(); i++) {
            answer = Math.max(answer, duckList.size());
            char now = input.charAt(i);
            if (now == 'q') duckList.add('q');
            else {
                boolean chk = false;
                int didx = -1;
                for (int d = 0; d < duck.length; d++) {
                    if (duck[d] == now) didx = d;
                }
                char beforeChar = duck[(didx+1)%5];
                for (int d = 0; d < duckList.size(); d++) {
                    if (duckList.get(d) == beforeChar) {
                        duckList.set(d, now);
                        chk = true;
                        if (now == 'k') duckList.remove(d);
                        break;
                    }
                }

                if (!chk) {
                    answer = -1;
                    break;
                }
            }
        }

        if (duckList.size() != 0) answer = -1;

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}