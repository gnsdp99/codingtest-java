import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] dices;
    static int[] pair = new int[]{5, 3, 4, 1, 2, 0};
    static int[] result = new int[6];
    static int[] top = new int[6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        dices = new int[N][6];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dices[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 6; i++) {
            top[i] = dices[0][pair[i]];
            result[i] = findMaxVal(dices[0], i, pair[i]);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 6; j++) {
                findNextDice(i, j);
            }
        }

        int answer = 0;
        for (int i = 0; i < 6; i++) {
            answer = Math.max(answer, result[i]);
        }
        System.out.println(answer);
    }

    static void findNextDice(int diceIdx, int seqIdx) {
        int idx = findIdx(dices[diceIdx], top[seqIdx]);
        top[seqIdx] = dices[diceIdx][pair[idx]];
        result[seqIdx] += findMaxVal(dices[diceIdx], idx, pair[idx]);
    }

    static int findIdx(int[] dice, int target) {
        for (int i = 0; i < 6; i++) {
            if (dice[i] == target) {
                return i;
            }
        }
        return -1;
    }

    static int findMaxVal(int[] dice, int a, int b) {
        int maxVal = 0;
        for (int i = 0; i < 6; i++) {
            if (i == a || i == b) {
                continue;
            }
            maxVal = Math.max(maxVal, dice[i]);
        }
        return maxVal;
    }
}