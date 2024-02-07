import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/07 
 * @link https://www.acmicpc.net/problem/16935
 * @keyword_solution  
 * 문제 요약
 * - 6가지 연산이 있고, 배열에 R번의 연산을 적용한 결과를 구한다.
 * 1. 배열을 상하 반전시킨다.
 * 2. 배열을 좌우 반전시킨다.
 * 3. 배열을 오른쪽으로 90도 회전시킨다.
 * 4. 배열을 왼쪽으로 90도 회전시킨다.
 * 배열을 크기가 N/2 x M/2인 4개의 부분 배열로 나눈다.
 * 5. 각 그룹을 시계방향으로 이동시킨다.
 * 6. 각 그룹을 반시계방향으로 이동시킨다.
 * 
 * 구현
 * 배열의 크기를 N과 M중 더 큰 값으로 한다.
 * 회전을 할 때만 시작 행과 시작 열을 바꾸어준다. 
 * 1. N이 짝수이기 때문에 절반만큼 대칭이다. 따라서 행 전체를 스왑하면 된다.
 * 2. M이 짝수이기 때문에 절반만큼 열 전체를 스왑한다.
 * 3. 1행을 N열로, N행을 1열로 이동한다.
 * 4. 1행을 1열로, N행을 N열로 이동한다.
 * 5. 시계 방향은 4번 그룹부터 시행한다. 연산 전에 1번 그룹의 상태를 저장한다.
 * 6. 반시계 방향은 1번 그룹부터 시행한다. 연산 전에 4번 그룹의 상태를 저장한다.
 * 
 * @input 
 * - 배열의 크기 N, M [2, 100]
 * - N, M은 짝수이다.
 * - 연산 수 R [1, 1,000]
 * 
 * @output   
 * - R번 연산 수행한 결과를 출력한다.
 * 
 * @time_complex O(NMR)  
 * @perf 
 */

public class Main {
	
	static int N, M, R, row, col;
	static int[][] board;
	static int halfX, halfY;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		int size = N >= M ? N : M;
		board = new int[size][size];
		row = col = 0; // 시작 위치
		halfX = N / 2; halfY = M / 2;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			operate(Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void operate(int num) {
		if (num == 1) flipHorizontal();
		else if (num == 2) flipVertical();
		else if (num == 3) rotateClockWise();
		else if (num == 4) rotateCounterClockWise();
		else if (num == 5) rotateGroupClockWise();
		else if (num == 6) rotateGroupCounterClockWise();
	}
	
	static void flipHorizontal() {
		for (int x = row; x < row + N / 2; x++) {
			int[] tmp = board[N - 1 - x];
			board[N - 1 - x] = board[x];
			board[x] = tmp;
		}
	}
	
	static void flipVertical() {
		for (int y = col; y < col + M / 2; y++) {
			for (int x = row; x < row + N; x++) {
				int tmp = board[x][M - 1 - y];
				board[x][M - 1 - y] = board[x][y];
				board[x][y] = tmp;
			}
		}
	}
	
	static void rotateClockWise() {
		int[][] originRows = getAllRows();

		for (int y = N - 1; y >= 0; y--) {
			for (int x = 0; x < M; x++) {
				board[x][y] = originRows[N - 1 - y][x];
			}
		}
		swapNM();
	}
	
	static void rotateCounterClockWise() {
		int[][] originRows = getAllRows();

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				board[x][y] = originRows[y][M - 1 - x];
			}
		}
		swapNM();
	}
	
	static void rotateGroupClockWise() {
		int[][] group1 = getGroup(row, col);
		
		// 4 -> 1
		rotateGroup(getGroup(row + halfX, col), row, col);
		// 3 -> 4
		rotateGroup(getGroup(row + halfX, col + halfY), row + halfX, col);
		// 2 -> 3
		rotateGroup(getGroup(row, col + halfY), row + halfX, col + halfY);
		// 1 -> 2
		rotateGroup(group1, row, col + halfY);
	}
	
	static void rotateGroupCounterClockWise() {
		int[][] group4 = getGroup(row + halfX, col);
		
		// 1 -> 4
		rotateGroup(getGroup(row, col), row + halfX, col);
		// 2 -> 1
		rotateGroup(getGroup(row, col + halfY), row, col);
		// 3 -> 2
		rotateGroup(getGroup(row + halfX, col + halfY), row, col + halfY);
		// 4 -> 3
		rotateGroup(group4, row + halfX, col + halfY);
	}
	
	static int[][] getGroup(int sx, int sy) {
		int[][] group = new int[halfX][halfY];
		
		for (int x = 0; x < halfX; x++) {
			for (int y = 0; y < halfY; y++) {
				group[x][y] = board[sx + x][sy + y];
			}
		}
		return group;
	}
	
	static void rotateGroup(int[][] group, int sx, int sy) {
		for (int x = 0; x < halfX; x++) {
			for (int y = 0; y < halfY; y++) {
				board[sx + x][sy + y] = group[x][y];
			}
		}
	}
	
	static int[] getColumn(int y) {
		int[] column = new int[M];
		for (int x = 0; x < N; x++) {
			column[x] = board[row + x][y]; 
		}
		return column;
	}
	
	static int[][] getAllRows() {
		int[][] rows = new int[N][M];
		for (int i = 0; i < N; i++) {
			rows[i] = Arrays.copyOf(board[i], board[i].length);
		}
		return rows;
	}
	
	static void swapNM() {
		int tmp = N;
		N = M;
		M = tmp;
		halfX = N / 2;
		halfY = M / 2;
	}
}