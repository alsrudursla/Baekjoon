import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int city_num = Integer.parseInt(br.readLine()); // 정점
        int bus_num = Integer.parseInt(br.readLine()); // 간선

        int[][] path = new int[city_num+1][city_num+1];
        for (int i = 0; i < city_num+1; i++) {
            for (int j = 0; j < city_num+1; j++) {
                if (i == j) {
                    path[i][j] = 0;
                } else {
                    path[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        StringTokenizer st;
        for (int i = 0; i < bus_num; i++) {
            st = new StringTokenizer(br.readLine());
            int city_start = Integer.parseInt(st.nextToken());
            int city_end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (cost < path[city_start][city_end]) {
                path[city_start][city_end] = cost;
            }
        }

        // 플로이드
        for (int k = 1; k <= city_num; k++) { // 경유
            for (int j = 1; j <= city_num; j++) { // 시작
                for (int i = 1; i <= city_num; i++) { // 끝
                    if (path[j][k] != Integer.MAX_VALUE && path[k][i] != Integer.MAX_VALUE) { // 오버플로우 방지
                        if (path[j][i] > path[j][k] + path[k][i]) {
                            path[j][i] = path[j][k] + path[k][i];
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= city_num; i++) {
            for (int j = 1; j <= city_num; j++) {
                if (path[i][j] == Integer.MAX_VALUE) {
                    bw.write("0 ");
                } else {
                    bw.write(path[i][j] + " ");
                }
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}