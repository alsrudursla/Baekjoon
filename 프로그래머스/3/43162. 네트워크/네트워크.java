class Solution {
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        // 네트워크의 개수를 return
        visited = new boolean[n];
        
        int network = 0;
        for (int i = 0; i < n; i++) {
            // i 번 컴퓨터의 연결 정보 확인
            if (visited[i]) continue;
            visited[i] = true;
            
            connect(computers, i);
            network++;
        }
        
        return network;
    }
    
    private void connect(int[][] computers, int number) {
        for (int i = 0; i < computers[number].length; i++) {
            // 해당 컴퓨터 연결 정보 배열 돌기
            int now = computers[number][i];
            if (now == 1 && !visited[i]) {
                visited[i] = true;
                connect(computers, i);
            }
        }
    }
}