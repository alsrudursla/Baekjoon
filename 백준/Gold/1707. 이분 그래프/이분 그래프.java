import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] arr; // 인접 리스트로 그래프 표현
    static boolean[] visited; // 정점 방문 여부
    static int[] check; // 각 정점이 어느 집합에 속하는지 (0 또는 1)
    static boolean chk; // 이분 그래프 여부
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNum = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < testCaseNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            arr = new ArrayList[v+1];
            visited = new boolean[v+1];
            check = new int[v+1];
            chk = true;

            for (int l = 0; l < v+1; l++) {
                arr[l] = new ArrayList<Integer>();
            }

            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());

                arr[node1].add(node2);
                arr[node2].add(node1);
            }

            for (int k = 1; k <= v; k++) {
                if (!visited[k]) {
                    DFS(k);
                }
            }
            if (chk) {
                bw.write(String.valueOf("YES"));
                bw.newLine();
            } else {
                bw.write(String.valueOf("NO"));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
    }

    private static void DFS(int node) {
        visited[node] = true;
        for (int i : arr[node]) {
            if (!visited[i]) {
                check[i] = (check[node] + 1) % 2; // 현재 정점 node 가 속한 반대의 집합으로 인접한 정점 i 색칠
                DFS(i);
            } else {
                if (check[node] == check[i]) {
                    chk = false;
                }
            }
        }
    }
}