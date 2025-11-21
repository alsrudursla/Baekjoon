import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        while (!input.equals("end")) {
            // 1. 맵 만들기
            char[] arr = input.toCharArray();
            char[][] map = new char[3][3];
            int idx = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) map[i][j] = arr[idx++];
            }

            // 2. 검사
            // 2-1. 둘의 차이가 1 이하여야 함
            int Ocnt = 0;
            int Xcnt = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (map[i][j] == 'O') Ocnt++;
                    else if (map[i][j] == 'X') Xcnt++;
                }
            }

            if (Xcnt == Ocnt+1) {
                // X 차례로 끝난 상태 : O 가 빙고면 안됨
                if (chkMap(map, 'O') >= 1) bw.write("invalid\n");
                else if (chkMap(map, 'X') >= 1) bw.write("valid\n");
                else if (isFull(map)) bw.write("valid\n");
                else bw.write("invalid\n");
            } else if (Xcnt == Ocnt) {
                // O 차례로 끝난 상태 : X 가 빙고면 안됨
                if (chkMap(map, 'X') >= 1) bw.write("invalid\n");
                else if (chkMap(map, 'O') >= 1) bw.write("valid\n");
                else bw.write("invalid\n");
            } else bw.write("invalid\n");

            input = br.readLine();
        }

        bw.flush();
        bw.close();
    }

    private static boolean isFull(char[][] map) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == '.') return false;
            }
        }
        
        return true;
    }

    private static int chkMap(char[][] map, char stone) {
        int bingoCnt = 0;
        // 1. 가로 확인
        for (int i = 0; i < 3; i++) {
            int tmp = 0;
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == stone) tmp++;
            }
            if (tmp == 3) bingoCnt++;
        }
        
        // 2. 세로 확인
        for (int j = 0; j < 3; j++) {
            int tmp = 0;
            for (int i = 0; i < 3; i++) {
                if (map[i][j] == stone) tmp++;
            }
            if (tmp == 3) bingoCnt++;
        }

        // 3. 대각선 확인
        if (map[0][0] == stone && map[1][1] == stone && map[2][2] == stone) bingoCnt++;
        if (map[0][2] == stone && map[1][1] == stone && map[2][0] == stone) bingoCnt++;

        return bingoCnt;
    }
}