#include <iostream>
#include <string>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    // 주어진 문서
    string text;
    getline(cin, text);

    // 검색하고 싶은 단어
    string word;
    getline(cin, word);

    int ans = 0;
    for (int i = 0; i < text.length(); i++) {
        int len = word.length();
        string sub = text.substr(i, len);
        if (word == sub) {
            ans++;
            i = i+len-1;
        }
    }

    cout << ans;
    return 0;
}