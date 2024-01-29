import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    static StringBuilder sb;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        BigInteger cnt = new BigInteger("2").pow(N).subtract(new BigInteger("1"));
        System.out.println(cnt);
        if (N <= 20) {
            hanoi(N, 1, 3, 2);
            System.out.println(sb);
        }
    }

    static void hanoi(int numPlate, int topFrom, int topTo, int topMiddle) {
        if (numPlate == 1) {
            move(topFrom, topTo);
            return;
        }
        hanoi(numPlate - 1, topFrom, topMiddle, topTo);
        move(topFrom, topTo);
        hanoi(numPlate - 1, topMiddle, topTo, topFrom);
    }

    static void move(int topFrom, int topTo) {
        sb.append(topFrom).append(" ").append(topTo).append("\n");
    }
}