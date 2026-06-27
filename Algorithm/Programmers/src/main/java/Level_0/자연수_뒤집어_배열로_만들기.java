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

class Solution2 {
    public int[] solution(long n) {
        // 직접 쪼개고 내림차순
        String s = String.valueOf(n);
        int parts[] = new int[s.length()];

        for(int i=0; i<s.length(); i++){
            parts[i]=s.charAt(i)-'0';
        }
        // int배열은 내림차순 정렬이 없음
        for(int i=0; i<parts.length/2; i++){
            int tmp = parts[i];
            parts[i] = parts[parts.length -i -1];
            parts[parts.length -i -1] = tmp;
        }

        return parts;


//         split사용
//         String s = String.valueOf(n);
//         String ans[] = s.split("");

//         int[] answer = new int[ans.length];
//         Arrays.sort(ans,(a,b)->b.compareTo(a));
//         for(int i=0; i<ans.length; i++)
//             answer[i]=Integer.parseInt(ans[i]);
        // return answer;
    }
}