import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();

        List<int[]> idxNval = new ArrayList<>(); // 인덱스, 값
        int[] arr = new int[testcase];
        for (int i = 0; i < testcase; i++) {
            int value = sc.nextInt();
            idxNval.add(new int[]{i, value});
            arr[i] = value;
        }
        Collections.sort(idxNval, (o1, o2) -> o1[1] - o2[1]);

        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            // 이 수를 만들 수 있는지 찾기
            int target = arr[i];
            int targetIdx = i;
            
            int sidx = 0;
            int eidx = testcase-1;
            while (sidx < eidx) {
                if (idxNval.get(sidx)[0] == targetIdx) sidx++;
                else if (idxNval.get(eidx)[0] == targetIdx) eidx--;
                else {
                    long sum = (long)idxNval.get(sidx)[1] + (long)idxNval.get(eidx)[1];
                    if (sum > (long)target) eidx--;
                    else if (sum < (long)target) sidx++;
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