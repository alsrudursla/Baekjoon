import java.io.*;
import java.util.*;
public class Main {
    static int N, ans;
    static int[][] people_map,ep_map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        // d 최대 : N-1, d 최소 : 1

        people_map = new int[N][N]; // 인구 수를 담은 맵
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                people_map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 기준점 i, j
                divideArea(i, j);
            }
        }

        // 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이의 최솟값
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static void divideArea(int i, int j) {
        // 1. d1, d2 정하기
        // 2. 영역 나누기
        // 3. 인구 차이 구하기

        for (int d1 = 1; d1 <= N-1; d1++) {
            for (int d2 = 1; d2 <= N-1; d2++) {
                if (i - d1 < 0 || i + d2 > N-1 || j + d1 + d2 > N-1) continue;

                ep_map = new int[N][N]; // 선거구 번호 맵
                drawLine(i, j, d1, d2);

                int[] people_num = new int[5]; // 선거구 별 인구 수
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {
                        if (ep_map[r][c] == -1) { // 경계선
                            people_num[4] += people_map[r][c];
                        } else {
                            people_num[ep_map[r][c] - 1] += people_map[r][c];
                        }
                    }
                }

                int largest = Integer.MIN_VALUE;
                int smallest = Integer.MAX_VALUE;
                for (int p = 0; p < people_num.length; p++) {
                    largest = Math.max(largest, people_num[p]);
                    smallest = Math.min(smallest, people_num[p]);
                }

                ans = Math.min(ans, largest - smallest);
            }
        }
    }

    private static void drawLine(int y, int x, int d1, int d2) {
        // 1번 경계선: (x, y), (x+1, y-1), ..., (x+d1, y-d1)
        // 4번 경계선: (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
        for (int i = 0; i <= d1; i++) {
            ep_map[y - i][x + i] = -1;
            ep_map[y + d2 - i][x + d2 + i] = -1;
        }

        // 2번 경계선: (x, y), (x+1, y+1), ..., (x+d2, y+d2)
        // 3번 경계선: (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
        for (int i = 0; i <= d2; i++) {
            ep_map[y + i][x + i] = -1;
            ep_map[y - d1 + i][x + d1 + i] = -1;
        }

        boolean firstChk = false;
        for (int i = y - d1 + 1; i < y + d2; i++) {
            firstChk = false;
            for (int j = x; j < x + d1 + d2; j++) {
                if (ep_map[i][j] == -1 && !firstChk) firstChk = true;
                else if (ep_map[i][j] == -1 && firstChk) break;
                else if (firstChk) ep_map[i][j] = 5;
            }
        }

        // 나머지 영역 채우기
        for (int i = 0; i < y; i++) {
            for (int j = 0; j <= x+d1; j++) {
                if (ep_map[i][j] == -1) break;
                ep_map[i][j] = 1;
            }
        }

        for (int i = 0; i <= y-d1+d2; i++) {
            for (int j = N-1; j >= x+d1+1; j--) {
                if (ep_map[i][j] == -1) break;
                ep_map[i][j] = 2;
            }
        }

        for (int i = y; i < N; i++) {
            for (int j = 0; j < x+d2; j++) {
                if (ep_map[i][j] == -1) break;
                ep_map[i][j] = 3;
            }
        }

        for (int i = y-d1+d2+1; i < N; i++) {
            for (int j = N-1; j >= x+d2; j--) {
                if (ep_map[i][j] == -1) break;
                ep_map[i][j] = 4;
            }
        }
    }
}