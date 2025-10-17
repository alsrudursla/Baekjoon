import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        double sum = 0.0;
        double creditSum = 0.0;
        for (int i = 0; i < 20; i++) {
            st = new StringTokenizer(br.readLine());
            String subject = st.nextToken();
            double credit = Double.parseDouble(st.nextToken()); // 학점
            String grade = st.nextToken(); // 과목평점

            if (!grade.equals("P")) {
                double translateCredit = 0.0;
                if (grade.equals("A+")) translateCredit = 4.5;
                else if (grade.equals("A0")) translateCredit = 4.0;
                else if (grade.equals("B+")) translateCredit = 3.5;
                else if (grade.equals("B0")) translateCredit = 3.0;
                else if (grade.equals("C+")) translateCredit = 2.5;
                else if (grade.equals("C0")) translateCredit = 2.0;
                else if (grade.equals("D+")) translateCredit = 1.5;
                else if (grade.equals("D0")) translateCredit = 1.0;

                sum += (credit * translateCredit);
                creditSum += credit;
            } 
        }

        double ans = sum / creditSum;
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}