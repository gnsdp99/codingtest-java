import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c =c;
        }
    }

    static int[] bitString = new int[27]; // 0 ~ 8: 행, 9 ~ 17: 열, 18 ~ 26: 3x3
    static int[][] sdoku = new int[9][9];
    static ArrayList<Pos> zeros = new ArrayList<>();
    static Map<Integer, Boolean> root = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 각 행, 열, 3x3보드의 비트열을 기록한다.
        // 백트래킹

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i <= 6; i += 3) {
            for (int j = 0; j <= 6; j += 3) {
                root.put(9 * i + j, true);
            }
        }

        for (int i = 0; i < 9; i++) {
            String row = br.readLine();
            for (int j = 0; j < 9; j++) {
                sdoku[i][j] = row.charAt(j) - '0';
                if (sdoku[i][j] == 0) {
                    zeros.add(new Pos(i, j));
                } else {
                    int bit = (1 << sdoku[i][j]);
                    bitString[i] |= bit;
                    bitString[9 + j] |= bit;
                    int square = (i / 3) * 3 + (j / 3);
                    bitString[18 + square] |= bit;
                }
            }
        }

        backtracking(0);
        System.out.println(sb);
    }

    static boolean backtracking(int kth) {

        if (kth == zeros.size()) {
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    sb.append(sdoku[r][c]);
                }
                sb.append("\n");
            }
            return true;
        }

        Pos pos = zeros.get(kth);
        int r = pos.r;
        int c = pos.c;

        int square = (r / 3) * 3 + (c / 3);

        for (int i = 1; i <= 9; i++) {
            int bit = 1 << i;
            if (((bitString[r] & bit) != 0)
            || ((bitString[9 + c] & bit) != 0)
            || ((bitString[18 + square] & bit) != 0)) {
                continue;
            }

            set(r, c, square, bit, i);
            if (backtracking(kth + 1)) {
                return true;
            }
            unset(r, c, square, bit);
        }
        return false;
    }

    static void set(int r, int c, int square, int bit, int num) {
        bitString[r] |= bit;
        bitString[9 + c] |= bit;
        bitString[18 + square] |= bit;
        sdoku[r][c] = num;
    }

    static void unset(int r, int c, int square, int bit) {
        bitString[r] &= ~bit;
        bitString[9 + c] &= ~bit;
        bitString[18 + square] &= ~bit;
        sdoku[r][c] = 0;
    }

    static boolean isIn(int r, int c) {
        return r >= 0 && r < 9 && c >= 0 && c < 9;
    }
}