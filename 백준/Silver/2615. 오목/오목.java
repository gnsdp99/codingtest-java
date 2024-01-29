import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static final int SIZE = 20;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[][] board = new int[SIZE][SIZE];
		for (int i = 1; i < SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < SIZE; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] deltas = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}};
		int curPlayer, winner = 0;
		int nr, nc, num;
		int winnerR = -1, winnerC = -1;
		
		outer: for (int i = 1; i < SIZE; i++) {
			for (int j = 1; j < SIZE; j++) {
				if (board[i][j] == 0) {
					continue;
				}
				curPlayer = board[i][j];
				for (int k = 0; k < deltas.length; k++) {
					num = 1;
					int tmpR = i - deltas[k][0], tmpC = j - deltas[k][1];
					if (isIn(tmpR, tmpC) && board[tmpR][tmpC] == curPlayer) {
						continue;
					}
					nr = i + deltas[k][0];
					nc = j + deltas[k][1];
					while (isIn(nr, nc) && board[nr][nc] == curPlayer) {
						num++;
						nr += deltas[k][0];
						nc += deltas[k][1];
					}
					
					if (num == 5) {
						winner = curPlayer;       
						winnerR = i; winnerC = j;
						break outer;
					}
				}
			}
		}
		
		sb.append(winner).append("\n");
		if (winner != 0) {
			sb.append(winnerR).append(" ").append(winnerC).append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean isIn(int r, int c) {
		return r >= 1 && r < SIZE && c >=1 && c < SIZE; 
	}
}
