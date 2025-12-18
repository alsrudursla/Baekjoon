import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int childrenCnt = sc.nextInt();

        int[] order = new int[childrenCnt];
        for (int i = 0; i < childrenCnt; i++) order[i] = sc.nextInt();

        // 증가 수열이 가장 긴 것 찾기 (최대한 안움직이기)
        // dp[i] : i번째 수를 "마지막으로" 하는 증가 부분 수열의 최대 길이
        int[] dp = new int[childrenCnt];

        for (int i = 0; i < childrenCnt; i++) {
            dp[i] = 1; // 나 자신
            for (int j = 0; j < i; j++) {
                // 나보다 작은 순서가 앞에 있을 때
                if (order[i] > order[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        // 가장 많이 안움직여도 되는 수열의 원소 개수
        int canNotMove = 0;
        for (int i = 0; i < dp.length; i++) canNotMove = Math.max(canNotMove, dp[i]);

        // 최소로 움직이기 = 전체 - 가장 많이 안움직이기
        int answer = childrenCnt - canNotMove;
        System.out.println(answer);
        sc.close();
    }
}