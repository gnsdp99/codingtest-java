class Solution {
    public int[] solution(int[] answers) {
        int[][] personArr = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        int[] scoreArr = new int[3];
        int[] idxArr = new int[3];
        
        for (int answer: answers) {
            for (int i = 0; i < 3; i++) {
                if (answer == personArr[i][idxArr[i]]) {
                    ++scoreArr[i];
                }
                idxArr[i] = (idxArr[i] + 1) % personArr[i].length;
            }
        }
        
        int maxScore = 0;
        for (int score: scoreArr) {
            if (maxScore < score) {
                maxScore = score;
            }
        }
        
        int cnt = 0;
        for (int score: scoreArr) {
            if (maxScore == score) {
                ++cnt;
            }
        }
        
        int[] answer = new int[cnt];
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            if (maxScore == scoreArr[i]) {
                answer[idx++] = i + 1;
            }
        }
        
        return answer;
    }
}