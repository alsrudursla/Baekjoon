import java.io.*;
import java.util.*;
public class Main {

    static boolean[] visited;
    static ArrayList<Integer>[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1]; // 방문 확인 배열
        A = new ArrayList[N+1]; // 인접 노드 담는 인접 리스트
        for (int i = 1; i < N+1; i++) { // 인접 리스트 구조 세팅
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) { // 인접 리스트 세팅
            st = new StringTokenizer(br.readLine());
            int first_num = Integer.parseInt(st.nextToken());
            int second_num = Integer.parseInt(st.nextToken());
            A[first_num].add(second_num); // 방향이 따로 없어서 양방향으로 추가
            A[second_num].add(first_num);
        }

        int cnt = 0;
        for (int i = 1; i < N+1; i++) {
            if (!visited[i]) {
                cnt++;
                DFS(i);
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
    }

    private static void DFS(int num) {
        if (visited[num]) {
            return;
        }
        visited[num] = true;
        for (int i : A[num]) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }
}