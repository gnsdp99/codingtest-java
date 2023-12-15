import java.util.Scanner;

// 백준 1463. 1로 만들기 (S3)
public class P1463 {
    public static void main(String[] args) {
        int N;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int[] dp = new int[N + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1];
            if (i % 3 == 0 && dp[i / 3] < dp[i]) {
                dp[i] = dp[i / 3];
            }
            if (i % 2 == 0 && dp[i / 2] < dp[i]) {
                dp[i] = dp[i / 2];
            }
            dp[i] += 1;
        }

        System.out.println(dp[N]);
    }
}