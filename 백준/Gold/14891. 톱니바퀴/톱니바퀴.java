import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<int[]> wheel = new ArrayList<>();
        // 12시 방향 인덱스 : 0, 3시 방향 : 2, 6시 방향 : 4, 9시 방향 : 6
        for (int i = 0; i < 4; i++) {
            String cogwheel = br.readLine();
            int[] tmp = new int[cogwheel.length()];
            for (int j = 0; j < cogwheel.length(); j++) {
                tmp[j] = Integer.parseInt(String.valueOf(cogwheel.charAt(j)));
            }
            wheel.add(tmp);
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int wheel_num = Integer.parseInt(st.nextToken()) - 1;
            int wheel_dir = Integer.parseInt(st.nextToken());

            // 1. 톱니바퀴 별 상태 확인 - 회전할 톱니바퀴 체크 [ 톱니바퀴 번호, 회전 방향 ]
            List<int[]> rotateList = chkWheelStatus(wheel, wheel_num, wheel_dir);

            // 2. 톱니바퀴 회전
            rotateWheel(rotateList, wheel);
        }

        // 3. 톱니바퀴 점수 합
        int score = 0;
        if (wheel.get(0)[0] == 1) score += 1;
        if (wheel.get(1)[0] == 1) score += 2;
        if (wheel.get(2)[0] == 1) score += 4;
        if (wheel.get(3)[0] == 1) score += 8;

        bw.write(String.valueOf(score));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void rotateWheel(List<int[]> rotateList, List<int[]> wheel) {
        for (int i = 0; i < rotateList.size(); i++) {
            int wheel_num = rotateList.get(i)[0];
            int wheel_dir = rotateList.get(i)[1];

            // 시계 방향 : 1, 반시계 방향 : -1
            if (wheel_dir == 1) {
                int tmp = wheel.get(wheel_num)[7];
                for (int j = wheel.get(wheel_num).length-1; j >= 1 ; j--) {
                    wheel.get(wheel_num)[j] = wheel.get(wheel_num)[j-1];
                }
                wheel.get(wheel_num)[0] = tmp;
            } else {
                int tmp = wheel.get(wheel_num)[0];
                for (int j = 0; j < wheel.get(wheel_num).length-1; j++) {
                    wheel.get(wheel_num)[j] = wheel.get(wheel_num)[j+1];
                }
                wheel.get(wheel_num)[7] = tmp;
            }
        }

    }

    private static List<int[]> chkWheelStatus(List<int[]> wheel, int wheel_num, int wheel_dir) {
        // 전체 톱니바퀴 리스트, 회전할 톱니바퀴 번호, 회전하는 톱니바퀴 방향
        List<int[]> rotateList = new ArrayList<>();
        rotateList.add(new int[]{wheel_num, wheel_dir});

        Queue<int[]> myqueue = new LinkedList<>(); // 톱니바퀴 번호, 회전 방향
        boolean[] visited = new boolean[wheel.size()];
        myqueue.add(new int[]{wheel_num, wheel_dir});
        visited[wheel_num] = true;

        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int now_wheel = now[0];
            int now_dir = now[1];
            int now_left = wheel.get(now_wheel)[6];
            int now_right = wheel.get(now_wheel)[2];

            // 왼쪽에 바퀴가 있을 때
            int left_wheel_idx = now_wheel-1;
            if (0 <= left_wheel_idx && left_wheel_idx <= 3 && !visited[left_wheel_idx]) {
                if (wheel.get(left_wheel_idx)[2] != now_left) {
                    // 다른 극
                    visited[left_wheel_idx] = true;
                    myqueue.add(new int[]{left_wheel_idx, -now_dir});
                    rotateList.add(new int[]{left_wheel_idx, -now_dir});
                }
            }

            // 오른쪽에 바퀴가 있을 때
            int right_wheel_idx = now_wheel+1;
            if (0 <= right_wheel_idx && right_wheel_idx <= 3 && !visited[right_wheel_idx]) {
                if (wheel.get(right_wheel_idx)[6] != now_right) {
                    // 다른 극
                    visited[right_wheel_idx] = true;
                    myqueue.add(new int[]{right_wheel_idx, -now_dir});
                    rotateList.add(new int[]{right_wheel_idx, -now_dir});
                }
            }
        }

        return rotateList;
    }
}