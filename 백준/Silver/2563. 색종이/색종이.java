import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[][] map = new boolean[100][100];
        
        int paperNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < paperNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());

            for (int y = startY; y < startY+10; y++) {
                for (int x = startX; x < startX+10; x++) {
                    map[y][x] = true;
                }
            }
        }

        int size = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j]) size++; 
            }
        }

        bw.write(String.valueOf(size));
        bw.flush();
        bw.close();
    }
}