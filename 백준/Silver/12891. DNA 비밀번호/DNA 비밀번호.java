import java.io.*;
import java.util.*;
public class Main {

    private static int[] alphabetNum = new int[4]; // 필요한 A C G T 개수 배열
    private static int[] checkArr = new int[4]; // 부분 문자열 길이 배열에서의 A C G T 개수 배열
    private static int checkNum = 0; // 부분 문자열 길이 배열이 필요한 A C G T 개수와 맞는지 확인

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String dna = br.readLine();
        char[] dnaArr = dna.toCharArray(); // 주어진 문자열 배열

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < alphabetNum.length; i++) {
            alphabetNum[i] = Integer.parseInt(st.nextToken());
            if (alphabetNum[i] == 0) {
                checkNum++; // 필요한 최소 개수가 0이면 조건 만족
            }
        }

        int cnt = 0;
        // 맨 처음 세팅
        for (int j = 0; j < P; j++) {
            Add(dnaArr[j]);
        }

        if (checkNum == 4) {
            cnt++;
        }

        // 슬라이딩 윈도우
        for (int k = P; k < S; k++) {
            int l = k - P;
            Add(dnaArr[k]);
            Remove(dnaArr[l]);
            if (checkNum == 4) {
                cnt++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(cnt));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void Add(char c) {
        switch (c) {
            case 'A' :
                checkArr[0]++;
                if (checkArr[0] == alphabetNum[0]) checkNum++;
                break;
            case 'C' :
                checkArr[1]++;
                if (checkArr[1] == alphabetNum[1]) checkNum++;
                break;
            case 'G' :
                checkArr[2]++;
                if (checkArr[2] == alphabetNum[2]) checkNum++;
                break;
            case 'T' :
                checkArr[3]++;
                if (checkArr[3] == alphabetNum[3]) checkNum++;
                break;
        }
    }

    private static void Remove(char c) {
        switch (c) {
            case 'A' :
                if (checkArr[0] == alphabetNum[0]) checkNum--;
                checkArr[0]--;
                break;
            case 'C' :
                if (checkArr[1] == alphabetNum[1]) checkNum--;
                checkArr[1]--;
                break;
            case 'G' :
                if (checkArr[2] == alphabetNum[2]) checkNum--;
                checkArr[2]--;
                break;
            case 'T' :
                if (checkArr[3] == alphabetNum[3]) checkNum--;
                checkArr[3]--;
                break;
        }
    }
}
