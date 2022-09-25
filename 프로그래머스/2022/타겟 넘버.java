class Solution {
    int n;
    int[] numbers;
    int target;
    int count;
    public int solution(int[] numbers, int target) {
        n = numbers.length;
        this. numbers = numbers;
        this.target = target;
        dfs(0, 0);
        return count;
    }
    
    void dfs(int idx, int re){
        if(idx == n){
            if(re == target){
                count++;
            }
            return;
        }
        
        dfs(idx + 1, re + numbers[idx]);
        dfs(idx + 1, re - numbers[idx]);
    }
}