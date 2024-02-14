import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - 한 개의 회실과 N개의 회의가 있다.
 * - 각 회의 I에 대해 시작 시간과 종료 시간이 있고, 각 회의가 겹치지 않게 배정해야 한다.
 * - 이때 배정할 수 있는 회의의 최대 개수를 구한다.
 * - 회의는 한번 시작하면 중간에 중단될 수 없고 한 회의가 끝남과 동시에 다음 회의가 시작될 수 있다.
 * 
 * 구현
 * - 최대한 많은 회의를 배치하려면 일찍 끝나는 순으로 배치해야 한다.
 * - 따라서 종료 시간으로 정렬한다. 
 * 
 * 입력
 * - 회의의 수 N [1, 100,000]
 * - 시작 시간과 끝나는 시간 [0, 2^31 - 1] >> int형 최댓값까지 
 *
 * 출력
 * - 회의의 최대 개수를 출력한다.
 *
 * 시간복잡도 O(N*logN) 정렬
 * 
 * 결과
 */

public class Main {
	
	static class Meeting implements Comparable<Meeting> {
		int start;
		int end;
		
		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting o) { // 종료 시간 순으로 정렬
			return this.end != o.end ? Integer.compare(this.end, o.end) : Integer.compare(this.start, o.start);
		}
	}

	static int N;
	static Meeting[] meetings;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력
		N = Integer.parseInt(br.readLine());
		meetings = new Meeting[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(meetings); // 그리디를 위한 정렬

		int ans = 1; // 첫 회의는 무조건 배정
		int prev = 0; // 이전 회의
		for (int next = 1; next < N; next++) { // 이전 회의 끝나는 시간보다 늦거나 같으면 배정
			if (meetings[next].start >= meetings[prev].end) {
				ans++;
				prev = next;
			}
		}
		
		// 출력
		System.out.println(ans);
	}
}