import java.util.*;

class Main {
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();

        for (int t = 0; t < testcase; t++) {
            N = sc.nextInt();
            StringBuffer sb = new StringBuffer();
            sb.append(1);
            makeZero(1, sb);
            System.out.println();
        }

        sc.close();
    }

    private static void makeZero(int idx, StringBuffer sb) {
        // 종료 조건 : 현재 수가 N 일 때
        if (idx == N) {
            int tmp = 0;
            int sidx = 0;
            char beforeOp = ' ';
            StringBuffer beforeNum = new StringBuffer();
            while (sidx < sb.length()) {
                char now = sb.charAt(sidx);
                if (now == '+' || now == '-') {
                    if (beforeOp == ' ') tmp = Integer.parseInt(beforeNum.toString());
                    else if (beforeOp == '+') tmp += Integer.parseInt(beforeNum.toString());
                    else if (beforeOp == '-') tmp -= Integer.parseInt(beforeNum.toString());
                    beforeOp = now;
                    beforeNum.setLength(0);
                } else if (now != ' ') beforeNum.append(now);
                sidx++;
            }
            if (beforeOp == '+') tmp += Integer.parseInt(beforeNum.toString());
            else if (beforeOp == '-') tmp -= Integer.parseInt(beforeNum.toString());
            if (tmp == 0 && beforeOp != ' ') System.out.println(sb.toString());
            return;
        }

        StringBuffer sb4 = new StringBuffer();
        sb4.append(sb.toString());
        sb4.append(' ');
        sb4.append(idx+1);
        makeZero(idx+1, sb4);

        StringBuffer sb2 = new StringBuffer();
        sb2.append(sb.toString());
        sb2.append('+');
        sb2.append(idx+1);
        makeZero(idx+1, sb2);

        StringBuffer sb3 = new StringBuffer();
        sb3.append(sb.toString());
        sb3.append('-');
        sb3.append(idx+1);
        makeZero(idx+1, sb3);
    }
}