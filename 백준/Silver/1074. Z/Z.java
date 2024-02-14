import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/14
 * @link https://www.acmicpc.net/problem/1074
 * @keyword_solution
 * 문제 요약  
 * - 크기가 2^N x 2^N인 2차원 배열을 z모양으로 탐색한다.
 * - 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 순서대로 Z모양이다.
 * - N > 1인 경우, 배열을 2^(N-1) x 2^(N-1)로 4등분 한 후 재귀적으로 순서대로 방문한다. 
 * - r행 c열을 몇 번째로 방문했는지 구한다.
 * 
 * 구현
 * - 문제를 절반씩 분할해서 해결한다.
 * - 최소 크기 2x2가 될 때까지 분할한다. >> 전체 탐색하면 O(2^2N)임.
 * - 만약 중간에 (R, C)를 만나면 탐색을 중단해야 한다.
 * 
 * - 전체를 탐색하지 않고 (R, C)가 존재할 만한 곳을 계속 찾아가야 함. 
 * - 4개로 분할 했을 때의 각 왼쪽 위 좌표를 구한다.
 * - 왼쪽 위 좌표가 (x, y)일 때 x <= R < x + 현재 한 변의 길이 && y <= C < y + 현재 한 변의 길이이면 그 사각형에 속한 것이다.
 * - 만약 i번째 사각형(Z모양으로)에 속한다면 2^한 변의 길이 * (i - 1)을 미리 카운트하면 된다.
 * - 이때 마지막에 -1을 해줘야 한다. (카운트가 0부터 시작해야 함) 
 * 
 * 주의
 * - 방문 순서는 0부터 시작임.
 * - 맵의 크기가 NxN이 아니라 2^N x 2^N임.
 * 
 * @input 
 * - N [1, 15]
 * - r, c [0, 2^N)
 * 
 * @output   
 * - r행 c열을 몇 번째로 방문했는지 출력한다.
 * 
 * @time_complex  
 * O(2^2N) -> 전체를 한 번씩 탐색할 경우
 * O(N) -> 사각형을 하나씩만 탐색할 경우
 * 
 * @perf 
 */

public class Main {

	static final int X = 0, Y = 1;
	static int N, R, C, ans;
	static int[][] map;
	static int[][] delta = {{0, 0}, {0, 1}, {1, 0}, {1, 1}}; // Z모양
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		searchZ((int) Math.pow(2, N), 0, 0);
		
		System.out.println(ans); 
	}
	
	static void searchZ(int size, int x, int y) { // 배열의 길이 2가 될 때가지 분할 후 탐색
		// size: 배열의 길이, x: 왼쪽 위 행, y: 왼쪽 위 열
		int half = size / 2;
		
		if (size == 2) {
			// Z 탐색
			for (int i = 0; i < 4; i++) {
				if (x + delta[i][X] == R && y + delta[i][Y] == C) break;
				
				ans++; // 방문 순서 (0부터 시작이라 나중에 더함)
			}
			return;
		}
		
		// 4분할 중 하나만 선택
		for (int i = 0; i < 4; i++) {
			int nx = x + half * delta[i][X];
			int ny = y + half * delta[i][Y];
			
			if (isIn(nx, ny, half)) {
				ans += i * half * half;
				searchZ(half, nx, ny);
				break;
			}
		}
	}
	
	static boolean isIn(int x, int y, int size) {
		return R >= x && R < x + size && C >= y && C < y + size;
	}
}