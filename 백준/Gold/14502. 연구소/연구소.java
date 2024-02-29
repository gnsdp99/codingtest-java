import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/29
 * @link https://www.acmicpc.net/problem/14502
 * @keyword_solution  
 * - 조합을 구할 경우 64C3 * N^2
 *  
 * @input
 * - 세로 크기 N [3, 8]
 * - 가로 크기 M [3, 8]
 *  
 * @output   
 * - 안전 영역의 최대 크기 출력
 * 
 * @time_complex O(64C3 * NM)  
 * @perf 
 */

public class Main {
	
	static class Pos {
		int x, y;
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
		
	}

	static int N, M, ans;
	static int NUM;
	static int[][] board;
	static Pos[] delta = {new Pos(-1, 0), new Pos(0, 1), new Pos(1, 0), new Pos(0, -1)};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		NUM = N * M;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		getCombination(0, new Pos[3], 0);
		System.out.println(ans);
	}
	
	static void getCombination(int kth, Pos[] selected, int start) {
		// kth: 설치할 벽 자릿수, selected: 설치한 벽의 좌표, start: 이전에 설치한 벽의 1차원 좌표 다음 좌표
		if (kth == 3) {
			// 안전 영역 계산
			int numSave = getNumSave(selected);
			ans = ans < numSave ? numSave : ans;
			return;
		}
		
		for (int i = start; i < NUM; i++) {
			int x = i / M;
			int y = i % M;
			if (board[x][y] != 0) continue;
			selected[kth] = new Pos(x, y);
			getCombination(kth + 1, selected, i + 1);
		}
	}
	
	static int getNumSave(Pos[] selected) {
		// 벽 설치
		for (Pos pos: selected) {
			board[pos.x][pos.y] = 1;
		}
		
		int numVirus = 0;
		int numWall = 0;
		Queue<Pos> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 2) {
					queue.offer(new Pos(i, j));
					numVirus++;
				} else if (board[i][j] == 1) numWall++;
			}
		}
		boolean[][] visited = new boolean[N][M];
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			for (int d = 0; d < delta.length; d++) {
				int nx = cur.x + delta[d].x;
				int ny = cur.y + delta[d].y;
				if (!isIn(nx, ny) || board[nx][ny] != 0 || visited[nx][ny]) continue;
				queue.offer(new Pos(nx, ny));
				visited[nx][ny] = true;
				numVirus++;
			}
		}
		// 벽 해제
		for (Pos pos: selected) {
			board[pos.x][pos.y] = 0;
		}
		
		return NUM - (numVirus + numWall);
	}
	
	static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}