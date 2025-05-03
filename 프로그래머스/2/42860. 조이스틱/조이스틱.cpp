#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(string name) {
    int answer = 0;
    
    // 1. 알파벳마다 상하 최소 이동 먼저 계산
    for (int i = 0; i < name.length(); i++) {
        char target = name[i];
        answer += min(target - 'A', 1 + 'Z' - target);
    }
    
    // 2. 좌우 최소 이동 계산
    int tmp = name.length();
    for (int i = 0; i < name.length(); i++) {
        int next_i = i+1;
        while (next_i < name.length() && name[next_i] == 'A') {
            next_i++;
        }
        int move = i*2 + name.length() - next_i;
        tmp = min(tmp, move);
        
        move = (name.length()-next_i)*2 + i;
        tmp = min(tmp, move);
    }
    
    answer += tmp;
    return answer;
}