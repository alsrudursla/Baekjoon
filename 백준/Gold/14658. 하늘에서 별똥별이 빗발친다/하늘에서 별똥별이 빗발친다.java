import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int starWidth = Integer.parseInt(st.nextToken());
        int starHeight = Integer.parseInt(st.nextToken());
        int tramWidth = Integer.parseInt(st.nextToken());
        int starCnt = Integer.parseInt(st.nextToken());

        List<int[]> starList = new ArrayList<>();
        for (int i = 0; i < starCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            starList.add(new int[]{sx, sy});
        }

        int[][] cntArr = new int[starCnt][starCnt];
        // 모든 별의 x좌표, y좌표로 트램펄린 왼쪽 하단 기준 좌표 후보로 놓음
        for (int i = 0; i < starCnt; i++) {
            int nowX = starList.get(i)[0];
            for (int j = 0; j < starCnt; j++) {
                int nowY = starList.get(j)[1];
                int cnt = 0;
                for (int s = 0; s < starList.size(); s++) {
                    int sx = starList.get(s)[0];
                    int sy = starList.get(s)[1];
                    int swidth = sx - nowX;
                    int sheight = sy - nowY;
                    if (swidth <= tramWidth && 0 <= swidth && sheight <= tramWidth && 0 <= sheight) cnt++;
                }
                cntArr[i][j] = cnt;
            }
        }

        int maxStarCnt = 0;
        for (int i = 0; i < cntArr.length; i++) {
            for (int j = 0; j < cntArr[i].length; j++) {
                maxStarCnt = Math.max(maxStarCnt, cntArr[i][j]);
            }
        }

        int answer = starCnt - maxStarCnt;
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}