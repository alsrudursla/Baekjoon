import java.util.*;
import java.io.*;

class Main {
    static class Node {
        int idx;
        Node next;
        Node (int idx) {this.idx = idx;}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int peopelCnt = Integer.parseInt(br.readLine());
        int[] parr = new int[peopelCnt];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < peopelCnt; i++) parr[i] = Integer.parseInt(st.nextToken());

        // 키 큰 사람부터 처리
        Node head = null; // 맨 앞 노드
        for (int i = parr.length-1; i >= 0; i--) {
            Node newNode = new Node(i+1);
            int pos = parr[i];

            if (pos == 0) {
                newNode.next = head;
                head = newNode;
            } else {
                Node cur = head;
                for (int j = 0; j < pos-1; j++) cur = cur.next;
                newNode.next = cur.next;
                cur.next = newNode;
            }
        }
        
        while (head != null) {
            bw.write(head.idx + " ");
            head = head.next;
        }

        bw.flush();
        bw.close();
    }
}