import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testcase = Integer.parseInt(br.readLine());
        String firstWord = br.readLine();

        int answer = 0;
        for (int i = 0; i < testcase-1; i++) {
            String nowWord = br.readLine();

            // 길이가 2 이상 차이 나면 안됨
            if (Math.abs(nowWord.length() - firstWord.length()) >= 2) continue;

            char[] alpha = new char[26];
            for (int a = 0; a < firstWord.length(); a++) {
                alpha[firstWord.charAt(a) - 'A']++;
            }

            int difference = 0;
            for (int j = 0; j < nowWord.length(); j++) {
                char nowChar = nowWord.charAt(j);

                if (alpha[nowChar - 'A'] > 0) {
                    alpha[nowChar - 'A']--;
                    difference++;
                }
            }

            // 1. 길이가 같을 때 : 문자 개수도 모두 같음 (같은 구성) or 하나만 다름
            // 2. 메인 문자열이 더 길 때 -> difference = 비교 문자열 길이
            // 3. 비교 문자열이 더 길 때 -> difference = 메인 문자열 길이
            if (nowWord.length() == firstWord.length() && 
                (difference == firstWord.length() || difference == firstWord.length() - 1)) answer++;
            else if (nowWord.length() + 1 == firstWord.length() && difference == nowWord.length()) answer++;
            else if (nowWord.length() - 1 == firstWord.length() && difference == firstWord.length()) answer++;
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}