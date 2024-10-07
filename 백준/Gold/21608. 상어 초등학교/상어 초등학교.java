import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Integer>[] favorite;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        map = new int[N*N+1][N*N+1];
        visited = new boolean[N*N+1][N*N+1];
        favorite = new ArrayList[N*N+1];
        for (int i = 1; i <= N*N; i++) {
            favorite[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i <= N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int student = 0;
            for (int j = 0; j < 5; j++) {
                if (j == 0) {
                    student = Integer.parseInt(st.nextToken());
                } else {
                    favorite[student].add(Integer.parseInt(st.nextToken()));
                }
            }
            bfs(student);
        }

        // 만족도 구하기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int point = 0;
                for (int k = 0; k < 4; k++) {
                    int x = j + dx[k];
                    int y = i + dy[k];

                    if (x >= 1 && x <= N && y >= 1 && y <= N && map[i][j] != 0) {
                        for (int fav_student : favorite[map[i][j]]) {
                            if (map[y][x] == fav_student) {
                                point++;
                            }
                        }
                    }
                }
                switch (point) {
                    case 1: ans += 1; break;
                    case 2: ans += 10; break;
                    case 3: ans += 100; break;
                    case 4: ans += 1000; break;
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void bfs(int student) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) ->
                o1.point != o2.point ? Integer.compare(o2.point, o1.point) :
                        (o1.zero_space != o2.zero_space ? Integer.compare(o2.zero_space, o1.zero_space) :
                        (o1.i != o2.i ? Integer.compare(o1.i, o2.i) : Integer.compare(o1.j, o2.j))));

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visited[i][j]) {
                    int point = 0;
                    int zero_space = 0;
                    for (int k = 0; k < 4; k++) {
                        int x = j + dx[k];
                        int y = i + dy[k];

                        if (x >= 1 && x <= N && y >= 1 && y <= N) {
                            for (int fav_student : favorite[student]) {
                                if (map[y][x] == fav_student) {
                                    point++;
                                }
                            }
                            if (map[y][x] == 0) {
                                zero_space++;
                            }
                        }
                    }
                    pq.add(new Node(i, j, point, zero_space));
                }
            }
        }

        Node selectedNode = pq.poll();
        assert selectedNode != null;
        map[selectedNode.i][selectedNode.j] = student;
        visited[selectedNode.i][selectedNode.j] = true;
    }

    static class Node{
        int i, j;
        int point;
        int zero_space;

        Node(int i, int j, int point, int zero_space) {
            this.i = i;
            this.j = j;
            this.point = point;
            this.zero_space = zero_space;
        }
    }
}