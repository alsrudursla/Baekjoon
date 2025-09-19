import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int playCnt = Integer.parseInt(st.nextToken());
        String game = st.nextToken();

        HashSet<String> nameList = new HashSet<>();
        for (int i = 0; i < playCnt; i++) {
            String name = br.readLine();
            nameList.add(name);
        }

        int divisor = 0;
        if (game.equals("Y")) divisor = 1;
        else if (game.equals("F")) divisor = 2;
        else divisor = 3;

        int ans = nameList.size() / divisor;
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}