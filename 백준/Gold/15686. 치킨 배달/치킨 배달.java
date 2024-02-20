import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/02/20
 * @link https://www.acmicpc.net/problem/15686
 * @keyword_solution  
 * 구현
 * - 전체 치킨집 중 M개의 조합을 구한다.
 * - 각 조합에서 도시의 치킨 거리를 구하고 그중 최소 치킨 거리를 구한다.
 * - 집을 객체로 만들어 치킨집, 일반집을 각자 배열로 관리한다.
 * - 조합은 치킨집 배열에서 M개를 선택해 만들고 조합을 완성할 때마다 모든 집에서 가장 가까운 치킨집과의 거리를 구한다.
 * >> 13CM * 2N * M
 * 
 * 주의
 * - 좌표는 (1, 1)부터 시작하다.
 * - 두 점 사이의 거리는 맨해튼 거리로 구한다.
 * - 집의 개수는 [1, 2N]이다.
 * - 치킨집의 개수는 [M, 13]
 * 
 * @input 
 * - 도시의 크기 N [2, 50]
 * - 최대 선택할 수 있는 치킨 집 개수 M [1, 13]
 * 
 * @output
 * - M개의 치킨집을 골랐을 때 도시의 치킨 거리의 최솟값을 출력한다.
 * 
 * @time_complex  O(13CM)
 * @perf 12,896kb, 144ms
 */

public class Main {

	static class House {
		int x, y;
		House(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, M, MAXDIST, ans;
	static ArrayList<House> chicken = new ArrayList<>(); // 치킨집
	static ArrayList<House> home = new ArrayList<>(); // 일반집
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAXDIST = 2 * N * N;
		ans = MAXDIST;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1) home.add(new House(i, j));
				else if (tmp == 2) chicken.add(new House(i, j));
			}
		}
		getCombination(0, 0, new House[M]);
		System.out.println(ans);
	}
	
	static void getCombination(int kth, int start, House[] selected) {
		if (kth == M) {
			// 치킨 거리 계산
			int dist = getChickenDist(selected);
			ans = ans > dist ? dist : ans;
			return;
		}
		
		for (int i = start; i < chicken.size(); i++) {
			selected[kth] = chicken.get(i);
			getCombination(kth + 1, i + 1, selected);
		}
	}
	
	static int getChickenDist(House[] selected) {
		int totalDist = 0;
		for (int h = 0; h < home.size(); h++) {
			House curHome = home.get(h);
			int minDist = MAXDIST;
			for (int c = 0; c < selected.length; c++) {
				int dist = calcDist(curHome, selected[c]); // 각 집에서 M개의 치킨집중 가장 가까운 곳과의 거리
				minDist = minDist > dist ? dist : minDist;
			}
			totalDist += minDist;
		}
		return totalDist;
	}
	
	static int calcDist(House h, House c) {
		return Math.abs(h.x - c.x) + Math.abs(h.y - c.y);
	}
}