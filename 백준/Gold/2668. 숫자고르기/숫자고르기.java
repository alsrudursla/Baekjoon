import java.util.*;
import java.io.*;

class Main {
    static List<Integer> numList;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];

        for (int i = 1; i < arr.length; i++) arr[i] = Integer.parseInt(br.readLine());

        // 사이클로 돌아와야 됨
        numList = new ArrayList<>();
        visited = new boolean[N+1];
        for (int i = 1; i < arr.length; i++) {
            visited[i] = true;
            dfs(i, i, arr);
            visited[i] = false;
        }

        Collections.sort(numList);
        bw.write(numList.size() + "\n");
        for (int n : numList) bw.write(n + "\n");
        bw.flush();
        bw.close();
    }

    private static void dfs(int start, int num, int[] arr) {
        if (start == arr[num]) numList.add(start);
        if (!visited[arr[num]]) {
            visited[arr[num]] = true;
            dfs(start, arr[num], arr);
            visited[arr[num]] = false;
        }
    }
}