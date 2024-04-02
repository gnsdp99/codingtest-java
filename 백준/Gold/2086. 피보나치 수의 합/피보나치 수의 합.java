import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[][] tmp = {{1, 1}, {1, 0}};
    static final int size = 2;
    static final int P = 1_000_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long[][] fiboAPlus = divide(A + 1, tmp);
        long[][] fiboBPlus = divide(B + 2, tmp);

        long ans = ((fiboBPlus[0][1] % P) - (fiboAPlus[0][1] % P) + P) % P;
        System.out.println(ans);
    }

    static long[][] divide(long N, long[][] fibo) {

        if (N > 1) {

            fibo = divide(N >> 1, fibo);
            fibo = matMul(fibo, fibo);

            if (N % 2 != 0) {
                fibo = matMul(fibo, tmp);
            }
        }
        return fibo;
    }

    static long[][] matMul(long[][] A, long[][] B) {

        long[][] res = new long[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    res[i][j] += (A[i][k] % P) * (B[k][j] % P) % P;
                }
            }
        }
        return res;
    }
}