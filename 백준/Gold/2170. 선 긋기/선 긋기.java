import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

        Line[] lines = new Line[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines[i] = new Line(s, e);
        }
        Arrays.sort(lines);

        int ans = 0;

        int start = lines[0].start;
        int end = lines[0].end;

        for (int i = 1; i < N; i++) {
            if (end < lines[i].start) {
                ans += (end - start);
                start = lines[i].start;
            }
            if (end < lines[i].end) {
                end = lines[i].end;
            }
        }
        ans += (end - start);

        System.out.println(ans);
    }
}