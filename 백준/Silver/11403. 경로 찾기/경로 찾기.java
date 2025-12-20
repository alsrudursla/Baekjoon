import java.util.*;
import java.io.*;

class Main {
    static ArrayList<Integer>[] graph;
    static int[][] gArr;
    static boolean[] visited;
    static HashSet<Integer> path;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeCnt = Integer.parseInt(br.readLine());

        graph = new ArrayList[nodeCnt];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < nodeCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < nodeCnt; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) graph[i].add(j);
            }
        }

        gArr = new int[nodeCnt][nodeCnt];
        for (int i = 0; i < nodeCnt; i++) {
            path = new HashSet<>();
            visited = new boolean[nodeCnt];
            findRoute(i, path);
            
            for (int linked : path) gArr[i][linked] = 1;
        }

        for (int i = 0; i < nodeCnt; i++) {
            for (int j = 0; j < nodeCnt; j++) bw.write(gArr[i][j] + " ");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void findRoute(int now, HashSet<Integer> path) {
        for (int next : graph[now]) {
            if (!visited[next]) {
                visited[next] = true;
                path.add(next);
                findRoute(next, path);
            }
        }
    }
}