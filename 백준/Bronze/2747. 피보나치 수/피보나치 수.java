import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] fibo = new int[2];

        fibo[0] = fibo[1] = 1;

        for (int i = 2; i < N; i++) {
            int tmp = fibo[0];
            fibo[0] = fibo[1];
            fibo[1] += tmp;
        }
        System.out.println(fibo[1]);
    }
}