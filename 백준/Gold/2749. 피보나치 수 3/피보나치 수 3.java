import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static long[][] tmp = {{1, 1}, {1, 0}};
    static final int P = 1_000_000;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        long[][] fibo = new long[2][2];
        fibo[0][0] = fibo[0][1] = fibo[1][0] = 1;

        fibo = divide(N, fibo);

        System.out.println(fibo[0][1] % P);
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
        long[][] res = new long[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                for (int k = 0; k < A.length; k++) {
                    res[i][j] += (A[i][k] % P) * (B[k][j] % P) % P;
                }
            }
        }
        return res;
    }
}