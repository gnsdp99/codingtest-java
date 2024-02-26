import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int R, C;
	static char[][] board;
	static Pos[] delta = {new Pos(-1, 0), new Pos(0, 1), new Pos(1, 0), new Pos(0, -1)};
	static Queue<Pos> waterQueue, curQueue;
	static boolean[][] visited;
	static Pos person1, person2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][];
		waterQueue = new ArrayDeque<>();
		curQueue = new ArrayDeque<>();
		
		
		for (int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (board[i][j] == 'L') {
					if (person1 == null) person1 = new Pos(i, j);
					else person2 = new Pos(i, j);
					board[i][j] = '.';
				}
				if (board[i][j] == '.') {
					waterQueue.offer(new Pos(i, j));
				}
			}
		}
		
		curQueue.offer(person1);
		visited = new boolean[R][C];
		visited[person1.x][person1.y] = true;

		int cnt = 0;	
		while (true) {
			if (move()) break;
			melt();
			cnt++;
		}
		System.out.println(cnt);
	}
	
	public static void melt() {
		int size = waterQueue.size();
		for (int i = 0; i < size; i++) {
			Pos pos = waterQueue.poll();
			for (int d = 0; d < delta.length; d++) {
				int nx = pos.x + delta[d].x;
				int ny = pos.y + delta[d].y;
				if (!isIn(nx, ny)) continue;
				
				if (board[nx][ny] == 'X') {
					board[nx][ny] = '.';
					waterQueue.offer(new Pos(nx, ny));
				}
			}
		}
	}
	
	public static boolean move() {
		Queue<Pos> nextQueue = new ArrayDeque<>();
		
		while (!curQueue.isEmpty()) {
			Pos pos = curQueue.poll();
			if (pos.x == person2.x && pos.y == person2.y) return true;
			for (int d = 0; d < delta.length; d++) {
				int nx = pos.x + delta[d].x;
				int ny = pos.y + delta[d].y;
				if (!isIn(nx, ny) || visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				if (board[nx][ny] == '.') curQueue.offer(new Pos(nx, ny));
				else if (board[nx][ny] == 'X') nextQueue.offer(new Pos(nx, ny));
			}
		}
		curQueue = nextQueue;
		return false;
	}
	
	public static boolean isIn(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
}