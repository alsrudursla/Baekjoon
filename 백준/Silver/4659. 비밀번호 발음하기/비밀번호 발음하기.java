import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();

        while (!input.equals("end")) {
            // end 전까지만 처리

            boolean chk = true;
            // 1. 모음 하나 포함 여부 확인하기
            for (int i = 0; i < input.length(); i++) {
                char alpha = input.charAt(i);
                if (alpha == 'a' || alpha == 'e' || alpha == 'i' || alpha == 'o' || alpha == 'u') {
                    chk = true;
                    break;
                } else chk = false;
            }

            if (chk) {
                // 2. 모음 3개 혹은 자음 3개 연속 불가
                int case1 = 0; // 연속된 모음
                int case2 = 0; // 연속된 자음
                int before = -1; // 0 모음 1 자음
                for (int i = 0; i < input.length(); i++) {
                    char alpha = input.charAt(i);
                    if (before == -1) { // 처음 시작
                        if (alpha == 'a' || alpha == 'e' || alpha == 'i' || alpha == 'o' || alpha == 'u') {
                            case1++;
                            before = 0;
                        } else {
                            case2++;
                            before = 1;
                        }
                    } else if (before == 0) { // 전 알파벳이 모음일 때
                        if (alpha == 'a' || alpha == 'e' || alpha == 'i' || alpha == 'o' || alpha == 'u') {
                            // 연속된 모음
                            case1++;
                        } else {
                            case1 = 0;
                            case2++;
                            before = 1;
                        }
                    } else if (before == 1) {
                        if (alpha == 'a' || alpha == 'e' || alpha == 'i' || alpha == 'o' || alpha == 'u') {
                            // 바뀜
                            case2 = 0;
                            case1++;
                            before = 0;
                        } else {
                            case2++;
                        }
                    }

                    if (case1 == 3 || case2 == 3) {
                        chk = false;
                        break;
                    }
                }
            }

            if (chk) {
                // 3. 같은 글자 연속적으로 2번 불가 but ee/oo 허용
                char before = 'A';
                for (int i = 0; i < input.length(); i++) {
                    char alpha = input.charAt(i);
                    if (before == 'A') before = alpha;
                    else if (before == alpha) {
                        // 연속된 경우
                        if (alpha == 'e' || alpha == 'o') continue;
                        else {
                            chk = false;
                            break;
                        }
                    } else {
                        before = alpha;
                    }
                }
            }

            if (chk) bw.write("<" + input + "> is acceptable.\n");
            else bw.write("<" + input + "> is not acceptable.\n");

            input = br.readLine();
        }
        bw.flush();
        bw.close();
    }
}