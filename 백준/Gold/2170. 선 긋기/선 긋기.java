import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static class Line implements Comparable<Line> {
        int start, end;

        Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line line) {
            return Integer.compare(start, line.start);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<Line> lines = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines.add(new Line(s, e));
        }
        Collections.sort(lines);

        int ans = 0;

        int start = lines.get(0).start;
        int end = lines.get(0).end;

        for (int i = 1; i < N; i++) {
            if (end < lines.get(i).start) {
                ans += (end - start);
                start = lines.get(i).start;
            }
            if (end < lines.get(i).end) {
                end = lines.get(i).end;
            }
        }
        ans += (end - start);

        System.out.println(ans);
    }
}