import java.io.*;
import java.util.*;

// 플로이드 워셜 (최단거리 알고리즘)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int cityNum = Integer.parseInt(br.readLine());
        int busNum = Integer.parseInt(br.readLine());

        int[][] distance = new int[cityNum+1][cityNum+1]; // 인접 행렬로 그래프 표현
        for (int i = 1; i <= cityNum; i++) {
            for (int j = 1; j <= cityNum; j++) {
                if (i == j) {
                    distance[i][j] = 0; // 자기 자신은 가중치 0
                } else {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        StringTokenizer st;
        for (int i = 0; i < busNum; i++) { // 주어진 입력값
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (c < distance[a][b]) {
                distance[a][b] = c; // 가중치 저장
            }
        }

        // 플로이드 점화식
        for (int k = 1; k <= cityNum; k++) {
            for (int s = 1; s <= cityNum; s++) {
                for (int e = 1; e <= cityNum; e++) {
                    if (distance[s][k] != Integer.MAX_VALUE && distance[k][e] != Integer.MAX_VALUE) { // 오버플로우 방지
                        distance[s][e] = Math.min(distance[s][e], distance[s][k] + distance[k][e]);
                    }
                }
            }
        }

        for (int i = 1; i <= cityNum; i++) {
            for (int j = 1; j <= cityNum; j++) {
                if (distance[i][j] == Integer.MAX_VALUE) {
                    bw.write("0 ");
                } else {
                    bw.write(distance[i][j] + " ");
                }
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}