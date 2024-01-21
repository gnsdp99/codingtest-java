import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class P16637 {

    static char[] ops;
    static int[] nums;

    static int numOps;
    static int ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String exp = br.readLine();

        if (N == 1) {
            System.out.println(exp);
            return;
        }

        numOps = N / 2;
        ops = new char[numOps];
        nums = new int[numOps + 1];
        int numIdx = 0, opIdx = 0;

        for (int i = 0; i < N; i++) {
            // 짝수 번째는 숫자, 홀수 번째는 연산자
            if (i % 2 == 0) {
                nums[numIdx++] = exp.charAt(i) - '0';
            } else {
                ops[opIdx++] = exp.charAt(i);
            }
        }

        ans = Integer.MIN_VALUE;

        dfs(nums[0], 0);
        System.out.println(ans);
    }

    public static void dfs(int val, int opIdx) {
        // 연산자를 기준으로 왼쪽은 괄호 없이 계산하고, 오른쪽은 괄호가 있는 경우와 없는 경우로 나누어 계산한다.
        if (opIdx >= numOps) {
            ans = Math.max(ans, val);
            return;
        }

        // 오른쪽에 괄호가 없는 경우
        int tmp1 = calcTwoNums(val, nums[opIdx + 1], ops[opIdx]);
        dfs(tmp1, opIdx + 1);

        // 오른쪽에 괄호가 있는 경우
        if (opIdx + 1 < numOps) {
            int tmp2 = calcTwoNums(nums[opIdx + 1], nums[opIdx + 2], ops[opIdx + 1]);
            tmp2 = calcTwoNums(val, tmp2, ops[opIdx]);
            dfs(tmp2, opIdx + 2);
        }
    }

    public static int calcTwoNums(int num1, int num2, char op) {
        int res;
        if (op == '+') {
            res = num1 + num2;
        } else if (op == '*') {
            res = num1 * num2;
        } else {
            res = num1 - num2;
        }
        return res;
    }
}