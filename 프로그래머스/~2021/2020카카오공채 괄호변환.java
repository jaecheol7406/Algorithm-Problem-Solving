import java.util.*;
class Solution {
    public String solution(String p) {
        return check(p);
    }
    
    public String check(String s){
        if(s.equals(""))
            return "";
        
        Stack<Character> st = new Stack<>();
        
        int leftCount = 0, rightCount = 0;
        
        String u = "";
        int i = 0;
        for(; i < s.length(); i++){
            char c = s.charAt(i);
            u += c;
            
            if(c == '('){
                leftCount++;
            } else {
                rightCount++;
            }
            
            if(c == ')') {
                if(!st.empty() && st.peek() == '(')
                    st.pop();
                else
                    st.push(c);
            } else {
                st.push(c);
            }
            
            if(leftCount != 0 && leftCount == rightCount)
                break;
        }
        
        String v = "";
        if(i != s.length() - 1)
            v = s.substring(i + 1);
        
        if(st.empty()){
            return u + check(v);
        } else {
            String newU = u.substring(1, u.length() - 1);
            String changed = "";
            for(int j = 0; j < newU.length(); j++){
                if(newU.charAt(j) == '('){
                    changed += ')';
                } else {
                    changed += '(';
                }
            }
            return "(" + check(v) + ")" + changed;
        }
    }
}