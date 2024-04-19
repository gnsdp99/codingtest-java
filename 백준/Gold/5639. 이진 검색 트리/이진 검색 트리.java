import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static class Node {

        int num;
        Node left, right;

        Node(int num) {
            this.num = num;
        }
        void insert(int num) {
            if (num > this.num) {
                if (this.right == null) {
                    this.right = new Node(num);
                } else {
                    this.right.insert(num);
                }
            } else {
                if (this.left == null) {
                    this.left = new Node(num);
                } else {
                    this.left.insert(num);
                }
            }
        }
    }

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));
        while (true) {

            String str = br.readLine();
            if (str == null || str.equals("")) {
                break;
            }

            root.insert(Integer.parseInt(str));
        }
        postOrder(root);
        System.out.println(sb);
    }

    static void postOrder(Node cur) {

        if (cur == null) {
            return;
        }
        postOrder(cur.left);
        postOrder(cur.right);
        sb.append(cur.num).append("\n");
    }
}