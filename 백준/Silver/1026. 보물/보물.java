import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int len = Integer.parseInt(br.readLine());
        int[] A = new int[len];
        PriorityQueue<Integer> Bpq = new PriorityQueue<>((o1, o2) -> o2 - o1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < len; i++) A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < len; i++) Bpq.add(Integer.parseInt(st.nextToken()));

        // S = A[0] × B[0] + ... + A[N-1] × B[N-1]
        // S의 값을 가장 작게 만들기 위해 A의 수를 재배열
        // -> B의 최대값에 A의 최소값을 곱해줘야 함

        // 1. A 정렬
        Arrays.sort(A);

        // 2. B 를 담은 우선순위 큐 : 최대값 우선
        int S = 0;
        for (int i = 0; i < A.length; i++) {
            int Avalue = A[i];
            int Bvalue = Bpq.poll();
            S += (Avalue * Bvalue);
        }

        bw.write(String.valueOf(S));
        bw.flush();
        bw.close();
    }
}