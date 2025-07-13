import java.io.*;
// 각각 포함하지 않은 알파벳 개수만큼 제거
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word1 = br.readLine();
        String word2 = br.readLine();

        int[] arr1 = new int[26];
        int[] arr2 = new int[26];

        for (int i = 0; i < word1.length(); i++) arr1[word1.charAt(i) - 'a']++;
        for (int i = 0; i < word2.length(); i++) arr2[word2.charAt(i) - 'a']++;

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (arr1[i] > arr2[i]) ans += (arr1[i]-arr2[i]);
            else if (arr2[i] > arr1[i]) ans += (arr2[i] - arr1[i]);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}