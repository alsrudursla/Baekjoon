/*
- DFS
- 두 가지 경우를 모두 실행
*/
class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, target, 0);
        return answer;
    }
    
    private static void dfs(int[] numbers, int depth, int target, int sum) {
        if (depth == numbers.length) {
            if (sum == target) {
                answer++;
                return;
            }
        } else {
            dfs(numbers, depth+1, target, sum+numbers[depth]);
            dfs(numbers, depth+1, target, sum-numbers[depth]);
        }
    }
}