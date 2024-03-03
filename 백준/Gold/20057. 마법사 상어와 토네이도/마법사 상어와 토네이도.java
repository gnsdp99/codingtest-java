import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - 가운데에서 토네이도 시작
 * - 토네이도가 이동하는 곳에 존재하는 모래가 흩어지는 것임.
 * - 토네이도 방향에 따라 주어진 비율표를 회전해야 함.
 * - 정답은 "격자의 밖으로 나간" 모래의 양을 구하는 것임.
 *
 * - 비율표 회전 규칙
 * 왼쪽을 (x, y)로 가정
 * 아래쪽은 (-y, x)
 * 오른쪽은 (x, -y)
 * 위쪽은 (y, -x)
 * */

public class Main {
    static int N, ans;
    static int tx, ty, td; // 토네이도 정보
    static int[][] board;
    static int[][] delta = {
            {0, -1}, // 좌
            {1, 0}, // 하
            {0, 1}, // 우
            {-1, 0} // 상
    };
    static int[][][] percentTable = {
            {{-1, 0, 1}, {1, 0, 1}, {-1, -1, 7}, {1, -1, 7}, {-2, -1, 2}, {2, -1, 2}, {-1, -2, 10}, {1, -2, 10}, {0, -3, 5}},
            {},
            {},
            {}
    };
    static final int X = 0, Y = 1, PER = 2; // 인덱스 상수
    static final int LEFT = 0, DOWN = 1, RIGHT = 2, UP = 3; // 방향 상수
    static final int ONE = 1, TWO = 2, FIVE = 5, SEVEN = 7, TEN = 10; // 비율 상수
    static int[] cntTable; // 이동 규칙을 위한 테이블
    static int[] percentage; // 모래 비율 별 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        initPercentTable();

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        percentage = new int[11]; // 1%, 2%, 5%, 7%, 10%
        cntTable = new int[N];
        for (int i = 1; i < N - 1; i++) {
            cntTable[i] = i << 1;
        }
        cntTable[N - 1] = ((N - 1) << 1) + (N - 1);

        int center = N >> 1;
        tx = center; ty = center; td = LEFT;

        int cntIdx = 1; // cntTable의 인덱스, cntTable의 값이 0이 되면 인덱스 증가
        int restCnt = cntIdx; // 현재 방향으로 이동할 남은 횟수
        while (tx != 0 || ty != 0) {
            if (cntTable[cntIdx] == 0) cntIdx++;
            if (restCnt == 0) {
                td = (td + 1) % 4; // 방향 전환
                restCnt = cntIdx;
            }

            int nx = tx + delta[td][X];
            int ny = ty + delta[td][Y];
            if (board[nx][ny] != 0) { // 모래 있을 때만
                ans += scatter(nx, ny, tx, ty, td);
            }
            tx = nx; ty = ny;
            restCnt--;
            cntTable[cntIdx]--;
        }
        System.out.println(ans);
    }

    static int scatter(int x, int y, int tx, int ty, int d) { // 격자 밖으로 나간 모래 양 리턴
        percentage[ONE] = board[x][y] / 100; percentage[TWO] = (board[x][y] << 1) / 100; percentage[SEVEN] = ((board[x][y] << 3) - board[x][y]) / 100;
        percentage[FIVE] = ((board[x][y] << 2) + board[x][y]) / 100; percentage[TEN] = ((board[x][y] << 3) + (board[x][y] << 1)) / 100;

        int numOut = 0; // 격자 밖을 나간 모래 양
        int numIn = 0; // 격자로 뿌려진 모래 양
        for (int i = 0; i < 9; i++) {
            int ntx = tx + percentTable[d][i][X];
            int nty = ty + percentTable[d][i][Y];
            int perIdx = percentTable[d][i][PER];
            if (isIn(ntx, nty)) {
                board[ntx][nty] += percentage[perIdx];
                numIn += percentage[perIdx];
            } else { // 벗어남
                numOut += percentage[perIdx];
            }
        }

        int alpha = board[x][y] - (numIn + numOut); // 뿌려지고, 밖을 나간 모래 외 나머지
        int nx = x + delta[d][X]; // 알파 위치
        int ny = y + delta[d][Y];
        if (isIn(nx, ny)) board[nx][ny] += alpha;
        else numOut += alpha;
        board[x][y] = 0;
        return numOut;
    }

    static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void initPercentTable() {
        percentTable[RIGHT] = new int[9][3];
        percentTable[DOWN] = new int[9][3];
        percentTable[UP] = new int[9][3];
        int[][] left = percentTable[LEFT];

        for (int i = 0; i < 9; i++) {
            percentTable[DOWN][i][X] = -left[i][Y];
            percentTable[DOWN][i][Y] = left[i][X];
            percentTable[DOWN][i][PER] = left[i][PER];
        }
        for (int i = 0; i < 9; i++) {
            percentTable[RIGHT][i][X] = left[i][X];
            percentTable[RIGHT][i][Y] = -left[i][Y];
            percentTable[RIGHT][i][PER] = left[i][PER];
        }
        for (int i = 0; i < 9; i++) {
            percentTable[UP][i][X] = left[i][Y];
            percentTable[UP][i][Y] = -left[i][X];
            percentTable[UP][i][PER] = left[i][PER];
        }
    }
}