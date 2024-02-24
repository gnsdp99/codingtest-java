import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 구현
 * - 두 문자열의 최장 공통 부분 수열 길이를 구한다.
 * - D(i, j): 문자열 A, B가 있을 때 A[1:i]와 B[1:j]의 최장 공통 부분 수열 길이
 * - LCS의 길이를 구한 다음 LCS 문자열을 구하기 위해서는 스택을 사용한다.
 * - 마지막 행, 마지막 열부터 시작해 왼쪽 위 방향으로 탐색한다.
 * - dp[i][j] == dp[i - 1][j]이면 왼쪽으로 이동, dp[i][j] == dp[i][j - 1]이면 위로 이동.
 * - 둘 다 아니면 대각선 위로 이동 후 스택에 삽입.
 *
 * 입력
 * - 문자열의 길이 L [1, 1,000]
 *
 * 출력
 * - LCS 길이와 해당 LCS 출력
 *
 * 시간복잡도 O(L^2)
 *
 * 결과
 *
 * */
public class Main {
    static char[] string1, string2, lcsString;
    static int[][] lcs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        string1 = br.readLine().toCharArray();
        string2 = br.readLine().toCharArray();
        int len1 = string1.length;
        int len2 = string2.length;
        lcs = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (string1[i - 1] == string2[j - 1]) lcs[i][j] = lcs[i - 1][j - 1] + 1;
                else if (lcs[i - 1][j] >= lcs[i][j - 1]) lcs[i][j] = lcs[i - 1][j];
                else lcs[i][j] = lcs[i][j - 1];
            }
        }

        int lenString = lcs[len1][len2];

        sb.append(lenString).append("\n");
        if (lenString == 0) {
            System.out.println(sb);
            return;
        }

        lcsString = new char[lenString];
        int idx = lenString - 1;
        int i = len1, j = len2;
        while (i >= 1 && j >= 1) {
            if (lcs[i][j] == lcs[i][j - 1]) j--;
            else if (lcs[i][j] == lcs[i - 1][j]) i--;
            else {
                lcsString[idx--] = string1[i - 1];
                i--;
                j--;
            }
        }
        sb.append(lcsString);

        System.out.println(sb);
    }
}