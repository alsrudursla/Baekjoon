import java.io.*;
import java.util.StringTokenizer;

// 세그먼트 트리
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 수 변경 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 합 구하는 횟수

        /* 트리 배열 구하는 방법
         2^k >= N 을 만족하는 k 최솟값 구한 후 2^k *2 를 트리 배열의 크기로 정한다
         */
        int k = 1;
        while((1<<k) < N) {
            k++;
        }
        int tree_size = (1<<k) * 2;
        long[] segment_tree = new long[tree_size];

        // 트리 리프 노드에 입력값 넣기
        int start = 1<<k;
        for (int i = 0; i < N; i++) {
            segment_tree[start++] = Long.parseLong(br.readLine());
        }

        // 나머지 노드 채우기 (구간 합 ver.)
        for (int i = (1<<k)-1; i > 0; i--) {
            segment_tree[i] = segment_tree[i*2] + segment_tree[i*2 + 1];
        }

        // 수 변경 후 출력
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            // 주어진 질의 인덱스를 세그먼트 트리의 리프 노드에 해당하는 인덱스로 변경
            int segment_b = b + (1<<k) - 1;

            if (a == 1) { // 수 변경
                segment_tree[segment_b] = c;

                // 부모 노드 값 변경
                while(segment_b > 0) {
                    segment_b /= 2;
                    segment_tree[segment_b] = segment_tree[segment_b*2] + segment_tree[segment_b*2 + 1];
                }
            } else { // 구간 합 출력
                int segment_c = (int) c + (1<<k) - 1;

                int start_idx = segment_b;
                int end_idx = segment_c;
                long sum = 0;
                while (start_idx <= end_idx) {
                    if (start_idx % 2 == 1) {
                        sum += segment_tree[start_idx];
                        start_idx++;
                    }
                    if (end_idx % 2 == 0) {
                        sum += segment_tree[end_idx];
                        end_idx--;
                    }
                    start_idx /= 2;
                    end_idx /= 2;
                }
                bw.write(String.valueOf(sum));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}