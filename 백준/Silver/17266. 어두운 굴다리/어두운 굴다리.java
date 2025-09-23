import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int streetLength = Integer.parseInt(br.readLine()); // 굴다리 길이
        int lightCnt = Integer.parseInt(br.readLine()); // 가로등 개수
        int[] lightPos = new int[lightCnt]; // 가로등 위치

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < lightCnt; i++) lightPos[i] = Integer.parseInt(st.nextToken());

        int longestBewteenLength = 0;
        for (int i = 0; i < lightPos.length-1; i++) {
            int foreLightPos = lightPos[i];
            int backLightPos = lightPos[i+1];
            int betweenLength = backLightPos - foreLightPos;
            longestBewteenLength = Math.max(longestBewteenLength, betweenLength);
        }

        int height = 0;
        // 1. 가로등 사이 거리 만족
        if (longestBewteenLength % 2 == 0) height = longestBewteenLength / 2;
        else height = longestBewteenLength / 2 + 1;

        // 2. 시작과 끝 지점 사이 거리 만족
        height = Math.max(height, lightPos[0]);
        height = Math.max(height, streetLength-lightPos[lightPos.length-1]);

        bw.write(String.valueOf(height));
        bw.flush();
        bw.close();
    }
}