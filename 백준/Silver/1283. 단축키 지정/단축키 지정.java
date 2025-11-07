import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Set<Character> keyAlpha = new HashSet<>();
        Set<String> keyWord = new HashSet<>();

        int optionCnt = Integer.parseInt(br.readLine());
        for (int o = 0; o < optionCnt; o++) {
            String word = br.readLine();
            String[] words = word.split(" ");

            // 1. 단어의 첫 글자 확인
            boolean chk = false;
            for (int i = 0; i < words.length; i++) {
                if (!keyAlpha.contains(words[i].toUpperCase().charAt(0))) {
                    chk = true;
                    keyAlpha.add(words[i].toUpperCase().charAt(0)); // 대문자로 저장
                    words[i] = "[" + words[i].charAt(0) + "]" + words[i].substring(1);
                    break;
                }
            }

            // 2. 단축키 지정 안된 알파벳 확인
            if (chk) {
                for (int i = 0; i < words.length; i++) bw.write(words[i] + " ");
                bw.newLine();
                continue;
            } else {
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == ' ') continue;
                    if (!keyAlpha.contains(word.toUpperCase().charAt(i))) {
                        keyAlpha.add(word.toUpperCase().charAt(i));
                        word = word.substring(0, i) + "[" + word.charAt(i) + "]" + word.substring(i+1);
                        break;
                    }
                }
            }
            
            bw.write(word);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}