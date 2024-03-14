/*
- 타깃 노드는 진입 차수가 0이고 진출 차수가 2 이상인 노드이다.
- 따라서 진입 차수를 구해 타깃 노드를 찾는다.
- 그리고 진입 차수가 0인 노드는 막대 그래프의 헤드이고, 진입 차수, 진출 차수 모두 2인 노드는 8자 그래프의 중앙이다.
- 도넛 그래프의 개수는 타깃 노드의 진출 차수 - (막대 그래프 수 + 8자 그래프 수) 이다.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Solution {
    
    class Node {
        int num;
        Node next;
        
        Node(int num, Node next) {
            this.num = num;
            this.next = next;
        }
    }
    
    int target, numDoughnut, numStick, numEight;
    HashSet<Integer> nodes = new HashSet<>();
    HashMap<Integer, Integer> inDegrees = new HashMap<>();
    HashMap<Integer, Integer> outDegrees = new HashMap<>();
    Node[] adjList;
    boolean[] visited;
    
    public int[] solution(int[][] edges) {
        
        adjList = new Node[edges.length + 2];
        
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            nodes.add(a);
            nodes.add(b);
            outDegrees.put(a, outDegrees.containsKey(a) ? outDegrees.get(a) + 1 : 1);
            inDegrees.put(b, inDegrees.containsKey(b) ? inDegrees.get(b) + 1 : 1);
            adjList[a] = new Node(b, adjList[a]);
        }
        
        // 타깃 노드 구하기
        for (int i = 1; i <= nodes.size(); i++) {
            if (!inDegrees.containsKey(i) &&
               outDegrees.containsKey(i) && outDegrees.get(i) >= 2) {
                target = i;
                break;
            }
        }
        // 타깃 노드에서 연결된 각 그래프의 종류 구하기
        visited = new boolean[nodes.size() + 1];
        for (Node cur = adjList[target]; cur != null; cur = cur.next) {
            if (outDegrees.containsKey(cur.num) && outDegrees.containsKey(cur.num)
              && inDegrees.get(cur.num) == 3 && outDegrees.get(cur.num) == 2) {
                numEight++;
                continue;
            }
            
            visited[cur.num] = true;
            DFS(cur.num);
            visited[cur.num] = false;
        }
        
        numStick = outDegrees.get(target) - (numDoughnut + numEight);
        
        return new int[] {target, numDoughnut, numStick, numEight};
    }
    
    boolean DFS(int num) {
        for (Node cur = adjList[num]; cur != null; cur = cur.next) {
            if (inDegrees.containsKey(cur.num) && outDegrees.containsKey(cur.num) &&
               inDegrees.get(cur.num) == 2 && outDegrees.get(cur.num) == 2) {
                numEight++;
                return true;
            }
            
            if (visited[cur.num]) {
                numDoughnut++;
                return true;
            };
            
            visited[cur.num] = true;
            boolean flag = DFS(cur.num);
            visited[cur.num] = false;
            if (flag) return true;
        }
        return false;
    }
}