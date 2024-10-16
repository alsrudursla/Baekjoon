/*
- 양쪽에서 각각 한개의 숫자를 뽑아 곱한 값을 누적하였을 때 한 쪽의 최댓값과 다른 쪽의 최솟값을 곱하는 경우 누적값을 최소로 구할 수 있다
- 배열 정렬 : Arrays.sort() // java.util.Array
*/

import java.io.*;
import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int answer = 0;
        for (int i = 0; i < A.length; i++) {
            // A 최솟값 * B 최댓값
            int tmp = A[i] * B[A.length - i - 1];
            answer += tmp;
        }
        
        return answer;
    }
}