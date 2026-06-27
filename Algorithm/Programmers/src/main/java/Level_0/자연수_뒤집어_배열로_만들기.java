package Level_0;

class Solution {
    public int[] solution(long n) {
        String s = String.valueOf(n);
        String val[] = s.split("");
        int ans[] = new int[val.length];
        int len = val.length;
        if(len%2==1) ans[len/2]=Integer.parseInt(val[len/2]); // 가운데 처리


        for(int i=0; i<len/2; i++){
            int tmp = Integer.parseInt(val[i]);
            ans[i] = Integer.parseInt(val[len-1-i]);
            ans[len-1-i] = tmp;
        }


        return ans;
    }
}

// 정수를 문자로
// 문자 쪼개기 -> split

// 길이 절반 순회
// 1 -> 5 swap, 2 -> 4 swap
// 중앙값 처리(홀수)