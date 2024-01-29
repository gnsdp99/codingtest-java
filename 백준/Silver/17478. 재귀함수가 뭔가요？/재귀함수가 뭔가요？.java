import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 김예훈_1147165
 * @date 24/01/29
 * @link https://www.acmicpc.net/problem/17478
 * @keyword_solution
 * 재귀.
 * 반복 요소: 출력
 * 매개변수: 진행한 횟수
 * 종료 조건: 진행한 횟수가 N보다 커질 때
 * @input
 * 재귀 횟수 (1 <= N <= 50) 
 * @output
 * 재귀 횟수에 따른 챗봇의 응답 출력.
 * @time_complex
 * @perf
 */

public class Main {

    static int N;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        print(0);
        System.out.println(sb);
    }

    static void print(int cnt) {
        if (cnt > N) {
            return;
        }

        printUnderBar(cnt);
        sb.append("\"재귀함수가 뭔가요?\"\n");

        if (cnt == N) {
            printUnderBar(cnt);
            sb.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
        } else {
            printUnderBar(cnt);
            sb.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
            printUnderBar(cnt);
            sb.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
            printUnderBar(cnt);
            sb.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
        }

        print(cnt + 1);

        printUnderBar(cnt);
        sb.append("라고 답변하였지.\n");
    }

    static void printUnderBar(int cnt) {
        for (int i = 0; i < cnt; i++) {
            sb.append("____");
        }
    }
}