class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] re = new int[n];
        re[n - 1] = 0;
        for(int i = 0; i < n - 1; i++){
            int std = prices[i];
            int count = 0;
            for(int j = i + 1; j < n; j++){
                count++;
                if(std > prices[j])
                    break;
            }
            re[i] = count;
        }
        return re;
    }
}