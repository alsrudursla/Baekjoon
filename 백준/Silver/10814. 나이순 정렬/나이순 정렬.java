import java.io.*;
import java.util.*;

class Main {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 회원 수

        PriorityQueue<Person> pq = new PriorityQueue<>((o1, o2) ->
            o1.age != o2.age ? Integer.compare(o1.age, o2.age) : Integer.compare(o1.number, o2.number)); // 나이, 이름

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            pq.add(new Person(age, name, i));
        }

        for (int i = 0; i < N; i++) {
            Person now = pq.poll();
            int now_age = now.age;
            String now_name = now.name;
            bw.write(now_age + " " + now_name);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static class Person {
        private int age;
        private String name;
        private int number;

        Person (int age, String name, int number) {
            this.age = age;
            this.name = name;
            this.number = number;
        }
    }
}