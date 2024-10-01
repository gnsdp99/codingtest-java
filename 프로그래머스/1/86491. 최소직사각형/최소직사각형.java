class Solution {
    public int solution(int[][] sizes) {
        int maxOfMax = 0, maxOfMin = 0;
        for (int[] item: sizes) {
            int gt = Math.max(item[0], item[1]);
            int ls = Math.min(item[0], item[1]);
            maxOfMax = Math.max(maxOfMax, gt);
            maxOfMin = Math.max(maxOfMin, ls);
        }
        return maxOfMax * maxOfMin;
    }
}