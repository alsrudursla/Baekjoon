import java.util.*;
import java.io.*;

class Main {
    static class Alpha {
        int cnt;
        List<Integer> idxList;
        Alpha (int cnt, List<Integer> idxList) {
            this.cnt = cnt;
            this.idxList = idxList;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testcase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testcase; i++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            List<Alpha> alphaList = new ArrayList<>();
            for (int a = 0; a < 26; a++) alphaList.add(new Alpha(0, new ArrayList<>()));

            // 어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이
            int shortest = Integer.MAX_VALUE;
            for (int j = 0; j < W.length(); j++) {
                int nowIdx = W.charAt(j) - 'a';
                
                if (alphaList.get(nowIdx).cnt < K) {
                    alphaList.get(nowIdx).cnt++;
                    alphaList.get(nowIdx).idxList.add(j);

                    if (alphaList.get(nowIdx).cnt == K) {
                        int sidx = alphaList.get(nowIdx).idxList.get(0);
                        int len = j - sidx + 1;
                        shortest = Math.min(shortest, len);
                    }
                } else {
                    // 이미 K개가 있을 때, 새로 갱신
                    alphaList.get(nowIdx).idxList.remove(0); // 맨 앞 인덱스 삭제
                    alphaList.get(nowIdx).idxList.add(j);

                    int sidx = alphaList.get(nowIdx).idxList.get(0);
                    int len = j - sidx + 1;
                    shortest = Math.min(shortest, len);
                }
            }

            if (shortest == Integer.MAX_VALUE) {
                bw.write("-1\n");
                continue;
            } else bw.write(shortest + " ");

            alphaList = new ArrayList<>();
            for (int a = 0; a < 26; a++) alphaList.add(new Alpha(0, new ArrayList<>()));

            // 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이
            int longest = -1;
            for (int j = 0; j < W.length(); j++) {
                int nowIdx = W.charAt(j) - 'a';
                
                if (alphaList.get(nowIdx).cnt < K) {
                    alphaList.get(nowIdx).cnt++;
                    alphaList.get(nowIdx).idxList.add(j);

                    if (alphaList.get(nowIdx).cnt == K) {
                        int sidx = alphaList.get(nowIdx).idxList.get(0);
                        int len = j - sidx + 1;
                        longest = Math.max(longest, len);
                    }
                } else {
                    // 이미 K개가 있을 때, 새로 갱신
                    alphaList.get(nowIdx).idxList.remove(0); // 맨 앞 인덱스 삭제
                    alphaList.get(nowIdx).idxList.add(j);

                    int sidx = alphaList.get(nowIdx).idxList.get(0);
                    int len = j - sidx + 1;
                    longest = Math.max(longest, len);
                }
            }
            
            bw.write(longest + "\n");
        }

        bw.flush();
        bw.close();
    }
}