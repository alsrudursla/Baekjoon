import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 0 개수
        int[] zeroCnt = new int[501];
        zeroCnt[0] = 0; // 0! = 1
        int twoCnt = 0;
        int fiveCnt = 0;
        for (int i = 1; i <= 500; i++) {
            twoCnt += divNum(i, 2);
            fiveCnt += divNum(i, 5);

            int cnt = Math.min(twoCnt, fiveCnt);
            zeroCnt[i] = cnt;
        }

        System.out.println(zeroCnt[N]);
        sc.close();
    }

    private static int divNum(int value, int div) {
        int cnt = 0;
        while (value % div == 0) {
            cnt++;
            value /= div;
        }
        return cnt;
    }
}

/*
1
2
3! : 6
4!(2^2) : 24
5! : 120
6! : 720
7! : 5040
8! : 40320
9! : 362880
10!(2*5) : 3628800
-> 누적된 2&5 쌍 만큼 0이 붙음
*/