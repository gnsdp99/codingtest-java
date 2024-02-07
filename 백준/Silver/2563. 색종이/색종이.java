import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/07
 * @link https://www.acmicpc.net/problem/2563
 * @keyword_solution  
 * 문제 요약
 * - 색종이가 붙은 영역의 넓이를 구한다.
 * 
 * 구현
 * - 색종이를 좌표로 만들어 cnt가 2이상인 경우 겹친 것으로 판단한다.
 * 
 * @input 
 * - 색종이의 수 N [1, 100] 100*100 = 10,000
 * - 왼쪽 벽과 도화지 왼쪽 변 사이의 거리, 아래쪽 벽과 도화지 아래쪽 변 사이의 거리가 주어진다.
 * 
 * @output   
 * - 색종이가 붙은 넓이를 출력한다.
 * 
 * @time_complex O(N^2)  
 * @perf 
 */

public class Main {

	static int N;
	static int board[][] = new int[100][100];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int area = 100 * N;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int bot = Integer.parseInt(st.nextToken());

			 for (int x = bot; x < bot + 10; x++) {
				 for (int y = left; y < left + 10; y++) {
					 if (board[x][y] == 0) board[x][y] = 1;
					 else area--;
				 }
			}
		}
		System.out.println(area);
	}
}