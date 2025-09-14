import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int stoneCnt = Integer.parseInt(br.readLine());

        // 3개를 가져가는 것 = 1개씩 돌아가면서 가져간 거랑 경우가 같음
        // -> 3으로 나눈 나머지 돌을 통해 승자를 결정
        // 홀수 턴일 때 0 : 상근, 1 : 창영, 2 : 상근

        if (stoneCnt < 3) {
            if (stoneCnt == 1) bw.write("SK");
            else bw.write("CY");
        } else {
            int turn = (stoneCnt / 3);
            int rest = (stoneCnt % 3);
            if (turn % 2 == 0) {
                if (rest == 0 || rest == 2) bw.write("CY");
                else bw.write("SK");
            } else {
                if (rest == 0 || rest == 2) bw.write("SK");
                else bw.write("CY");
            }
        }
        
        bw.flush();
        bw.close();
    }
}