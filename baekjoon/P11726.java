/*
* 타일의 크기가 1 x 2와 2 x 1이기 때문에 2 x (n - 2) 직사각형과 2 x (n - 1) 직사각형에서 만든 경우의 수에 타일 하나씩만 추가하면 됨.
* 따라서 dp[n] = dp[n - 2] + dp[n - 1]
* 단, n의 최댓값이 1000이기 때문에 오버플로우의 위험이 있다. 따라서 마지막에 10007의 나머지를 구하는 것이 아닌, 두 값을 더할 때마다 나머지 연산을 해야 한다.
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N + 1];
        dp[1] = 1;
        if (N >= 2) {
            dp[2] = 2;
            for (int i = 3; i <= N; i++) {
                dp[i] = (dp[i - 2] + dp[i - 1]) % 10007;
            }
        }
        System.out.println(dp[N]);
    }
}