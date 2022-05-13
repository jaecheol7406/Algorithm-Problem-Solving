class Solution {
    public String solution(String new_id) {
        StringBuffer s = new StringBuffer(new_id.toLowerCase());
        String s2 = "";
        char prev = '\0';
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '.' && prev == '.'){
                continue;
            }
            if((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9' ) || c == '-' || c == '_' || c == '.'){
                s2 += c;
                prev = c;
            }
        }
        
        System.out.println(s2);
        
        if(s2.length() == 0 || s2.equals(".") || s2.equals("..")){
            s2 = "a";
        } else {
            if(s2.charAt(0) == '.'){
                s2 = s2.substring(1, s2.length());
            }
            if(s2.charAt(s2.length() - 1) == '.'){
                s2 = s2.substring(0, s2.length() - 1);
            }
        }
        
        if(s2.length() >= 16){
            s2 = s2.substring(0, 15);
            if(s2.charAt(s2.length() - 1) == '.'){
                s2 = s2.substring(0, s2.length() - 1);
            }
        }
        
        if(s2.length() <= 2){
            char last = s2.charAt(s2.length() - 1);
            int len = s2.length();
            for(int i = 0; i < 3 - len; i++){
                s2 += last;
            }
        }
        
        return s2;
    }
}