import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김예훈 
 * @date 24/02/28
 * @link https://www.acmicpc.net/problem/1600
 * @keyword_solution  
 * @input 
 * @output   
 * @time_complex O(HWK)
 * @perf 
 */

public class Main {
	
	static class Pos {
		int x, y;
		Pos (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Node {
		Pos pos;
		int cnt, numHorse;
		Node (Pos pos, int cnt, int numHorse) {
			this.pos = pos;
			this.cnt = cnt;
			this.numHorse = numHorse;
		}
	}
	
	static int K, W, H, ans = Integer.MAX_VALUE;
	static boolean[][] board;
	static boolean[][][] cntVisited;
	static Pos[] delta = {new Pos(-1, 0), new Pos(0, 1), new Pos(1, 0), new Pos(0, -1)};
	static Pos[] deltaHorse = {
			new Pos(-1, -2), new Pos(-2, -1), new Pos(-2, 1), new Pos(-1, 2),
			new Pos(1, 2), new Pos(2, 1), new Pos(2, -1), new Pos(1, -2)
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		board = new boolean[H + 1][W + 1];
		
		for (int i = 1; i <= H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken()) == 0 ? true : false;
			}
		}
		
		BFS();
		System.out.println(ans != Integer.MAX_VALUE ? ans : -1);
	}
	
	static void BFS() {
		cntVisited = new boolean[H + 1][W + 1][K + 1];
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(new Pos(1, 1), 0, K));
		cntVisited[1][1][K] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int x = node.pos.x;
			int y = node.pos.y;
			int cnt = node.cnt;
			int numHorse = node.numHorse;
			if (x == H && y == W) {
				ans = cnt;
				return;
			}
			
			if (numHorse > 0) {
				for (int d = 0; d < deltaHorse.length; d++) {
					int nx = x + deltaHorse[d].x;
					int ny = y + deltaHorse[d].y;
					if (!isPossible(nx, ny) || cntVisited[nx][ny][numHorse - 1]) continue;
					
					cntVisited[nx][ny][numHorse - 1] = true;
					queue.offer(new Node(new Pos(nx, ny), cnt + 1, numHorse - 1));
				}
			}
			for (int d = 0; d < delta.length; d++) {
				int nx = x + delta[d].x;
				int ny = y + delta[d].y;
				if (!isPossible(nx, ny) || cntVisited[nx][ny][numHorse]) continue;
				
				cntVisited[nx][ny][numHorse] = true;
				queue.offer(new Node(new Pos(nx, ny), cnt + 1, numHorse));
			}
		}
	}
	
	static boolean isPossible(int x, int y) {
		return x >= 1 && x <= H && y >= 1 && y <= W && board[x][y];
	}
}