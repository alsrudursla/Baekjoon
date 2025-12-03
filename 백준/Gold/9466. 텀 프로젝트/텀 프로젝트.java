import java.util.*;
import java.io.*;

class Main {
    static int[] sArr;
    static boolean[] visited;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcase = Integer.parseInt(br.readLine());
        for (int t = 0; t < testcase; t++) {
            int studentCnt = Integer.parseInt(br.readLine());
            sArr = new int[studentCnt+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int s = 1; s <= studentCnt; s++) sArr[s] = Integer.parseInt(st.nextToken());

            // 처음 시작한 번호로 다시 돌아오면 된다!
            visited = new boolean[studentCnt+1];
            answer = 0;
            for (int s = 1; s <= studentCnt; s++) {
                if (visited[s]) continue;
                if (sArr[s] == s) {
                    visited[s] = true;
                    continue;
                }
                makeTeam(s);
            }

            bw.write(String.valueOf(answer));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    private static void makeTeam(int start) {
        // 순회한 번호 기록
        List<Integer> myTeamArr = new ArrayList<>(); // 순서 기록
        myTeamArr.add(start);
        Set<Integer> myTeamSet = new HashSet<>(); // 사이클 탐지
        myTeamSet.add(start);

        // 한 번도 방문하지 않은 번호만 순회
        visited[start] = true;

        // 큐가 빌 때까지 반복
        Queue<Integer> order = new LinkedList<>();
        order.add(start);

        boolean cycle = false;
        int sidx = -1;
        while (!order.isEmpty()) {
            int now = order.poll();
            int next = sArr[now];

            // 사이클 탐지
            if (myTeamSet.contains(next)) {
                cycle = true;
                sidx = myTeamArr.indexOf(next);
                break;
            }

            if (!visited[next]) {
                visited[next] = true;
                myTeamArr.add(next);
                myTeamSet.add(next);
                order.add(next);
            }
        }

        if (cycle) {
            // 사이클 인덱스 시작 전까지 answer 에 추가
            int cnt = sidx;
            answer += cnt;
        } else {
            // 사이클 없을 때 (거쳐간 순서 모두 answer 에 추가)
            answer += myTeamArr.size();
        }
    }
}