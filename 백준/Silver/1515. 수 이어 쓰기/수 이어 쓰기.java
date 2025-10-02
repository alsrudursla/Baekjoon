import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();

        int idx = 0;
        int num = 0;
        while (idx < input.length()) {
            num++;

            String numS = String.valueOf(num);
            boolean chk = false;
            for (int i = 0; i < numS.length(); i++) {
                if (idx >= input.length()) break;
                if (input.charAt(idx) == numS.charAt(i)) {
                    idx++;
                }
            }

            if (idx >= input.length()) break;
        }

        bw.write(String.valueOf(num));
        bw.flush();
        bw.close();
    }
}