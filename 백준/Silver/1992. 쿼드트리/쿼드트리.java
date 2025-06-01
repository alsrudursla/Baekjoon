import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        // 4분할
        String ans = compress(N, 0, 0);
        bw.write(ans);

        bw.flush();
        bw.close();
    }

    private static String compress(int size, int startY, int startX) {
        // 처음부터 모든 수가 같은 경우 (4분할 X)
        if (isAll0(startY, startX, startY + size - 1, startX + size - 1)) return "0";
        else if (isAll1(startY, startX, startY + size - 1, startX + size - 1)) return "1";

        // 1. 왼쪽 위 검사
        String chkLeftUp = chk(startY, startX, startY + size / 2 - 1, startX + size / 2 - 1, size);

        // 2. 오른쪽 위 검사
        String chkRightUp = chk(startY, startX + size / 2, startY + size / 2 - 1, startX + size - 1, size);

        // 3. 왼쪽 아래 검사
        String chkLeftDown = chk(startY + size / 2, startX, startY + size - 1, startX + size / 2 - 1, size);

        // 4. 오른쪽 아래 검사
        String chkRightDown = chk(startY + size / 2, startX + size / 2, startY + size - 1, startX + size - 1, size);

        return "(" + chkLeftUp + chkRightUp + chkLeftDown + chkRightDown + ")";
    }

    private static String chk(int startY, int startX, int endY, int endX, int size) {
        if (isAll0(startY, startX, endY, endX)) return "0";
        else if (isAll1(startY, startX, endY, endX)) return "1";
        else return compress(size/2, startY, startX);
    }

    private static boolean isAll0(int startY, int startX, int endY, int endX) {
        for (int i = startY; i <= endY; i++) {
            for (int j = startX; j <= endX; j++) {
                if (map[i][j] == 1) return false;
            }
        }
        return true;
    }

    private static boolean isAll1(int startY, int startX, int endY, int endX) {
        for (int i = startY; i <= endY; i++) {
            for (int j = startX; j <= endX; j++) {
                if (map[i][j] == 0) return false;
            }
        }
        return true;
    }
}