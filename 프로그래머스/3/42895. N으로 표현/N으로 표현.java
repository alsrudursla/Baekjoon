import java.util.*;
class Solution {
    public int solution(int N, int number) {
        if (N == number) return 1;
        
        List<Set<Integer>> dpList = new ArrayList<>();
        for (int i = 0; i <= 8; i++) dpList.add(new HashSet<>());
        dpList.get(1).add(N);
        
        int tmp = N;
        for (int i = 2; i <= 8; i++) {
            Set<Integer> now = dpList.get(i);
            
            int repeated = tmp * 10 + N;
            tmp = repeated;
            if (repeated == number) return i;
            now.add(repeated);
            
            for (int j = 1; j < i; j++) {
                for (int a : dpList.get(j)) {
                    for (int b : dpList.get(i-j)) {
                        now.add(a+b);
                        now.add(a-b);
                        now.add(a*b);
                        if (b != 0) now.add(a/b);
                    }
                }
            }
            
            if (now.contains(number)) return i;
        }
        return -1;
    }
}

/*
1. N을 1개 사용할 때 만들 수 있는 수
ex) 5
2. N을 2개 사용
ex) 55, 5+5(10), 5/5(1), 5*5(25), 5-5(0) : 1의 경우에 사칙연산 & 5*10+5
3. N을 3개 사용
ex) 555, 5+5+5(15), 5+5-5(5), (5+5)*5(50)3
: 2의 경우에 각각 사칙연산 & 수*10+수
*/