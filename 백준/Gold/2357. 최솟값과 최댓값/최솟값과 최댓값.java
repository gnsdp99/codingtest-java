import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static final int LEN = 1 << 17;
    static final int SIZE = 1 << 18;
    static final int MAX = 1_000_000_000;
    static int[] treeMax = new int[SIZE];
    static int[] treeMin = new int[SIZE];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Arrays.fill(treeMin, MAX);

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            treeMax[LEN + i] = num;
            treeMin[LEN + i] = num;
        }

        for (int i = LEN - 1; i >= 1; i--) {
            treeMax[i] = Math.max(treeMax[i << 1], treeMax[(i << 1) + 1]);
            treeMin[i] = Math.min(treeMin[i << 1], treeMin[(i << 1) + 1]);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(findMin(1, 0, LEN - 1, a - 1, b - 1)).append(" ");
            sb.append(findMax(1, 0, LEN - 1, a - 1, b - 1)).append("\n");
        }
        System.out.println(sb);
    }

    static int findMax(int idx, int curStart, int curEnd, int start, int end) {

        if (start <= curStart && curEnd <= end) {
            return treeMax[idx];
        } else if (start > curEnd || end < curStart) {
            return 0;
        } else {
            int mid = curStart + ((curEnd - curStart) >> 1);
            return Math.max(findMax(idx << 1, curStart, mid, start, end), findMax((idx << 1) + 1, mid + 1, curEnd, start, end));
        }
    }

    static int findMin(int idx, int curStart, int curEnd, int start, int end) {

        if (start <= curStart && curEnd <= end) {
            return treeMin[idx];
        } else if (start > curEnd || end < curStart) {
            return 1_000_000_000;
        } else {
            int mid = curStart + ((curEnd - curStart) >> 1);
            return Math.min(findMin(idx << 1, curStart, mid, start, end), findMin((idx << 1) + 1, mid + 1, curEnd, start, end));
        }
    }
}