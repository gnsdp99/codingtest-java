import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[1001][N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i][0] = dp[i][i] = 1;
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % 10007;
			}
		}
		System.out.println(dp[N][K]);
	}
}