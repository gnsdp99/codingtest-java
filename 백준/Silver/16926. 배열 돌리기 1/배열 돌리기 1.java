import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈 
 * @date 24/02/06
 * @link https://www.acmicpc.net/problem/16926
 * @keyword_solution  
 * 문제 요약
 * - 2차원 배열을 주어진 규칙에 따라 반시계 방향으로 돌린다.
 * - 바깥쪽 사각형부터 회전시킨다.
 * - 각 사각형은 시작점 (x, y)를 기준으로 nx < x || nx > (N+1-x) || ny < y || ny > (M+1-y)이면 방향을 회전한다. 
 * 
 * 구현
 * - 서브 사각형은 min(N, M) / 2개가 존재한다. 
 * 
 * 
 * @input 
 * - 배열의 크기 N x M (2 <= N, M <= 300)
 * - 회전 수 R (1 <= R <= 1,000)
 * - N과 M 둘 중 작은 값은 짝수여야 한다.
 * @output   
 * - 배열을 R번 회전시킨 결과를 출력한다.
 * @time_complex  O(NM)
 * @perf 
 */

public class Main {
	
	static int N, M, R, numRotate;
	static int[][] board;
	
	static int[] deltaX = {-1, 0, 1, 0};
	static int[] deltaY = {0, -1, 0, 1};
	static int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		board = new int[N + 1][M + 1];
		numRotate = Math.min(N, M) / 2;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j ++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < R; i++) {
			rotateRth();
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j ++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void rotateRth() {
		for (int start = 1; start <= numRotate; start++) {
			int dir = DOWN; // 시작점은 무조건 DOWN
			int x = start, y = start;
			
			while (true) {
				int nx = x + deltaX[dir], ny = y + deltaY[dir];
				if (nx < start || nx > (N + 1 - start) || ny < start || ny > (M + 1 - start)) {
					dir = (dir + 1) % 4;
					continue;
				}
				x = nx; y = ny;
				if (x == start && y == start) break;
				
				// swap
				int tmp = board[x][y];
				board[x][y] = board[start][start];
				board[start][start] = tmp; 
			}
		} 
	}
}