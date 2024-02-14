import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 김예훈
 * @date 24/02/01
 * @link https://www.acmicpc.net/problem/9663
 * @keyword_solution
 * N x N 체스판 위에 퀸 N개를 서로 공격할 수 없게 배치해야 한다.
 * >> 즉 8방으로 공격할 대상이 없어야 한다.
 *
 * - 별다른 공식은 존재하지 않으니 백트래킹으로 하나씩 놓아 보는 수밖에 없다.
 * - 이때 체스판을 열을 기준으로 하나의 1차원 배열로 만들 수 있다.
 * >> 배열의 인덱스는 열, 값은 행을 의미한다.
 * - 그러면 길이 N짜리의 순열을 구하는 것과 같다.
 * >> 중간에 조건에 맞지 않으면 가지치기 할 수 있다.
 * >> 같은 대각선 or 같은 행에 놓는 건 가지치기 대상.
 * >> 놓을 때마다 왼쪽 위 대각선, 왼쪽 아래 대각선, 왼쪽 직선을 탐색해야 한다.
 *
 * @input
 * - N [1, 15]
 * >> 순열을 모두 구하면 15! = 1조
 * @output
 * - 조건을 만족하도록 퀸을 배치하는 경우의 수 출력.
 * @time_complex O(2^(N*N))
 * @perf
 */
public class Main {

    static int N, ans;
    static boolean[] rows, left_cross, right_cross;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        rows = new boolean[N];
        left_cross = new boolean[2 * N];
        right_cross = new boolean[2 * N];

        backtracking(0);
        System.out.println(ans);
    }

    static void backtracking(int row) {
        if (row == N) {
            ans++;
            return;
        }
        
        for (int col = 0; col < N; col++) {
			if (rows[col] || left_cross[row + col] || right_cross[row - col + N]) continue;
			
			rows[col] = true;
			left_cross[row + col] = true;
			right_cross[row - col + N] = true;
			backtracking(row + 1);
			rows[col] = false;
			left_cross[row + col] = false;
			right_cross[row - col + N] = false;
		}
    }
}