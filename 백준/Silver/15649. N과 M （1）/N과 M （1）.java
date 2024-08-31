import java.io.*;
import java.util.*;

// 백트래킹
public class Main {
    static int N, M;
    static List<Integer> ans;
    static boolean[] visited; // 방문 배열 체크
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = new ArrayList<>();
        visited = new boolean[N+1];
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        sequence(0);
        bw.flush();
        bw.close();
    }

    private static void sequence(int num) throws IOException {
        if (num == M) {
            for (int i = 0; i < ans.size(); i++) {
                bw.write(String.valueOf(ans.get(i)) + ' ');
            }
            bw.newLine();
            return;
        }

        for (int j = 1; j <= N; j++) {
            if (!visited[j]) {
                visited[j] = true;
                ans.add(j);
                sequence(num+1);
                visited[j] = false;
                ans.remove(Integer.valueOf(j));
            }
        }
    }
}