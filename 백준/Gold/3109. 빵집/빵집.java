import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈 
 * @date 24/02/14
 * @link https://www.acmicpc.net/problem/3109
 * @keyword_solution  
 * 문제 요약
 * - 첫째 열은 근처 빵집의 가스관, 마지막 열은 원웅의 빵집이다.
 * - 가스관과 빵집을 연결하는 파이프를 설치한다. 중간에 건물이 있는 곳에는 설치할 수 없다.
 * - 모든 파이프라인은 첫째 열에서 시작해서 마지막 열에 끝나야 한다.
 * - 각 칸은 오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결할 수 있다.
 * - 최대한 많은 파이프라인을 설치해야 하는데, 각 파이프끼리는 접하면 안된다. 즉 한 칸에 하나의 파이프만 설치할 수 있다.
 * 
 * 구현
 * 완전탐색
 * - O(R * 3^C) (X)
 * 
 * 그리디 + 백트래킹
 * - 맨 위 행부터 연결을 시도한다.
 * - 방향도 위부터 연결을 시도한다.
 *  
 * @input 
 * - R [1, 10,000]
 * - C [5, 500]
 * - '.'은 빈칸, 'x'는 건물이다. 첫 열과 마지막 열은 항상 비어있다.
 * 
 * @output   
 * - 설치할 수 있는 파이프라인의 최대 개수를 출력한다.
 * 
 * @time_complex  O(RC)
 * @perf 
 */

public class Main {

	static int R, C, ans;
	static char[][] map;
	
	static int[] delta = {-1, 0, 1}; // 열은 무조건 1 이동이므로 행만 필요
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		for (int r = 0; r < R; r++) {
			if (backtracking(r, 0)) ans++;
		}
		
		System.out.println(ans);
	}
	
	static boolean backtracking(int row, int col) {
		map[row][col] = 'x';
		
		if (col == C - 1) return true;
		
		for (int i = 0; i < 3; i++) {
			int nx = row + delta[i];
			int ny = col + 1;
			if (isIn(nx, ny) && map[nx][ny] == '.') {
				if (backtracking(nx, ny)) return true;
			}
		}
		return false;
	}
	
	static boolean isIn(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
}