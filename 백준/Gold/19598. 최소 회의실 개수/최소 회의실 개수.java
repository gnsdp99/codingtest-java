import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Meeting implements Comparable<Meeting> {
        int start, end;

        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Meeting meeting) {
            return start == meeting.start ? Integer.compare(end, meeting.end) : Integer.compare(start, meeting.start);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Meeting> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Meeting(start, end));
        }

        Collections.sort(list);

        PriorityQueue<Meeting> queue = new PriorityQueue<>((m1, m2) -> {
            return Integer.compare(m1.end, m2.end);
        });
        queue.offer(list.get(0));
        int ans = 1;
        int cur = 1;

        for (int i = 1; i < N; i++) {
            Meeting meeting = list.get(i);

            while (!queue.isEmpty() && meeting.start >= queue.peek().end) {
                queue.poll();
                --cur;
            }
            queue.offer(meeting);
            if (ans < ++cur) {
                ans = cur;
            }
        }
        System.out.println(ans);
    }
}