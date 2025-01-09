/* 
- 구명 보트는 최대 2명씩밖에 탈 수 없으니 배열을 정렬하여 가장 적은 몸무게인 사람 + 가장 큰 몸무게인 사람을 비교하여 경우의 수를 계산한다.
- 인원 제한이 없으면, 가장 가벼운 사람부터 최대한 많이 태우기
*/
import java.util.*;
class Solution {
    public int solution(int[] people, int limit) { // 사람 몸무게 배열, 무게 제한
        int people_num = people.length; // 사람 수
        int answer = 0; // 필요한 구명보트 개수의 최솟값

        Arrays.sort(people); // 몸무게 오름차순 정렬
        
        int left = 0;
        int right = people_num-1;
        while (left <= right) {
            int now_weight = people[left] + people[right]; // 현재 몸무게
            
            if (now_weight <= limit) { // 둘 다 태울 수 있음
                answer++;
                left++;
                right--;
            } else { // 현재 가장 큰 몸무게를 가진 사람은 혼자 탈 수 밖에 없음
                answer++;
                right--;
            }
        }
        
        return answer;
    }
}