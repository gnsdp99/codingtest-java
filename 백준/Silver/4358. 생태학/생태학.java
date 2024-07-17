import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    static Map<String, Integer> countMap = new TreeMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int totalCount = 0;
        while (true) {
            String name = br.readLine();
            if (name == null) {
                break;
            }
            countMap.put(name, countMap.getOrDefault(name, 0) + 1);
            ++totalCount;
        }

        for (Map.Entry<String, Integer> entry: countMap.entrySet()) {
            double val = 1.0 * entry.getValue() / totalCount * 100;
            sb.append(entry.getKey()).append(" ").append(String.format("%.4f", val)).append("\n");
        }
        System.out.println(sb);
    }
}