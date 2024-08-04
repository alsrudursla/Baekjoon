import java.io.*;
import java.util.*;

public class Main {
    static int[][] tree; // 노드 저장할 2차원 배열
    static BufferedWriter bw;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int node_num = Integer.parseInt(br.readLine());

        tree = new int[26][2]; // 알파벳 개수 26개

        StringTokenizer st;
        for (int i = 0; i < node_num; i++) {
            st = new StringTokenizer(br.readLine());
            char node = st.nextToken().charAt(0);
            char child_left = st.nextToken().charAt(0);
            char child_right = st.nextToken().charAt(0);

            int now_index = node - 'A'; // 인덱스로 변환
            
            if (child_left == '.') { // 왼쪽 자식 노드 없으면 -1 저장
                tree[now_index][0] = -1;
            } else {
                tree[now_index][0] = child_left - 'A';
            }
            
            if (child_right == '.') { // 오른쪽 자식 노드 없으면 -1 저장
                tree[now_index][1] = -1;
            } else {
                tree[now_index][1] = child_right - 'A';
            }
        }
        
        preOrder(0);
        bw.newLine();
        
        inOrder(0);
        bw.newLine();
        
        postOrder(0);
        bw.flush();
        bw.close();
    }
    
    private static void preOrder(int idx) throws IOException { // 현재 → 왼쪽 → 오른쪽
        if (idx == -1) {
            return;
        }
        char now_value = (char) (idx + 'A'); // 다시 문자로 형변환

        bw.write(String.valueOf(now_value));
        preOrder(tree[idx][0]);
        preOrder(tree[idx][1]);
    }
    
    private static void inOrder(int idx) throws IOException { // 왼쪽 → 현재 → 오른쪽
        if (idx == -1) {
            return;
        }
        char now_value = (char) (idx + 'A');

        inOrder(tree[idx][0]);
        bw.write(String.valueOf(now_value));
        inOrder(tree[idx][1]);
    }
    
    private static void postOrder(int idx) throws IOException { // 왼쪽 → 오른쪽 → 현재
        if (idx == -1) {
            return;
        }
        char now_value = (char) (idx + 'A');

        postOrder(tree[idx][0]);
        postOrder(tree[idx][1]);
        bw.write(String.valueOf(now_value));
    }
}