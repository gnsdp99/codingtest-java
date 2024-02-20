/**
 * @author 김예훈 
 * @date 24/02/20
 * @link https://www.acmicpc.net/problem/2636
 * @keyword_solution 
 * 구현
 * flood fill 알고리즘
 * - (0, 0)은 항상 공기이므로 여기서부터 탐색을 시작한다.
 * - 각 공기에서 4방으로 인접한 치즈는 공기로 만든다.
 * - 치즈는 더 이상 탐색 대상에 넣지 않음으로써 가장자리만 녹인다.
 * - 공기는 계속해서 탐색 대상에 추가한다.
 * - 치즈를 큐에 넣지 않으면서 다음 탐색의 대상이 되도록 하기 위해 공기와 치즈의 방문 여부를 따로 관리한다.
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
 * @perf 13,004kb, 116ms
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
	static boolean[][] visited;
	static Queue<Pos> cheeseQueue = new ArrayDeque<>();
	static Pos[] delta = {new Pos(-1, 0), new Pos(0, 1), new Pos(1, 0), new Pos(0, -1)};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfsFloodFill(0, 0); // 0인 곳은 flood fill
	
		while (!cheeseQueue.isEmpty()) {
			ansTime++;
			int size = cheeseQueue.size();
			ansNum = size;
			for (; size > 0; size--) { // 녹은 치즈들의 4방 탐색
				Pos pos = cheeseQueue.poll();
				board[pos.x][pos.y] = 2; // 공기의 방문 처리
				
				for (int d = 0; d < delta.length; d++) {
					int nx = pos.x + delta[d].x;
					int ny = pos.y + delta[d].y;
					if (isIn(nx, ny)) {
						if (board[nx][ny] == 0) bfsFloodFill(nx, ny); // 0인 곳은 flood fill
						else if (!visited[nx][ny] && board[nx][ny] == 1) {
							visited[nx][ny] = true;
							cheeseQueue.offer(new Pos(nx, ny)); // 안쪽 치즈 추가
						}
					}
				}
			}
		}
		sb.append(ansTime).append("\n").append(ansNum);
		System.out.println(sb);
	}
	
	static void bfsFloodFill(int sx, int sy) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(sx, sy));
		board[sx][sy] = 2;
		
		while(!queue.isEmpty()) {
			Pos cur = queue.poll();
			for (int d = 0; d < delta.length; d++) {
				int nx = cur.x + delta[d].x;
				int ny = cur.y + delta[d].y;
				if (isIn(nx, ny)) {
					if (board[nx][ny] == 0) {
						queue.offer(new Pos(nx, ny));
						board[nx][ny] = 2;
					}
					else if (!visited[nx][ny] && board[nx][ny] == 1) {
						visited[nx][ny] = true;
						cheeseQueue.offer(new Pos(nx, ny));
					}
				}
			}
		}
	}
	
	static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}