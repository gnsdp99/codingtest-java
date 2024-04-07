import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static final int LEN = 1 << 17;
    static final int SIZE = 1 << 18;
    static int[] tree = new int[SIZE];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            tree[LEN + i] = Integer.parseInt(br.readLine());
        }

        for (int i = LEN - 1; i >= 1; i--) {
            tree[i] = Math.min(tree[i << 1], tree[(i << 1) + 1]);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            sb.append(findMin(1, 0, LEN - 1, a, b)).append("\n");
        }
        System.out.println(sb);
    }

    static int findMin(int idx, int curStart, int curEnd, int start, int end) {

        if (start <= curStart && curEnd <= end) {
            return tree[idx];
        } else if (curEnd < start || end < curStart) {
            return 1_000_000_000;
        } else {
            int mid = curStart + ((curEnd - curStart) >> 1);
            return Math.min(findMin(idx << 1, curStart, mid, start, end),
                    findMin((idx << 1) + 1, mid + 1, curEnd, start, end));
        }
    }
}