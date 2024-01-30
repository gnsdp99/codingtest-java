import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date  24/01/30
 * @link https://www.acmicpc.net/problem/1244
 * @keyword_solution
 * - 남학생은 스위치 번호가 자신의 배수이면 스위치의 상태를 바꾼다.  
 * - 여학생은 자신의 번호가 붙은 스위치를 중심으로 스위치 상태가 좌우 반전이 되는 모든 영역의 스위치를 바꾼다.
 * - 즉 여학생은 항상 홀수 개의 스위치를 바꿀 수 있고 자기 자신은 포함한다.
 * 
 * - 0과 1밖에 없으므로 boolean 사용.
 * - 문자열 입력을 boolean으로 변환하려면 equals() 메서드로 비교해야 함.
 * 
 * - 남학생 케이스
 * - 반복문 돌 때 자신의 번호부터 시작해서 번호만큼 증가하도록 한다.
 * 
 * - 여학생 케이스
 * - left, right 포인터를 두고 자기 자신으로부터 넓혀간다.
 * - left, right를 자기 자신부터 시작할 때 left-1과 right+1이 같은 지 확인해야 한다.
 * 
 * - 출력할 때는 boolean을 int로 변환해야 함.
 *  
 * @input 
 * - 스위치 개수는 100 이하.
 * - 1이 켜져 있는 상태, 0이 꺼져 있는 상태
 * - 학생 수는 100 이하. 따라서 완전 탐색 가능.
 * 
 * @output   
 * - 스위치의 상태를 한 줄에 20개씩 출력해야 한다.
 * - 스위치 사이에는 빈 칸이 존재한다.
 * 
 * @time_complex  O(N^2)
 * @perf 
 */

public class Main {
	
	static int numSwitch;
	static boolean[] switches;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		numSwitch = Integer.parseInt(br.readLine());
		switches = new boolean[numSwitch + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= numSwitch; i++) {
			switches[i] = st.nextToken().equals("1") ? true : false;
		}
		int numStudent = Integer.parseInt(br.readLine());
	
		// logic
		for (int i = 0; i < numStudent; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if (gender == 1) {
				boyStudent(num);
			} else if (gender == 2) {
				girlStudent(num);
			}
		}
		
		int cnt = 0;
		for (int i = 1; i <= numSwitch; i++) {
			sb.append(switches[i] == true ? 1 : 0).append(" ");
			cnt++;
			if (cnt >= 20) {
				sb.append("\n");
				cnt = 0;
			}
		}
		System.out.println(sb);
	}
	
	static void boyStudent(int num) {
		for (int i = num; i <= numSwitch; i+=num) {
			switches[i] = !switches[i];
		}
	}
	
	static void girlStudent(int num) {
		int left = num, right = num;
		
		while (left - 1 >= 1 && right + 1 <= numSwitch && switches[left - 1] == switches[right + 1]) {
			left--;
			right++;
		}
		
		for (int i = left; i <= right; i++) {
			switches[i] = !switches[i];
		}
	}
}