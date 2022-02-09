class Solution {
    public int[] solution(String s) {
        int time = 0;
        int total = 0;
        while(true){
            if(s.equals("1")){
                break;
            }
            
            int count = 0;
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(c == '1')
                    count++;
            }
            
            total += s.length() - count;
            s = binary(count);
            time++;
        }
        
        return new int[]{time, total};
    }
    
    public String binary(int n){
        if(n == 1)
            return "1";
        
        StringBuffer sb = new StringBuffer("");
        while(true){
            int a = n / 2;
            int b = n % 2;
            sb.insert(0, String.valueOf(b));
            if(a == 1)
                break;
            n = a;
        }
        return sb.insert(0, "1").toString();
    }
}