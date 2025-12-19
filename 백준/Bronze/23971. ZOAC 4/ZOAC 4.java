import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int Rspace = Integer.parseInt(st.nextToken());
        int Cspace = Integer.parseInt(st.nextToken());

        // 가로 최대
        int Cmax = 1;
        int CmaxQuo = C / (1+Cspace); // 앉은 칸(1) + 띄어야 하는 칸(Cspace)
        int CmaxRest = C % (1+Cspace);
        if (CmaxRest == 0) Cmax = CmaxQuo;
        else Cmax = CmaxQuo+1;

        // 세로 최대
        int Rmax = 1;
        int RmaxQuo = R / (1+Rspace);
        int RmaxRest = R % (1+Rspace);
        if (RmaxRest == 0) Rmax = RmaxQuo;
        else Rmax = RmaxQuo+1;

        long answer = (long)Cmax * (long)Rmax;
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}