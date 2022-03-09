class Solution {
    int[] left = new int[]{3,0};
    int[] right = new int[]{3,2};
    
    public String solution(int[] numbers, String hand) {
        String re = "";
        for(int num : numbers){
            int[] p = getPos(num);
            if(p[1] == 0){
                change(left, p);
                re += "L";
            } else if(p[1] == 2){
                change(right, p);
                re += "R";
            } else {
                int leftDif = Math.abs(left[0] - p[0]) + Math.abs(left[1] - p[1]);
                int rightDif = Math.abs(right[0] - p[0]) + Math.abs(right[1] - p[1]);
                if(leftDif == rightDif){
                    if(hand.equals("right")){
                        change(right, p);
                        re += "R";
                    } else {
                        change(left, p);
                        re += "L";
                    }
                } else if(leftDif > rightDif){
                    change(right, p);
                    re += "R";
                } else {
                    change(left, p);
                    re += "L";
                }
            }
        }
        
        return re;
    }
    
    void change(int[] from, int[] to){
        from[0] = to[0];
        from[1] = to[1];
    }
    
    int[] getPos(int num){
        if(num == 0){
            return new int[]{3, 1};
        }
        
        return new int[] {(num-1) / 3, (num-1) % 3};
    }
}