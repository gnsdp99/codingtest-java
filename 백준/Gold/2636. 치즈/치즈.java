/**
 * @author 김예훈 
 * @date 24/02/20
 * @link https://www.acmicpc.net/problem/2636
 * @keyword_solution 
 * 구현
 * flood fill 알고리즘
 * - (0, 0)은 항상 공기이므로 여기서부터 탐색을 시작한다.
 * - 각 공기에서 4방으로 인접한 치즈는 녹았음을 표시한다.
 * - 치즈는 더 이상 탐색 대상에 넣지 않음으로써 가장자리만 녹인다.
 * - 공기는 계속해서 탐색 대상에 추가한다.
 * - 또한 탐색 완료한 공기 탐색 완료를 표시하고 다음 번에 탐색하지 않도록 한다.
 * - 탐색을 할 때마다 녹은 치즈의 개수를 리턴하면 마지막 탐색에서 마지막 치즈의 개수를 구할 수 있다. 
 *  
 * @input
 * - 판의 크기 N, M [1, 100]
 * - 치즈는 1, 공기는 0
 *  
 * @output   
 * - 치즈가 모두 녹아 없어지는 데 걸리는 시간 출력
 * - 모두 녹기 한 시간 전 남아있는 치즈조각의 개수 출력
 * 
 * @time_complex  O(N^2)
 * @perf 
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static class Pos {
		int x, y;
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, M, ansTime, ansNum;
	static int[][] board;
	static Pos[] delta = {new Pos(-1, 0), new Pos(0, 1), new Pos(1, 0), new Pos(0, -1)};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		int totalCheese = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) totalCheese++;
			}
		}

		while (totalCheese > 0) {
			int numCheese = bfsFloodFill(0, 0);
			ansNum = numCheese;
			totalCheese -= numCheese;
			ansTime++;	
		}
		sb.append(ansTime).append("\n").append(ansNum);
		System.out.println(sb);
	}
	
	static int bfsFloodFill(int sx, int sy) {
		Queue<Pos> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new Pos(sx, sy));
		visited[sx][sy] = true;
		int numCheese = 0;
		
		while(!queue.isEmpty()) {
			Pos cur = queue.poll();
			for (int d = 0; d < delta.length; d++) {
				int nx = cur.x + delta[d].x;
				int ny = cur.y + delta[d].y;
				if (isIn(nx, ny) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					if (board[nx][ny] == 0) {
						queue.offer(new Pos(nx, ny));
					}
					else if (board[nx][ny] == 1) {
						numCheese++;
						board[nx][ny] = 0;
					}
				}
			}
		}
		return numCheese;
	}
	
	static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}