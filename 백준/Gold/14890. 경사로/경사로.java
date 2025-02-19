import java.io.*;
import java.util.*;
public class Main {
    static int N, L;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지도 크기
        L = Integer.parseInt(st.nextToken()); // 경사로 길이

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int path = 0;
        // 1. 가로 길 가능 개수 구하기
        for (int i = 0; i < N; i++) {
            int[] path_arr = new int[N];
            for (int j = 0; j < N; j++) {
                path_arr[j] = map[i][j];
            }

            boolean chk = chkPath(path_arr);
            if (chk) path++;
        }

        // 2. 세로 길 가능 개수 구하기
        for (int j = 0; j < N; j++) {
            int[] path_arr = new int[N];
            for (int i = 0; i < N; i++) {
                path_arr[i] = map[i][j];
            }

            boolean chk = chkPath(path_arr);
            if (chk) path++;
        }

        bw.write(String.valueOf(path));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static boolean chkPath(int[] path_arr) {
        boolean canGo = true;
        boolean[] visited = new boolean[N];

        for (int i = 1; i < path_arr.length; i++) {
            if (Math.abs(path_arr[i-1] - path_arr[i]) > 1) {
                // 같거나 1 만큼만 차이 나야 함
                canGo = false;
                break;
            }

            if (path_arr[i-1] == path_arr[i]) continue;
            else if (path_arr[i-1] > path_arr[i]) { // 내려가는 길
                // 경사로 길이 만큼 path_arr[i] 높이의 길이 있어야 함
                int idx = i;
                for (int j = 0; j < L; j++) {
                    if (!chkBoundary(idx) || path_arr[idx] != path_arr[i] || visited[idx]) {
                        canGo = false;
                        break;
                    }
                    visited[idx++] = true;
                }
                i += L-1;
            } else { // 올라가는 길
                int idx = i - 1;
                for (int j = 0; j < L; j++) {
                    if (!chkBoundary(idx) || path_arr[idx] != path_arr[i]-1 || visited[idx]) {
                        canGo = false;
                        break;
                    }
                    visited[idx--] = true;
                }
            }

            if (!canGo) break;
        }

        return canGo;
    }

    private static boolean chkBoundary(int num) {
        if (0 <= num && num < N) return true;
        else return false;
    }
}