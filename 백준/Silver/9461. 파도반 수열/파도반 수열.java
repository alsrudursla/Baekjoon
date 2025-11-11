import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();

        long[] tLen = new long[101]; // i 번째 삼각형 변의 길이
        tLen[1] = 1;
        tLen[2] = 1;
        tLen[3] = 1;
        tLen[4] = 2; // tLen[1] + tLen[3] : i-1, i-3
        tLen[5] = 2; // tLen[4]
        tLen[6] = 3; // tLen[5] + tLen[3] : i-1, i-3
        tLen[7] = 4; // tLen[6] + tLen[2] : i-1, i-5
        tLen[8] = 5; // tLen[7] + tLen[1] : i-1, i-6
        tLen[9] = 7; // tLen[8] + tLen[4]; : i-1, i-5
        tLen[10] = 9; // tLen[9] + tLen[5]; : i-1, i-5
        //tLen[11] = 12; // tLen[10] + tLen[6]; : i-1, i-5
        //tLen[12] = 16; // tLen[11] + tLen[7]; : i-1, i-5
        //tLen[13] = 21; // tLen[12] + tLen[8]; : i-1, i-5

        for (int i = 11; i <= 100; i++) {
            tLen[i] = tLen[i-1] + tLen[i-5];
        }

        for (int t = 0; t < testcase; t++) {
            int num = sc.nextInt();
            System.out.println(tLen[num]);
        }
    }
}