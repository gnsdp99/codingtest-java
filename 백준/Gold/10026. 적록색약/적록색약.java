/**
 * @author 김예훈 
 * @date 24/02/20
 * @link https://www.acmicpc.net/problem/10026
 * @keyword_solution
 * 구현
 * - 플러드 필 - 4방탐색으로 같은 색상이 있는 경우 계속 탐색한다.
 * - 적록색약과 아닌 사람의 그리드를 구분해야 하기 때문에 두 개를 만든다.
 * - 적록색약인 사람은 빨강과 초록이 같기 때문에 빨강으로만 표현한다.
 * - 탐색 완료한 격자는 X로 바꾼다.
 * 
 * @input
 * - 그리드의 크기 N [1, 100]
 *  
 * @output   
 * - 적록색약이 아닌 사람과 적록색약인 사람이 봤을 때의 각각의 구역의 수를 출력한다.
 * 
 * @time_complex O(N^2) 그리드 전체를 탐색
 * @perf 
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main {
	
	static class Pos {
		int x, y;
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, ansColor, ansNonColor;
	static char[][] boardColor, boardNonColor;
	static Pos[] delta = {new Pos(-1, 0), new Pos(0, 1), new Pos(1, 0), new Pos(0, -1)};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		boardColor = new char[N][N];
		boardNonColor = new char[N][N];
		for (int i = 0; i < N; i++) {
			char[] row = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				boardColor[i][j] = row[j];
				if (row[j] == 'G') boardNonColor[i][j] = 'R';
				else boardNonColor[i][j] = row[j];
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (boardColor[i][j] == 'B') { // 적록색약인 사람과 아닌 사람이 똑같음
					bfsFloodFill(boardColor, i, j, 'B');
					bfsFloodFill(boardNonColor, i, j, 'B');
					ansColor++;
					ansNonColor++;
				} else if (boardColor[i][j] == 'R') {
					bfsFloodFill(boardColor, i, j, 'R');
					ansColor++;
				} else if (boardColor[i][j] == 'G') {
					bfsFloodFill(boardColor, i, j, 'G');
					ansColor++;
				}
				if (boardNonColor[i][j] == 'R') {
					bfsFloodFill(boardNonColor, i, j, 'R');
					ansNonColor++;
				}
			}
		}
		System.out.println(ansColor + " " + ansNonColor);
	}
	
	static void bfsFloodFill(char[][] board, int sx, int sy, char color) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(sx, sy));
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			for (int d = 0; d < delta.length; d++) {
				int nx = cur.x + delta[d].x;
				int ny = cur.y + delta[d].y;
				if (isIn(nx, ny) && board[nx][ny] == color) {
					queue.offer(new Pos(nx, ny));
					board[nx][ny] = 'X'; // 방문 처리
				}
			}
		}
	}
	
	static void dfsFloodFill() {
		
	}
	
	static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}