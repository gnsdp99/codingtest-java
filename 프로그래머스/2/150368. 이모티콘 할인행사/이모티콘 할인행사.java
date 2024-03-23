/*
이모티콘 각각 10% ~ 40% 중 하나를 선택하는 4^7개의 순열을 구한다.
*/

import java.util.Arrays;

class Solution {
    
    static int[] percent = {10, 20, 30, 40};
    static int N, maxJoin, maxCost;
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        N = emoticons.length;
        
        DFS(0, new int[N], users, emoticons);
        return new int[] {maxJoin, maxCost};
    }
    
    private void DFS(int kth, int[] curPercent, int[][] users, int[] emoticons) {
        
        if (kth == N) {
            int numJoin = 0, numCost = 0;
            
            for (int[] user: users) {
                
                int sum = 0;
                for (int i = 0; i < N; i++) {
                    if (curPercent[i] >= user[0]) {
                        sum += emoticons[i] * (100 - curPercent[i]) / 100;    
                    }
                }
                if (sum >= user[1]) {
                    numJoin++;
                } else {
                    numCost += sum;
                }
            }
            if (numJoin > maxJoin
            || (numJoin == maxJoin && numCost > maxCost)) {
                maxJoin = numJoin;
                maxCost = numCost;
            }
            
            return;
        }    
        
        for (int per = 0; per < 4; per++) {
            curPercent[kth] = percent[per];
            DFS(kth + 1, curPercent, users, emoticons);
        }
    }
}