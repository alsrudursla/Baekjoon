import java.util.*;
class Solution {
    HashMap<String, Integer> menuMap;
    public String[] solution(String[] orders, int[] course) {
        menuMap = new HashMap<>();
        boolean[] visited;
        
        // 1. 손님 별로 확인하기
        for (int i = 0; i < orders.length; i++) {
            // 2. 조합 가능 수 대로 확인하기
            for (int j = 0; j < course.length; j++) {
                visited = new boolean[orders[i].length()];
                dfs(orders[i], course[j], visited, 0, "");
            }
        }
        
        // 3. 가장 많이 함께 주문된 단품메뉴 조합 뽑기
        Map<Integer, Integer> menuCnt = new HashMap<>(); // 단품개수, 최대
        for (String menu : menuMap.keySet()) {
            if (menuMap.get(menu) >= 2) menuCnt.put(menu.length(), Math.max(menuCnt.getOrDefault(menu.length(), 0),menuMap.get(menu)));
        }
        
        // 4. 저장하기
        List<String> ansList = new ArrayList<>();
        for (String menu : menuMap.keySet()) {
            int key = menu.length();
            if (menuMap.get(menu) == menuCnt.get(key)) ansList.add(menu);
        }
        
        String[] answer = new String[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) answer[i] = ansList.get(i);
        Arrays.sort(answer);
        return answer;
    }
    
    private void dfs(String order, int count, boolean[] visited, int idx, String now) {
        // 종료 조건
        if (now.length() == count) {
            // now 오름차순 정렬
            char[] tmpArr = now.toCharArray();
            Arrays.sort(tmpArr);
            String result = new String(tmpArr);
            
            menuMap.put(result, menuMap.getOrDefault(result, 0) + 1);
            return;
        }
        
        for (int i = idx; i < order.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                now += order.charAt(i);
                dfs(order, count, visited, i+1, now);
                now = now.substring(0, now.length()-1);
                visited[i] = false;
            }
        }
    }
}