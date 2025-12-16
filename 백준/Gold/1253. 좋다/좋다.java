import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();

        int[] arr = new int[testcase];
        for (int i = 0; i < testcase; i++) arr[i] = sc.nextInt();
        Arrays.sort(arr);

        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            // 이 수를 만들 수 있는지 찾기
            int target = arr[i];
            int targetIdx = i;
            
            int sidx = 0;
            int eidx = testcase-1;
            while (sidx < eidx) {
                // 내 인덱스는 넘기기
                if (sidx == targetIdx) sidx++;
                else if (eidx == targetIdx) eidx--;
                else {
                    long sum = arr[sidx] + (long)arr[eidx];
                    if (sum > target) eidx--;
                    else if (sum < target) sidx++;
                    else {
                        answer++;
                        break;
                    }
                }
                
            }
        }

        System.out.println(answer);
        sc.close();
    }
}