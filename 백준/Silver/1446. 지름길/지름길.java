import java.util.*;
import java.io.*;

class Main {
    static class Shortcut {
        int start, end, diff, length;
        Shortcut(int start, int end, int length) {
            this.start = start;
            this.end = end;
            this.length = length;
        }
    }
    static List<Shortcut> shortcutList;
    static int shortestLen;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int shortcutCnt = Integer.parseInt(st.nextToken());
        int pathLen = Integer.parseInt(st.nextToken());

        shortcutList = new ArrayList<>();
        for (int i = 0; i < shortcutCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            shortcutList.add(new Shortcut(start, end, length));
        }

        Collections.sort(shortcutList, (o1, o2) -> o1.start - o2.start);

        shortestLen = Integer.MAX_VALUE;
        findPath(0, pathLen, 0, 0); // 현재 위치, 총 거리, 움직인 거리, 리스트인덱스

        bw.write(String.valueOf(shortestLen));
        bw.flush();
        bw.close();
    }

    private static void findPath(int nowLen, int totalLen, int moved, int idx) {
        // 종료 조건 : 끝에 도달했을 때 or idx 끝났을 때
        if (nowLen > totalLen) return;
        else if (nowLen == totalLen) {
            shortestLen = Math.min(shortestLen, moved);
            return;
        } else if (idx == shortcutList.size()) {
            shortestLen = Math.min(shortestLen, moved + (totalLen - nowLen));
            return;
        }

        int start = shortcutList.get(idx).start;
        int end = shortcutList.get(idx).end;
        int length = shortcutList.get(idx).length;

        if (nowLen <= start) { // 지름길 입구 전에 있을 때
            int nowMoved = start - nowLen; // 지름길 시작 - 전 위치
            findPath(end, totalLen, moved+nowMoved+length, idx+1);
        }

        findPath(nowLen, totalLen, moved, idx+1);
    }
}