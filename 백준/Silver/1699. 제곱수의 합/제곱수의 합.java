import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j < i; j++) {
                int square = j * j;
                if (square > i) {
                    break;
                } else if (square == i) {
                    dp[i] = 1;
                    break;
                } else {
                    dp[i] = Math.min(dp[i], dp[square] + dp[i - square]);
                }
            }
        }
        System.out.println(dp[N]);
    }
}