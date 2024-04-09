import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Hole implements Comparable<Hole> {
        int start, end;

        Hole(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Hole hole) {
            return Integer.compare(start, hole.start);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        ArrayList<Hole> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Hole(start, end - 1));
        }

        Collections.sort(list);

        int cur = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            Hole hole = list.get(i);
            if (cur < hole.start) {
                cur = hole.start - 1;
            }
            while (cur < hole.end) {
                cur += L;
                ++ans;
            }
        }
        System.out.println(ans);
    }
}