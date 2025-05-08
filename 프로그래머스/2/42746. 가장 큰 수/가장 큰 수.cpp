#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(string a, string b) {
    return a + b > b + a;
}

string solution(vector<int> numbers) {
    vector<string> strvec;
    for (int n : numbers) strvec.push_back(to_string(n));
    
    sort(strvec.begin(), strvec.end(), compare);
    
    // 가장 큰 숫자부터 정렬했는데도 첫 번째가 "0"이면 → 전부 0이라는 뜻이므로, "0"만 리턴
    if (strvec[0] == "0") return "0";
    
    string answer = "";
    for (string s : strvec) answer += s;
    return answer;
}