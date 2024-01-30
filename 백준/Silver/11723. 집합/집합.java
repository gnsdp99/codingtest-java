import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김예훈
 * @date 24/01/30
 * @link https://www.acmicpc.net/problem/11723
 * @keyword_solution
 * 이 문제를 배열이나 컬렉션으로 구현하면 연산 횟수가 너무 많아진다.
 * 물론 x를 인덱스로 하는 boolean 배열을 사용할 수 있지만 20B를 사용해야 한다.
 * 또한 all과 empty 연산 시에는 배열 전체를 탐색해야 한다.
 * 하지만 비트마스킹을 사용하면 모든 연산을 한 번의 비트 연산으로 끝낼 수 있다.
 * 또한 x는 [1, 20]이므로 int 변수 하나로 해결할 수 있다.
 *
 * 초기화: 비어있는 공집합이므로 0.
 * add 연산: x번째 비트만 1인 수와 OR 연산을 한다. (1 << x)
 * remove 연산: x번째 비트만 1인 수와 AND 연산 후 존재하면 XOR 연산한다.
 * check 연산: x번째 비트만 1인 수와 AND 연산을 하고 그 결과가 0이 아닌지 확인한다.
 * toggle 연산: x번째 비트만 1인 수와 XOR 연산을 한다.
 * all 연산: 모든 비트가 1인 수와 OR 연산을 한다. (그냥 (1 << 21) - 1)
 * empty 연산: 0과 AND 연산을 한다. (그냥 0)
 * @input
 * 연산의 수는 1 <= M <= 3_000_000이다.
 * 모든 연산이 배열 전체를 탐색하는 것이라 해도 시간은 충분하지만, 비트마스킹을 이용하면 충분히 시간을 줄일 수 있다.
 * @output
 * check연산을 수행할 때만 그 결과를 출력한다.
 * @time_complex O(M)
 * @perf 324572kb 976ms
 */

public class Main {

    static int bitSet = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            char first = op.charAt(0);

            if (first == 'a') {
                if (op.charAt(1) == 'd'){
                    int num = Integer.parseInt(st.nextToken());
                    bitSet = bitSet | (1 << num);
                } else {
                    bitSet = (1 << 21) - 1;
                }
                continue;
            } else if (op.equals("empty")) {
                bitSet = 0;
                continue;
            }

            int num = Integer.parseInt(st.nextToken());
            if (first == 'r') {
                if (check(num)) {
                    toggle(num);
                }
            } else if (first == 'c') {
                if (check(num)) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
                sb.append("\n");
            } else if (first == 't') {
                toggle(num);
            }
        }
        System.out.println(sb);
    }

    static boolean check(int x) {
        return (bitSet & (1 << x)) != 0;
    }

    static void toggle(int x) {
        bitSet = bitSet ^ (1 << x);
    }
}