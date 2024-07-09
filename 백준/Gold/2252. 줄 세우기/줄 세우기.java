import java.io.*;
import java.util.*;
// 위상 정렬
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 노드 개수
        int M = Integer.parseInt(st.nextToken()); // 간선 개수

        ArrayList<Integer>[] students = new ArrayList[N+1]; // 학생 배열
        for (int i = 0; i < students.length; i++) {
            students[i] = new ArrayList<>();
        }

        int[] indegree = new int[N+1]; // 진입 차수 배열

        for (int i = 0; i < M; i++) { // 인접 리스트 세팅 → 진입 차수 배열 세팅
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            students[A].add(B);
            indegree[B]++;
        }

        Queue<Integer> queue = new LinkedList<>(); // 정렬 큐
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) { // 시작 노드 선택
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            bw.write(now + " ");
            for (int next : students[now]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        bw.newLine();
        bw.flush();
        bw.close();
    }
}