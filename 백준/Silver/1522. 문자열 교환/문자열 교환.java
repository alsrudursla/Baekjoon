import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();

        int aTotal = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'a') aTotal++;
        }

        if (aTotal == 0 || aTotal == input.length()) bw.write("0");
        else {
            String circleInput = input + input;
            int bSmallest = Integer.MAX_VALUE;
            int start = 0;
            int size = 0;
            int bCnt = 0;
            for (int end = 0; end < circleInput.length(); end++) {
                if (size < aTotal-1) {
                    if (circleInput.charAt(end) == 'b') bCnt++;
                    size++;
                } else {
                    // end 인덱스 값 추가
                    if (circleInput.charAt(end) == 'b') bCnt++;
                
                    // 최소 B 개수 업데이트
                    bSmallest = Math.min(bSmallest, bCnt);

                    // start 증가
                    if (circleInput.charAt(start) == 'b') bCnt--;
                    start++;
                }
            }
        
            bw.write(String.valueOf(bSmallest));
        }
        bw.flush();
        bw.close();
    }
}