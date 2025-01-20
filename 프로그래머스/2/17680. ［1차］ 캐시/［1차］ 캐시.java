import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        // cache hit : 1, cache miss : 5
        int answer = 0;
        
        if (cacheSize == 0) return cities.length * 5;
        
        List<String> city_hit = new LinkedList<>();
        for (int i = 0; i < cities.length; i++) {
            String now_city = cities[i].toLowerCase();
            
            if (city_hit.contains(now_city)) {
                answer += 1;
                city_hit.remove(now_city);
                city_hit.add(now_city);
            } else {
                if (city_hit.size() >= cacheSize) { 
                    city_hit.remove(0);
                }
                city_hit.add(now_city);
                answer += 5;
            }
        }
        
        return answer;
    }
}