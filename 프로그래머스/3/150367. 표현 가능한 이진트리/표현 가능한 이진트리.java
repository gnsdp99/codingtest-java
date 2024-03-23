/*
포화 이진 트리는 노드의 개수가 2^n - 1개. 이때 n은 트리의 높이.
주어진 수를 이진수로 만들고 포화 이진 트리가 되도록 앞에 0을 추가.
이진수의 개수가 k개일 때 루트 노드는 k / 2번째에 있음.
그리고 왼쪽 자식은 왼쪽으로 x칸에, 오른쪽 자식은 오른쪽으로 x칸에 있음. 
이 offset은 루트의 오른쪽 or 왼쪽 자식의 수 / 2 + 1부터 시작해 /2씩 감소.
모든 노드를 탐색할 동안 0인 이진수에게 1인 자식이 있으면 불가한 경우임.
*/

class Solution {
    
    int N;
    int[] answer;
    
    public int[] solution(long[] numbers) {
        
        N = numbers.length;
        answer = new int[N];
        
        for (int i = 0; i < N; i++) {
            
            String binStr = Long.toBinaryString(numbers[i]);
            
            int tmpLen = binStr.length();
            int tmp = 2;
            int height = 1;
            
            // 포화 이진 트리 만들기
            while (tmpLen > tmp - 1) {
                tmp *= 2;
                height++;
            }
            while (tmpLen < tmp - 1) {
                binStr = "0" + binStr;
                tmpLen++;
            }
            // System.out.println(height);
            // 0인 노드에게 1인 자식이 있는지 확인
            int rootIdx = tmpLen >> 1;
            answer[i] = DFS(binStr, rootIdx, ((tmpLen - rootIdx - 1) >> 1) + 1) ? 1 : 0;
        }
        return answer;
    }
    
    private boolean DFS(String binStr, int nodeIdx, int offset) {
        if (offset == 0) {
            
            return true;
        }
        
        if (binStr.charAt(nodeIdx) == '0'
         && (binStr.charAt(nodeIdx - offset) == '1'
         || binStr.charAt(nodeIdx + offset) == '1')) {
            
            return false;
        }
        
        if (!DFS(binStr, nodeIdx + offset, offset >> 1)) return false;
        if (!DFS(binStr, nodeIdx - offset, offset >> 1)) return false;
        
        return true;
    }
}