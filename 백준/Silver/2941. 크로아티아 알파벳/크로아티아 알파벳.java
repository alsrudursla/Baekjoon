import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();

        HashSet<String> croatia = new HashSet<>();
        croatia.add("c=");
        croatia.add("c-");
        croatia.add("dz=");
        croatia.add("d-");
        croatia.add("lj");
        croatia.add("nj");
        croatia.add("s=");
        croatia.add("z=");

        int idx = 0;
        int answer = 0;
        while (idx < word.length()) {
            // 길이가 2인 크로아티아 문자 확인
            if (idx+1 < word.length()) {
                String chkStrLen2 = word.substring(idx, idx+2);
                if (croatia.contains(chkStrLen2)) {
                    //System.out.println(chkStrLen2);
                    answer++;
                    idx += 2;
                    continue;
                }
            }

            // 길이가 3인 크로아티아 문자 확인
            if (idx+2 < word.length()) {
                String chkStrLen3 = word.substring(idx, idx+3);
                if (croatia.contains(chkStrLen3)) {
                    //System.out.println(chkStrLen3);
                    answer++;
                    idx += 3;
                    continue;
                }
            }

            // 크로아티아 문자 아닐 때
            //System.out.println(word.charAt(idx));
            answer++;
            idx++;
        }

        System.out.println(answer);
        sc.close();
    }
}