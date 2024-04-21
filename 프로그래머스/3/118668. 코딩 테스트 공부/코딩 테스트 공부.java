import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        
        int maxAlp = 0, maxCop = 0;
        for (int [] p: problems) {
            if (maxAlp < p[0]) {
                maxAlp = p[0];
            }
            if (maxCop < p[1]) {
                maxCop = p[1];
            }
        }
        
        if (alp >= maxAlp && cop >= maxCop) {
            return 0;
        }
        if (alp > maxAlp) {
            alp = maxAlp;
        } else if (cop > maxCop) {
            cop = maxCop;
        }
       
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i <= maxAlp; i++) {
            Arrays.fill(dp[i], 200);
        }
        dp[alp][cop] = 0;
        
        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                
                int fA = floor(a + 1, maxAlp);
                int fC = floor(c + 1, maxCop);
                dp[fA][c] = Math.min(dp[fA][c], dp[a][c] + 1);
                dp[a][fC] = Math.min(dp[a][fC], dp[a][c] + 1);
                for (int[] p: problems) {
                    if (a >= p[0] && c >= p[1]) {
                        fA = floor(a + p[2], maxAlp);
                        fC = floor(c + p[3], maxCop);
                        dp[fA][fC] = Math.min(dp[fA][fC], dp[a][c] + p[4]);
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
    
    int floor(int a, int max) {
        return a >= max ? max : a;
    }
}