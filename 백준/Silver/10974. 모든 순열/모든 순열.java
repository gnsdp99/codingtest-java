import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제 요약
 * - 1 ~ N으로 이루어진 순열을 사전순으로 출력한다.
 * 
 * 구현
 * 1. Next Permutation
 * - 1 ~ N으로 이루어진 배열을 오름차순으로 정렬한다. (여기서는 할 필요 없음)
 * - 첫 순열은 미리 처리한다. (do-while 문)
 * - 뒤에서부터 탐색하면서 감소 구간 i를 찾는다.
 * - i-1번째 수가 스왑의 대상이 된다.
 * - 뒤에서부터 i-1번째 수보다 큰 값을 찾는다. (원래는 i번째 수부터 i-1번째 수보다 큰 값 중 가장 작은 값을 찾아야 하지만 i번째부터 항상 내림차순 정렬되어 있기 때문에 맨 뒤에서 찾으면 됨)
 * - i-1번째 수와 j번째 수를 스왑한다.
 * - 순열의 순서를 위해 i번째부터 오름차순 정렬한다.
 * 
 * 2. 재귀
 * 
 * 입력
 * - N [1, 8] >> 8! = 56 * 720 = 40,320 
 *
 * 출력
 * - 순열을 사전순으로 한 줄에 하나씩 출력한다.
 *
 * 시간복잡도 O(N!), 8! = 56 * 720 = 40,320 
 * 
 * 결과
 */

public class Main {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 초기화
		N = Integer.parseInt(br.readLine());
		int[] perms = new int[N];
		for (int i = 0; i < N; i++) {
			perms[i] = i + 1;
		}
		
		// Next Permutation
		do {
			for (int e: perms) {
				sb.append(e).append(" ");
			}
			sb.append("\n");
		} while(nextPermutation(perms));
		
		System.out.println(sb);
	}
	
	static boolean nextPermutation(int[] perms) {
		int i = N - 1;
		while (i - 1 >= 0 && perms[i - 1] >= perms[i]) i--; // 감소 구간
		
		if (i == 0) return false; // 마지막 순열
		
		int j = N - 1;
		while (j >= i && perms[i - 1] >= perms[j]) j--; // 스왑 대상
		
		swap(perms, i - 1, j);
		
		// i번째부터 오름차순 정렬
		int k = N - 1;
		while (k > i) swap(perms, i++, k--);
		
		return true;
	}
	
	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}