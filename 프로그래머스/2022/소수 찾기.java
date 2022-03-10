class Solution {
    int n;
    String nums;
    int count;
    boolean[] check = new boolean[10000000];
    public int solution(String numbers) {
        this.n = numbers.length();
        this.nums = numbers;
        for(int r = 1; r <= n; r++){
            dfs("", 0, new boolean[n], r);    
        }
        return count;
    }
    
    void dfs(String s, int idx, boolean[] visit, int r){
        if(idx == r){
            int num = Integer.parseInt(s);
            if(!check[num]){
                check[num] = true;
                if(isPrime(num))
                    count++;
            }
            return;
        }
        
        for(int i = 0; i < n; i++){
            if(!visit[i]){
                visit[i] = true;
                dfs(s + nums.charAt(i), idx + 1, visit, r);
                visit[i] = false;
            }
        }
    }
    
    boolean isPrime(int n){
        if(n <= 1)
            return false;
        if(n <= 3)
            return true;
        
        for(int d = 2; d <= n / 2; d++){
            if(n % d == 0)
                return false;
        }
        return true;
    }
}