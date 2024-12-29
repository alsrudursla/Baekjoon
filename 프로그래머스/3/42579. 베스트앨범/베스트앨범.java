import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // genres : 노래 장르, plays : 노래 재생 횟수 (중복X)
        // 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시
        
        // <장르, 재생 횟수>
        HashMap<String, Integer> songs = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            songs.put(genres[i], songs.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        // 재생 횟수로 내림차순 정렬
        List<String> tmp = new ArrayList<>(songs.keySet());
        tmp.sort((o1,o2) -> songs.get(o2)- songs.get(o1));
        
        List<Integer> album = new ArrayList<>();
        for (String genre : tmp) {
            // 1. 속한 노래가 많이 재생된 장르를 먼저 수록            
            // 2. 장르 내에서 많이 재생된 노래를 먼저 수록
            // 3. 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
            List<int[]> tmp_songs = new ArrayList<>(); // plays, i

            for (int j = 0; j < genres.length; j++) {
                if (genres[j].equals(genre)) {
                    tmp_songs.add(new int[]{plays[j], j});
                }
            }
            
            tmp_songs.sort((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]);
            
            int chk = 0;
            for (int[] i : tmp_songs) {
                if (chk == 2) break;
                album.add(i[1]);
                chk++;
            }
        }
        
        int[] ans = new int[album.size()];
        for (int i = 0; i < album.size(); i++) {
            ans[i] = album.get(i);
        }
        return ans;
    }
}