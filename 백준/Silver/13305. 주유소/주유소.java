import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int cityCnt = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] cityDistance = new int[cityCnt-1];
        for (int i = 0; i < cityCnt-1; i++) cityDistance[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] oilPrice = new int[cityCnt];
        for (int i = 0; i < cityCnt; i++) oilPrice[i] = Integer.parseInt(st.nextToken());

        int lowestNode = findLowestNode(oilPrice);
        int now_node = 0;
        int totalPrice = 0;
        while (now_node < cityCnt-1) {
            // 내가 가장 적은 주유비일 때
            if (now_node == lowestNode) {
                totalPrice += calculateOilLeter(now_node, cityCnt-1, cityDistance) * oilPrice[now_node];
                //System.out.println(now_node + " " + totalPrice);
                break;
            } else {
                // 다음으로 가장 적은 노드까지 이동하기
                int nextLowestNode = findNextLowestNode(now_node, oilPrice);

                totalPrice += calculateOilLeter(now_node, nextLowestNode, cityDistance) * oilPrice[now_node];
                //System.out.println(now_node + " " + totalPrice);
                now_node = nextLowestNode;
            }
        }

        bw.write(String.valueOf(totalPrice));
        bw.flush();
        bw.close();
    }

    private static int findNextLowestNode(int start_node, int[] oilPrice) {
        for (int i = start_node+1; i < oilPrice.length-1; i++) {
            if (oilPrice[i] < oilPrice[start_node]) return i;
        }
        return start_node;
    }

    private static int findLowestNode(int[] oilPrice) {
        int lowestPrice = Integer.MAX_VALUE;
        int nodeIdx = -1;
        for (int i = 0; i < oilPrice.length-1; i++) {
            if (oilPrice[i] < lowestPrice) {
                lowestPrice = oilPrice[i];
                nodeIdx = i;
            }
        }
        return nodeIdx;
    }

    private static int calculateOilLeter(int start_node, int end_node, int[] cityDistance) {
        int total = 0;
        for (int i = start_node; i < end_node; i++) total += cityDistance[i];
        return total;
    }
}