import java.io.*;
import java.util.*;
public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n+1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (num == 0) {
                // 대표 노드 값으로 변경
                change_val(a, b);
            } else {
                // 대표 노드 값이 같은지 확인
                a = find(a);
                b = find(b);

                if (a == b) {
                    bw.write("YES\n");
                } else {
                    bw.write("NO\n");
                }
            }
            bw.flush();
        }
        bw.close();
    }

    private static void change_val(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            arr[b] = a;
        }
    }

    private static int find(int a) {
        if (a == arr[a]) {
            return a;
        } else {
            return arr[a] = find(arr[a]);
        }
    }
}