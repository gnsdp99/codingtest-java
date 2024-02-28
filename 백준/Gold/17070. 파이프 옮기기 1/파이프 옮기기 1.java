import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈 
 * @date 24/02/28 
 * @link https://www.acmicpc.net/problem/17070
 * @keyword_solution  
 * 완전탐색
 * tail은 무조건 head의 이전 위치로 이동하기 때문에 head의 위치만 범위 체크하면 됨.
 * 가로[1] = 대각선
 * 세로[1] = 대각선
 * 대각선[0] = 가로
 * 대각선[1] = 세로
 * 
 * @input 
 * - 집의 크기 N [3, 16]
 * - 0: 빈칸, 1: 벽
 * - 파이프의 초기 위치: (1, 1), (1, 2)
 * 
 *  * @output   
 * - 파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수를 출력.
 * - 없으면 0을 출력.
 * - 경우의 수는 항상 1,000,000 이하
 * 
 * @time_complex
 * @perf 
 */

public class Main {
	
	static class Pos {
		int x, y;
		Pos (int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	static class Pipe {
		Pos head, tail;
		int status;
		Pipe (Pos head, Pos tail, int status) {
			this.head = head;
			this.tail = tail;
			this.status = status;
		}
		
		boolean canArrive() {
			return head.x == N && head.y == N;
		}

		@Override
		public String toString() {
			return "Pipe [head=" + head + ", tail=" + tail + ", status=" + status + "]";
		}
		
	}

	static int N, ans;
	static boolean[][] board;
	static Pipe pipe;
	static final int ROW = 0, COL = 1, DGL = 2;
	static Pos[][] delta = {{new Pos(0, 1), new Pos(1, 1)}, {new Pos(1, 0), new Pos(1, 1)}, {new Pos(0, 1), new Pos(1, 0), new Pos(1, 1)}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new boolean[N + 1][N + 1];
		pipe = new Pipe(new Pos(1, 2), new Pos(1, 1), ROW);
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken()) == 0 ? true : false;
			}
		}
		
		moveBacktracking(pipe);
		System.out.println(ans > 0 ? ans : 0);
	}
	
	static void moveBacktracking(Pipe pipe) {
		if (pipe.canArrive()) {
			ans++;
			return;
		}
		
		int status = pipe.status;
		int headX = pipe.head.x;
		int headY = pipe.head.y;
		
		for (int d = 0; d < delta[status].length; d++) {
			int nx = headX + delta[status][d].x;
			int ny = headY + delta[status][d].y;
			
			int newStatus = status;
			if ((status == ROW && d == 1) || (status == COL && d == 1)) newStatus = DGL;
			else if (status == DGL && d == 0) newStatus = ROW;
			else if (status == DGL && d == 1) newStatus = COL;
			
			if (!isPossible(nx, ny, newStatus)) continue;
			
			moveBacktracking(new Pipe(new Pos(nx, ny), new Pos(headX, headY), newStatus));
		}
	}
	
	static boolean isPossible(int x, int y, int status) {
		if (x < 1 || x > N || y < 1 || y > N || !board[x][y]) return false;
		if (status == DGL && (!board[x - 1][y] || !board[x][y - 1])) return false; 
		return true;
	}
}