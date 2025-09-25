import java.util.*;
class Solution {
    int sheep_max;
    public int solution(int[] info, int[][] edges) {
        // 1. 트리 만들기
        ArrayList<Integer>[] tree = new ArrayList[info.length];
        for (int i = 0; i < tree.length; i++) tree[i] = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int parent_node = edges[i][0];
            int child_node = edges[i][1];
            tree[parent_node].add(child_node);
        }
        
        // 2. DFS 돌기
        sheep_max = 0;
        List<Integer> candidates = new ArrayList<>();
        candidates.addAll(tree[0]);
        dfs(tree, info, 1, 0, candidates);
        return sheep_max;
    }
    
    private void dfs(ArrayList<Integer>[] tree, int[] info, int now_sheep, int now_wolf, List<Integer> candidates) {
        sheep_max = Math.max(sheep_max, now_sheep);
        
        for (int i = 0; i < candidates.size(); i++) {
            int now_node = candidates.get(i);
            List<Integer> new_candidates = new ArrayList<>();
            for (int j = 0; j < candidates.size(); j++) {
                if (j == i) continue;
                new_candidates.add(candidates.get(j));
            }
            new_candidates.addAll(tree[now_node]);
            
            if (info[now_node] == 0) { // 양일 때
                dfs(tree, info, now_sheep+1, now_wolf, new_candidates);
            } else {
                // 늑대일 때 : 추가했을 때 현재 양의 수보다 많아지면 안됨
                if (now_sheep > now_wolf+1) {
                    dfs(tree, info, now_sheep, now_wolf+1, new_candidates);
                }
            }
        }
    }
}