import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date  24/02/25
 * @link https://www.acmicpc.net/problem/1158
 * @keyword_solution  
 * 문제 요약
 * - 1번부터 N번까지의 사람이 있다.
 * - N명을 모두 제거할 때까지 순서대로 K번째 사람을 제거한다.
 * - 제거한 순서를 출력한다.
 * 
 * 구현
 * - 중간에 있는 사람이 제거되어야 하므로 연결 리스트를 사용하는 것이 효율적이다.
 * - 이때 원으로 이어져야 하므로 마지막 노드가 첫 번째 노드를 가리키도록 한다.
 * 
 * @input
 * - 사람의 수 N [1, 5,000]
 * - K번째 사람 제거 [1, min(N, 5,000)] 
 * 
 * @output   
 * - 요세푸스 순열 출력
 * @time_complex O(NK)
 * @perf 
 */

public class Main {

	static class Node {
		int num;
		Node next;
		
		Node() {}
		
		Node(int num) {
			this.num = num;
		}
	}
	
	static int N, K, size;
	
	static Node tail, prev;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		prev = new Node();
		tail = prev;
		for (int i = 1; i <= N; i++) {
			Node node = new Node(i);
			tail.next = node;
			tail = node;
			size++;
		}
		tail.next = prev.next;
		
		sb.append("<");
		while (size > 1) {
			sb.append(removeKth()).append(", ");
		}
		sb.append(removeKth()).append(">");
		
		System.out.println(sb);
	}

	static int removeKth () {
		for (int i = 0; i < K - 1; i++) {
			prev = prev.next;
		}
		Node removed = prev.next;
		prev.next = removed.next;
		size--;
		
		return removed.num;
	}
}