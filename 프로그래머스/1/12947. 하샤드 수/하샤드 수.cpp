bool solution(int x) {
    int sum = 0;
    int tmp = x;
    while (tmp > 0) {
        int num = tmp % 10;
        sum += num;
        tmp = tmp / 10;
    }

    if (x % sum == 0) return true;
    else return false;
}