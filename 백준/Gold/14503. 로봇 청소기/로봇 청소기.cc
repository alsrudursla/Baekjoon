#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;

int N, M;
int startX, startY, startDir;
vector<vector<int>> map;
int dy[4] = {-1, 0, 1, 0}; // 북 동 남 서
int dx[4] = {0, 1, 0, -1};

int bfs() {
    queue<tuple<int, int, int>> myqueue;
    vector<vector<bool>> visited(N, vector<bool>(M, false));

    myqueue.push(make_tuple(startY, startX, startDir));
    int ans = 0;

    while (!myqueue.empty()) {
        tuple<int, int, int> now = myqueue.front();
        myqueue.pop();

        int nowY = get<0>(now);
        int nowX = get<1>(now);
        int nowDir = get<2>(now);

        // 청소되지 않은 경우
        if (!visited[nowY][nowX]) {
            visited[nowY][nowX] = true;
            ans++;
        }

        bool chk = false;
        for (int k = 0; k < 4; k++) {
            int nextY = nowY + dy[k];
            int nextX = nowX + dx[k];

            if (0 <= nextY && nextY < N && 0 <= nextX && nextX < M) {
                // 청소되지 않은 빈 칸 존재
                if (!visited[nextY][nextX] && map[nextY][nextX] == 0) {
                    chk = true;
                }
            }
        }

        // 청소되지 않은 빈 칸이 없을 경우
        if (!chk) {
            // 1. 방향 유지, 한 칸 후진
            int nextY = nowY + dy[(nowDir+2) % 4];
            int nextX = nowX + dx[(nowDir+2) % 4];

            // 범위 내, 빈 칸
            if (0 <= nextY && nextY < N && 0 <= nextX && nextX < M && map[nextY][nextX] == 0) {
                myqueue.push(make_tuple(nextY, nextX, nowDir));
            }
        } else {
            // 청소되지 않은 빈 칸 있을 경우
            for (int k = 0; k < 4; k++) {
                nowDir = (nowDir+3)%4;

                int nextY = nowY + dy[nowDir];
                int nextX = nowX + dx[nowDir];
                if (0 <= nextY && nextY < N && 0 <= nextX && nextX < M && !visited[nextY][nextX] && map[nextY][nextX] == 0) {
                    myqueue.push(make_tuple(nextY, nextX, nowDir));
                    break;
                }
            }
        }
    }

    return ans;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;
    cin >> startY >> startX >> startDir;

    map.resize(N, vector<int>(M));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> map[i][j];
        }
    }

    int ans = bfs();
    cout << ans;

    return 0;
}