import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();

        int zeroCnt = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '0') zeroCnt++;
        }

        // 가장 빠른 것 = 0이 모두 앞에 있고 1이 뒤에 몰려있음
        // 짝수 개의 0과 1 -> 둘 중 하나는 무조건 있지만 한 종류는 없을 수도 있음
        if (zeroCnt == 0) {
            // 모두 1로 구성됨
            for (int i = 0; i < input.length()/2; i++) bw.write("1");
        } else {
            if (zeroCnt == input.length()) {
                // 모두 0으로 구성됨
                for (int i = 0; i < input.length()/2; i++) bw.write("0");
            } else {
                int newZeroCnt = zeroCnt / 2;
                int newOneCnt = input.length() / 2 - newZeroCnt;
                // 1은 앞에서부터 지우고, 0은 뒤에서부터 지우기
                int cnt = 0;
                StringBuffer sb = new StringBuffer();
                for (int i = input.length()-1; i >= 0; i--) {
                    if (input.charAt(i) == '0' && cnt != newZeroCnt) cnt++;
                    else sb.append(input.charAt(i));
                }
                
                String tmpInput = sb.reverse().toString();
                StringBuffer sb2 = new StringBuffer();
                cnt = 0;
                for (int i = 0; i < tmpInput.length(); i++) {
                    if (tmpInput.charAt(i) == '1' && cnt != newOneCnt) cnt++;
                    else sb2.append(tmpInput.charAt(i));
                }
                bw.write(sb2.toString());
            }
        }
        
        bw.flush();
        bw.close();
    }
}