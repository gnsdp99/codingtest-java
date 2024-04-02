import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    static class Trie {

        String str;
        Map<String, Trie> children = new TreeMap<>();
        boolean isLeaf;

        Trie() {}

        Trie(String str) {
            this.str = str;
        }
    }

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Trie root = new Trie();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            Trie cur = root;
            for (int j = 0; j < K; j++) {
                String str = st.nextToken();
                if (!cur.children.containsKey(str)) {
                    Trie trie = new Trie(str);
                    cur.children.put(str, trie);
                }
                cur = cur.children.get(str);
            }
            cur.isLeaf = true;
        }
        DFS(root, 0);
        System.out.println(sb);
    }

    static void DFS(Trie cur, int cnt) {

        for (String str : cur.children.keySet()) {

            for (int i = 0; i < cnt; i++) {
                sb.append("-");
            }
            sb.append(str).append("\n");

            DFS(cur.children.get(str), cnt + 2);
        }
    }
}