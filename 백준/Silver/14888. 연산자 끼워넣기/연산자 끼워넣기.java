import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, ansMax, ansMin;
    static int[] nums;
    static int[] ops;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        ansMin = Integer.MAX_VALUE;
        ansMax = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        ops = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            ops[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(1, nums[0]);
        sb.append(ansMax).append("\n").append(ansMin);
        System.out.println(sb);
    }

    static void backtracking(int kth, int result) {

        if (kth == N) {
            if (ansMax < result) {
                ansMax = result;
            }
            if (ansMin > result) {
                ansMin = result;
            }
            return;
        }

        int curNum = nums[kth];
        for (int i = 0; i < 4; i++) {
            if (ops[i] > 0) {
                --ops[i];
                backtracking(kth + 1, operate(result, curNum, i));
                ++ops[i];
            }
        }
    }

    static int operate(int a, int b, int opNum) {
        if (opNum == 0) {
            return a + b;
        } else if (opNum == 1) {
            return a - b;
        } else if (opNum == 2) {
            return a * b;
        } else {
            int tmp = Math.abs(a) / Math.abs(b);
            if (a < 0 || b < 0) {
                return tmp * -1;
            } else {
                return tmp;
            }
        }
    }
}