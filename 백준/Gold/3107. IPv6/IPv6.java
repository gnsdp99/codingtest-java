import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] ipv6 = br.readLine().split(":");
        int len = ipv6.length;

        int cnt = 0;
        int start = -1;
        int next = -1;
        for (int i = 0; i < len; i++) {
            if (!ipv6[i].isEmpty()) {
                cnt++;
                if (ipv6[i].length() < 4) {
                    StringBuilder zero = new StringBuilder();
                    for (int j = 0; j < 4 - ipv6[i].length(); j++) {
                        zero.append("0");
                    }
                    ipv6[i] = zero.append(ipv6[i]).toString();
                }
                if (start != -1 && next == -1) {
                    next = i;
                }
            } else {
                if (start == -1) {
                    start = i;
                }
            }
        }

        if (start == -1) {
            start = cnt;
        }

        if (next == -1) {
            next = 8;
        }

        if (start < 8) {

            StringBuilder sb = new StringBuilder();

            int i = 0;
            for (;i < start; i++) {
                sb.append(ipv6[i]).append(":");
            }
            for (int j = 0; j < 8 - cnt; j++) {
                sb.append("0000:");
            }
            for (i = next; i < len; i++) {
                sb.append(ipv6[i]).append(":");
            }
            if (sb.charAt(sb.length() - 1) == ':') {
                sb.deleteCharAt(sb.length() - 1);
            }
            System.out.println(sb);

        } else {
            System.out.println(String.join(":", ipv6));
        }
    }
}