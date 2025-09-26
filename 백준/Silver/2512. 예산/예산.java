import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int districtCnt = Integer.parseInt(br.readLine());
        Long[] requestMoney = new Long[districtCnt];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        long nowTotalMoney = 0L;
        for (int i = 0; i < districtCnt; i++) {
            requestMoney[i] = Long.parseLong(st.nextToken());
            nowTotalMoney += requestMoney[i];
        }

        long totalMoney = Long.parseLong(br.readLine());

        long largest = 0L;
        for (int i = 0; i < districtCnt; i++) largest = Math.max(largest, requestMoney[i]);

        // 모든 예산 배정 가능
        if (nowTotalMoney > totalMoney) {
            // 이분탐색 : 최소 1, 최대 largest
            // 1. 정수 상한액 설정
            // 2. 조건 만족 확인
            long start = 1L;
            long end = largest;
            
            while (start <= end) {
                long middle = (start + end) / 2;

                int used = 0;
                for (int i = 0; i < requestMoney.length; i++) {
                    if (requestMoney[i] <= middle) used += requestMoney[i];
                    else used += middle;
                }

                if (used <= totalMoney) {
                    start = middle + 1L;
                    largest = middle;
                } else end = middle - 1L;
            }
        }
        
        bw.write(String.valueOf(largest));
        bw.flush();
        bw.close();
    }
}