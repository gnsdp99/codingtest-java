import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

    static class Schedule implements Comparable<Schedule> {
        int start, end;

        Schedule(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Schedule s) {
            return end == s.end ? Integer.compare(start, s.start) : Integer.compare(end, s.end);
        }
    }

    static int N;
    static Schedule[] schedule;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        schedule = new Schedule[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            schedule[i] = new Schedule(start, end);
        }

        Arrays.sort(schedule);

        int ans = 1;
        int end = schedule[0].end;

        for (int i = 1; i < N; i++) {
            if (schedule[i].start >= end) {
                ++ans;
                end = schedule[i].end;
            }
        }

        System.out.println(ans);
    }
}