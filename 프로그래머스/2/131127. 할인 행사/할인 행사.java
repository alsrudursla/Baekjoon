class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        // discount 배열 돌기
        for (int i = 0; i < discount.length; i++) {
            int[] copy_number = number.clone();
            
            // 10일이 될 수 없으면 그만 돌기
            if (i > discount.length - 10) break;
            
            // 10일씩 할인 제품 확인
            for (int j = 0; j < 10; j++) {
                String now_product = discount[i+j]; // 현재 할인 제품
                
                // 내가 원하는 제품 목록에 있는지 확인
                for (int k = 0; k < want.length; k++) {
                    if (want[k].equals(now_product)) {
                        copy_number[k]--;
                        break;
                    }
                }
            }
            
            // 필요한 제품이 모두 있는 기간이면 정답+1
            boolean chk = false;
            for (int l = 0; l < copy_number.length; l++) {
                if (copy_number[l] > 0) {
                    chk = true;
                    break;
                }
            }
            
            if (!chk) answer++;
        }
        
        return answer;
    }
}