import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

class Solution {
    
    static int N, half, win, lose, maxWin;
    static int[] result;
    static Map<Integer, Integer> selectedCnt, notSelectedCnt;
    
    public int[] solution(int[][] dice) {
        N = dice.length;
        half = N >> 1;
        // 조합
        getCombination(1, 2, (1 << 1), dice);
        Arrays.sort(result);
        return result;
    }
    
    void getCombination(int kth, int start, int selectBit, int[][] dice) {
        if (kth == half) {
            // 승, 무, 패 계산
            int[] selected = new int[half];
            int[] notSelected = new int[half];
            int idxSel = 0, idxNotSel = 0;
            for (int i = 1; i <= N; i++) {
                if ((selectBit & (1 << i)) != 0) selected[idxSel++] = i;
                else notSelected[idxNotSel++] = i;
            }
            
            selectedCnt = new HashMap<>();
            DFS(selected, 0, 0, dice, selectedCnt);
            
            notSelectedCnt = new HashMap<>();
            DFS(notSelected, 0, 0, dice, notSelectedCnt);
            
            win = lose = 0;
            startGame();
            int res = win >= lose ? win : lose;
            if (res > maxWin) {
                maxWin = res;
                if (res == win) result = Arrays.copyOf(selected, half);
                else result = Arrays.copyOf(notSelected, half);
            }
            return;
        }
        
        for (int i = start; i <= N; i++) {
            getCombination(kth + 1, i + 1, selectBit | (1 << i), dice);
        }
    }
    
    void startGame() {
        for (int keySel: selectedCnt.keySet()) {
            for (int keyNotSel: notSelectedCnt.keySet()) {
                int num = selectedCnt.get(keySel) * notSelectedCnt.get(keyNotSel);
                if (keySel > keyNotSel) {
                    win += num;
                } else if (keySel < keyNotSel) {
                    lose += num;
                }
            }
        }
    }
    
    void DFS(int[] selected, int kth, int sum, int[][] dice, Map<Integer, Integer> cntMap) {
        if (kth == half) {
            cntMap.put(sum, cntMap.containsKey(sum) ? cntMap.get(sum) + 1 : 1);
            return;
        }
        for (int i = 0; i < 6; i++) {
            DFS(selected, kth + 1, sum + dice[selected[kth] - 1][i], dice, cntMap);
        }
    }
}