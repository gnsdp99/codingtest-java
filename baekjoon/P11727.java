/*
 * 맨 앞에 2x1 1개를 세울 때, 1x2 2개를 세울 때, 2x2 1개를 세울 때
 * >> 2x1 과 나머지, 2x2와 나머지, 2x2와 나머지
 * 2x1 => 1개
 * 2x2 => 1 + 1 + 1 = 3
 * 2x3 => (2x2) + (2x1) + (2x1) = 3 + 1 + 1 = 5
 * 2x4 => (2x3) + (2x2) + (2x2) = 5 + 3 + 3 = 11
 * 2x5 =>
 * 2x6 =>
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[1001];
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 2]) % 10007;
        }
        System.out.println(dp[N]);
    }
}