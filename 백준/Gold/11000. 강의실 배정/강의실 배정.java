import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 요약
 * - N개의 수업은 각각 S_i에 시작해 T_i에 끝난다.
 * - 최소의 강의실을 사용해 모든 수업을 할 수 있도록 해야 한다.
 * - T_i와 S_j가 같으면 i수업 다음 바로 j수업을 시작한다.
 *
 * 구현
 * - 우선 수업 객체들을 시작 순서로 정렬한다.
 * - 우선순위 큐에 수업 객체를 담는데 정렬 기준은 종료 시간으로 한다.
 * - 그 이유는 시작 시간이 더 늦은 수업 중 종료 시간이 더 이른 경우는 뒷 수업과 먼저 비교해야 하기 때문이다.
 *
 * 입력
 * - 수업의 수 N [1, 200,000], O(N * logN)에 해결해야 한다.
 * - S, T [0, 10e9]
 *
 * 출력
 * - 최소 강의실 개수를 출력한다.
 *
 * 시간복잡도 O(N * logN) -> 정렬 O(N * logN), 우선순위 큐 O(N * logN) (N개의 수업에 대해 logN 연산)
 *
 * 결과
 *
 * */
public class Main {
    static class Class implements Comparable<Class> {
        int start, end;

        Class(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Class o) { // 시작 시간으로 오름차순 정렬
            return start == o.start ? Integer.compare(end, o.end) : Integer.compare(start, o.start);
        }
    }
    static int N;
    static Class[] classes;
    static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력
        N = Integer.parseInt(br.readLine());
        classes = new Class[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            classes[i] = new Class(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(classes); // 시작 순서로 정렬
        int ans = 1; // 첫 수업의 강의실 배정
        priorityQueue.offer(classes[0].end); // 종료 순서로 우선순위
        for (int i = 1; i < N; i++) {
            if (priorityQueue.peek() <= classes[i].start) { // 같은 강의실 배정
                priorityQueue.poll(); // 같은 강의실에 새로운 수업이 배정되었으므로 이전 수업은 삭제
            } else { // 새로운 강의실 배정
                ans++;
            }
            priorityQueue.offer(classes[i].end);
        }
        System.out.println(ans);
    }
}