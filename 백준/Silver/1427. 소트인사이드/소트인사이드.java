import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        int[] intArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            intArr[i] = arr[i] - '0';
        }

        Arrays.sort(intArr);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int j = intArr.length - 1; j >= 0; j--) {
            bw.write(String.valueOf(intArr[j]));
        }
        bw.newLine();
        bw.flush();
        bw.close();
    }
}