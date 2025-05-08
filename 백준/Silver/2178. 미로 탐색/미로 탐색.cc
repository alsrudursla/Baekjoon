#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;

int dy[4] = {-1, 0, 1, 0};
int dx[4] = {0, 1, 0, -1};
int N, M;
vector<vector<int>> map;

int bfs() {
    queue<tuple<int, int, int>> myqueue;
    vector<vector<bool>> visited(N+1, vector<bool>(M+1, false));

    // (1,1) 에서 출발
    myqueue.push(make_tuple(1,1,1));
    visited[1][1] = true;

    while (!myqueue.empty()) {
        tuple<int, int, int> now = myqueue.front();
        myqueue.pop();
        int nowY = get<0>(now);
        int nowX = get<1>(now);
        int path = get<2>(now);

        if (nowY == N && nowX == M) return path;

        for (int k = 0; k < 4; k++) {
            int nextY = nowY + dy[k];
            int nextX = nowX + dx[k];

            if (1 <= nextY && nextY <= N && 1 <= nextX && nextX <= M && !visited[nextY][nextX]) {
                if (map[nextY][nextX] == 1) {
                    visited[nextY][nextX] = true;
                    myqueue.push(make_tuple(nextY,nextX,path+1));
                }
            }
        }
    }

    return 0;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;

    // 1. 맵 입력받기
    map.resize(N+1, vector<int>(M+1));
    for (int i = 1; i <= N; i++) {
        string now;
        cin >> now;
        for (int j = 1; j <= M; j++) {
            map[i][j] = now[j-1] - '0';
        }
    }

    // 2. 이동
    int ans = bfs();
    cout << ans;

    return 0;
}