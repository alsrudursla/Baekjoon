/*
- 카펫 사이즈 경우의 수를 구하기 위해서 brown 격자 수 + yellow 격자 수의 약수를 구한다.
- 예를 들어, brown = 10,  yellow = 2 이라고 가정할 때, 12의 약수를 구한다 (1, 12) , (2, 6) , (3, 4) , (4, 3) , (6, 2) , (12, 1)
- 가로의 길이가 세로보다 길거나 같다
- 가운데에 노란색 격자가 위치하기 위해선 가로, 세로 길이가 모두 3 이상
- yellow의 개수만큼 노란색 격자가 가운데에 위치할 수 있는지 구해야한다
*/
class Solution {
    public int[] solution(int brown, int yellow) {
        
        int[] answer = new int[2];
        
        int size = brown + yellow;
        
        for (int init_x = 3; init_x <= size; init_x++) {
            int init_y = size/init_x;
            
            if (size % init_x == 0 && init_y >= 3) {
                int x = Math.max(init_x, init_y);
                int y = Math.min(init_x, init_y);
                
                // 노란색 = (전체 가로 - 2) * (전체 세로 = 2)
                int tmp_yellow = (x-2)*(y-2);
                if (tmp_yellow == yellow) {
                    answer[0] = x;
                    answer[1] = y;
                    break;
                }
            }
        }
        
        return answer;
    }
}