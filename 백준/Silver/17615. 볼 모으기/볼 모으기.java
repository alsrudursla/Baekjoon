import java.util.*;
import java.io.*;

class Main {
    static char[] bArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int ballCnt = Integer.parseInt(br.readLine());
        bArr = new char[ballCnt];
        
        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            bArr[i] = input.charAt(i);
        }

        // 1. 빨간 공 체크
        int redMove = moveBall('R');
        
        // 2. 파란 공 체크
        int blueMove = moveBall('B');

        bw.write(String.valueOf(Math.min(redMove, blueMove)));
        bw.flush();
        bw.close();
    }

    private static int moveBall(char color) {
        // 1. 오른쪽으로 모았을 때
        int rightMove = moveToDir(color, 'R');

        // 2. 왼쪽으로 모았을 때
        int leftMove = moveToDir(color, 'L');

        return Math.min(rightMove, leftMove);
    }

    private static int moveToDir(char color, char dir) {
        // 색깔이 color 인 공을 끝 쪽부터 dir 방향으로 모았을 때
        // 시뮬레이션은 시간초과, 가장자리에 모여있지 않은 공을 카운트
        int move = 0;
        if (dir == 'R') {
            if (bArr[bArr.length-1] == color) {
                // 가장자리에 모여있는 묶음처리하기
                int idx = bArr.length-1;
                while (idx >= 0 && bArr[idx] == color) idx--;
                for (int i = idx; i >= 0; i--) {
                    if (bArr[i] == color) move++;
                }
            } else {
                for (int i = 0; i < bArr.length; i++) {
                    if (bArr[i] == color) move++;
                }
            }
        } else {
            if (bArr[0] == color) {
                int idx = 0;
                while (idx < bArr.length && bArr[idx] == color) idx++;
                for (int i = idx; i < bArr.length; i++) {
                    if (bArr[i] == color) move++;
                }
            } else {
                for (int i = 0; i < bArr.length; i++) {
                    if (bArr[i] == color) move++;
                }
            }
        }
        return move;
    } 
}