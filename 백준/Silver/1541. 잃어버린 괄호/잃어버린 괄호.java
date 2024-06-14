import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splitBr = br.readLine().split("-");

        int answer = 0;
        for (int i = 0; i < splitBr.length; i++) {
            String[] addNum = splitBr[i].split("[+]");
            int sum = 0;
            for (int j = 0; j < addNum.length; j++) {
                sum += Integer.parseInt(addNum[j]);
            }

            if (i == 0) {
                answer += sum;
            }
            else {
                answer -= sum;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(answer));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}