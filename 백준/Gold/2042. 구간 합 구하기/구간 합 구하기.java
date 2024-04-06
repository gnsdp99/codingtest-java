import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static final int LEN = 1 << 20;
    static final int SIZE = 1 << 21;
    static long[] tree = new long[SIZE];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 세그먼트 트리 구성
        for (int i = 0; i < N; i++) {
            tree[LEN + i] = Long.parseLong(br.readLine());
        }

        for (int i = LEN - 1; i >= 1; i--) {
            tree[i] = tree[i << 1] + tree[(i << 1) + 1];
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            if (a == 1) { // 값 변경

                long prev = tree[LEN + b];
                for (int j = LEN + b; j >= 1; j >>= 1) {
                    tree[j] = tree[j] - prev + c;
                }

            } else if (a == 2) { // 구간 합

                sb.append(prefixSum(1, 0, LEN - 1, b, c - 1)).append("\n");

            }
        }
        System.out.println(sb);
    }

    static long prefixSum(int idx, int curStart, int curEnd, int start, long end) {

        if (start <= curStart && curEnd <= end) {

            return tree[idx];

        } else if (start > curEnd || end < curStart) {

            return 0;

        } else {

            int mid = curStart + ((curEnd - curStart) >> 1);

            long left = prefixSum(idx << 1, curStart, mid, start, end);
            long right = prefixSum((idx << 1) + 1, mid + 1, curEnd, start, end);
            return left + right;

        }
    }
}