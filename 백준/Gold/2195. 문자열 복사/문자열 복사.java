import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String S = br.readLine(); // 패턴을 가져다 쓸 수 있는 문자열
        String P = br.readLine(); // 만들어야 하는 문자열

        int Pidx = 0;
        int ans = 0;
        while (Pidx <= P.length()-1) {
            // 1. P 에서 길이 지정해서 비교하기
            int tidx = -1;
            for (int i = Pidx; i < P.length(); i++) {
                String str = P.substring(Pidx, i+1);

                // 2. S에서 찾을 수 있는 패턴이면 Pidx 중가
                if (S.contains(str)) tidx = i+1;
                else break;
            }
            Pidx = tidx;
            ans++;
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}