import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * @author 김예훈
 * @date 24/01/29
 * @link https://www.acmicpc.net/problem/1914
 * @keyword_solution
 * 재귀.
 * 반복 요소: 다른 탑 중 자신보다 큰 원판이 있거나 빈 곳으로 이동
 * 매개 변수: 이동할 원판의 수, 시작 탑, 도착 탑, 중간 탑
 * 종료 조건: 이동할 원판의 수가 1 (바로 이동)
 * @input
 * 원판의 개수 (1 <= N <= 100)
 * @output
 * 최소 이동 횟수 출력 (하노이 탑의 최소 이동 횟수는 (2^N-  1), N이 커지면 재귀로 절대 구할 수 없음.)
 * N이 20 이하인 경우 두 번째 줄부터 수행 과정 출력.
 * A B -> A번째 탑의 가장 위 원판을 B번째 탑의 가장 위로 이동.
 * @time_complex O(2^N)
 * @perf
 */

public class Main {

    static StringBuilder sb;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        BigInteger cnt = new BigInteger("2").pow(N).subtract(new BigInteger("1"));
        System.out.println(cnt);
        if (N <= 20) {
            hanoi(N, 1, 3, 2);
            System.out.println(sb);
        }
    }

    static void hanoi(int numPlate, int topFrom, int topTo, int topMiddle) {
        if (numPlate == 1) {
            move(topFrom, topTo);
            return;
        }
        hanoi(numPlate - 1, topFrom, topMiddle, topTo);
        move(topFrom, topTo);
        hanoi(numPlate - 1, topMiddle, topTo, topFrom);
    }

    static void move(int topFrom, int topTo) {
        sb.append(topFrom).append(" ").append(topTo).append("\n");
    }
}