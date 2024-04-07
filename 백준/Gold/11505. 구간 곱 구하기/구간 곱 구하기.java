import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static final int LEN = 1 << 20;
    static final int SIZE = 1 << 21;
    static final int MOD = 1_000_000_007;
    static long[] tree = new long[SIZE];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(tree, 1);

        for (int i = 0; i < N; i++) {
            tree[LEN + i] = Integer.parseInt(br.readLine());
        }
        for (int i = LEN - 1; i >= 1; i--) {
            tree[i] = ((tree[i << 1] % MOD) * (tree[(i << 1) + 1] % MOD)) % MOD;
        }

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) { // 업데이트
                tree[LEN + b] = c;
                for (int j = (LEN + b) >> 1; j >= 1; j >>= 1) {
                    tree[j] = ((tree[j << 1] % MOD) * (tree[(j << 1) + 1] % MOD)) % MOD;
                }
            } else if (a == 2) { // 구간 곱
                sb.append(prefixMul(1, 0, LEN - 1, b, c - 1)).append("\n");
            }
        }
        System.out.println(sb);
    }

    static long prefixMul(int idx, int curStart, int curEnd, int start, int end) {

        if (start <= curStart && curEnd <= end) {
            return tree[idx];
        } else if (curEnd < start || end < curStart) {
            return 1;
        } else {
            int mid = curStart + ((curEnd - curStart) >> 1);
            long left = prefixMul(idx << 1, curStart, mid, start, end);
            long right = prefixMul((idx << 1) + 1, mid + 1, curEnd, start, end);
            return ((left % MOD) * (right % MOD)) % MOD;
        }
    }
}