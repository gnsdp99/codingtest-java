import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long S = Long.parseLong(br.readLine());

        int N = 1;
        long sum = 0;

        while (sum <= S) {
            sum += N++;
        }

        System.out.println(N - 2);
    }
}