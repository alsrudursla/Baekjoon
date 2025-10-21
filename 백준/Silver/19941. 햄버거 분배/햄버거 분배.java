import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tableLen = Integer.parseInt(st.nextToken());
        int distance = Integer.parseInt(st.nextToken());

        char[] posArr = br.readLine().toCharArray();

        boolean[] canEat = new boolean[tableLen];
        int answer = 0;
        for (int i = 0; i < posArr.length; i++) {
            char nowChar = posArr[i];
            if (nowChar == 'H') continue;

            for (int j = i-distance; j <= i+distance; j++) {
                if (j < 0 || j >= posArr.length) continue;
                if (posArr[j] == 'H' && !canEat[j]) {
                    canEat[j] = true;
                    answer++;
                    break;
                }
            }
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}