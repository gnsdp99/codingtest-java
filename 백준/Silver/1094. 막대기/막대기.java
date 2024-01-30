import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 김예훈
 * @date 24/01/30
 * @link https://www.acmicpc.net/problem/1094
 * @keyword_solution
 * 반으로 자른 막대의 길이가 x보다 크다면 하나를 버린 후 다시 절반으로 자른다.
 * x보다 작다면 하나를 버리는 대신 절반으로 자른다.
 *
 * 가지고 있는 막대의 길이를 비트로 표현한다.
 * 막대의 합이 X보다 클 때: 가장 짧은 막대를 하나 버리고 하나는 반으로 줄인다.
 * 막대의 합이 X보다 작을 때: 가장 짧은 막대를 하나 놔두고 하나는 반으로 줄인다.
 * 막대가 두 개일 때 하나를 절반으로 자르는 연산: (길이 - 1)번째 비트만 1인 수와 OR 연산
 * @input
 * X는 64이하의 자연수. (X가 2의 제곱수인 경우 바로 구할 수 있음)
 * @output
 * Xcm를 만들 수 있는 막대의 개수 (전체 막대를 합쳤을 때 X가 나와야 함)
 * @time_complex
 * @perf
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int lenPole = 1 << 6; // 64cm
        int minLen = 6;
        int cnt = 1;

        while (true) {
            if (lenPole == X) {
                System.out.println(cnt);
                break;
            } else if (lenPole > X) {
                lenPole &= ~(1 << minLen);
            } else {
                cnt++;
            }
            lenPole |= (1 << (minLen - 1));
            minLen -= 1;
        }
    }
}