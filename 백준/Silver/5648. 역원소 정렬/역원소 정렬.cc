#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;

    vector<string> arr(n);
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }

    // 1. 뒤집기
    vector<long long int> arr2;
    for (int i = 0; i < n; i++) {
        reverse(arr[i].begin(), arr[i].end());
        while (arr[i][0] == '0') arr[i] = arr[i].substr(1);
        arr2.push_back(stoll(arr[i]));
    }

    // 2. 정렬
    sort(arr2.begin(), arr2.end());

    // 3. 출력
    for (long long int a : arr2) cout << a << '\n';

    return 0;
}