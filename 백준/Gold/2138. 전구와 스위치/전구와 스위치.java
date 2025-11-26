import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String nowBulbStatus = br.readLine();
        String finalBulbStatus = br.readLine();

        boolean[] onNoff = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (nowBulbStatus.charAt(i) == '1') onNoff[i] = true;
        }

        boolean[] answer = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (finalBulbStatus.charAt(i) == '1') answer[i] = true;
        }

        // 0. 처음 상태 점검
        boolean same = true;
        for (int i = 0; i < N; i++) {
            if (onNoff[i] != answer[i]) {
                same = false;
                break;
            }
        }

        if (same) bw.write("0");
        else {
            // 1. 0번 스위치 눌렀을 때
            int firstTry = 0;
            for (int i = 0; i < nowBulbStatus.length(); i++) {
                if (i == 0) { // 0번을 누름
                    firstTry++;
                    onNoff[0] = !onNoff[0];
                    onNoff[1] = !onNoff[1];
                    continue;
                }

                // 바로 앞 전구에 영향을 주는 건 이제 다음 전구 하나 밖에 없음
                // ex. 0번에는 1번만 영향을 줌
                // -> 최종 만들어야 하는 상태랑 다르다면 다음 전구를 눌러야 함
                if (onNoff[i-1] != answer[i-1]) {
                    firstTry++;
                    onNoff[i-1] = !onNoff[i-1];
                    onNoff[i] = !onNoff[i];
                    if (i == nowBulbStatus.length()-1) break;
                    onNoff[i+1] = !onNoff[i+1];
                } // 같으면 안누름
            }

            // 1-1. 최종 상태 비교
            boolean chk = true;
            for (int i = 0; i < N; i++) {
                if (onNoff[i] != answer[i]) {
                    chk = false;
                    break;
                }
            }

            // 2. 0번 스위치를 누르지 않았을 때
            onNoff = new boolean[N];
            for (int i = 0; i < N; i++) {
                if (nowBulbStatus.charAt(i) == '1') onNoff[i] = true;
            }
            
            int secondTry = 0;
            for (int i = 1; i < nowBulbStatus.length(); i++) {
                if (onNoff[i-1] != answer[i-1]) {
                    secondTry++;
                    onNoff[i-1] = !onNoff[i-1];
                    onNoff[i] = !onNoff[i];
                    if (i == nowBulbStatus.length()-1) break;
                    onNoff[i+1] = !onNoff[i+1];
                }
            }

            // 2-1. 최종 상태 비교
            boolean chk2 = true;
            for (int i = 0; i < N; i++) {
                if (onNoff[i] != answer[i]) {
                    chk2 = false;
                    break;
                }
            }

            // 3. 최소 횟수 구하기
            if (chk && chk2) bw.write(String.valueOf(Math.min(firstTry, secondTry)));
            else if (chk) bw.write(String.valueOf(firstTry));
            else if (chk2) bw.write(String.valueOf(secondTry));
            else bw.write("-1");
        }

        bw.flush();
        bw.close();
    }
}