import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구현
 * - 두 문자열의 최장 공통 부분 수열 길이를 구한다.
 * - D(i, j): 문자열 A, B가 있을 때 A[1:i]와 B[1:j]의 최장 공통 부분 수열 길이
 *
 * 입력
 * - 문자열의 길이 L [1, 1,000]
 *
 * 출력
 * - LCS 길이 출력
 *
 * 시간복잡도 O(L^2)
 *
 * 결과
 *
 * */
public class Main {
    static char[] string1, string2;
    static int[][] lcs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        string1 = br.readLine().toCharArray();
        string2 = br.readLine().toCharArray();
        int len1 = string1.length;
        int len2 = string2.length;
        lcs = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                lcs[i][j] = string1[i - 1] == string2[j - 1] ? lcs[i - 1][j - 1] + 1 : lcs[i - 1][j] >= lcs[i][j - 1] ? lcs[i - 1][j] : lcs[i][j - 1];
            }
        }
        System.out.println(lcs[len1][len2]);
    }
}