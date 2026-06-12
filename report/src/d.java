import java.util.Arrays;

//class Solution {
//    public int[] solution(int[] num_list, int n) {
//        int[] ans= new int[(num_list.length+n-1)/n];
//        int idx=0;
//        for (int i = 0; i < num_list.length; i+=n) {
//            ans[idx++]=num_list[i];
//        }
//
//        return ans;
//    }
//}
//class Solution {
//    public int solution(int[] num_list) {
//        String str1="";
//        String str2="";
//        for(int a : num_list){
//            if(a%2==0)str1+=a;
//            else str2+=a;
//        }
//        return Integer.parseInt(str1)+Integer.parseInt(str2);
//    }
//}

//class Solution {
//    public int solution(int[] num_list) {
//        int ans = 0;
//        for(int i=0; i<num_list.length; i++){
//            if(num_list[i]<0){ans=i;break;}
//        }
//        return ans;
//
//    }
//}

//class Solution {
//    public int solution(String my_string, String target) {
//
//        return my_string.contains(target)?1:0;
//    }
//}

class Solution {
    public int[] solution(int[] num_list) {
        int[] answer = new int[(num_list.length+1)];
        for(int i=0; i<num_list.length; i++){
            if(i==(num_list.length-1)){
                if(num_list[i-1]>num_list[i]){
                    answer[num_list.length]=(num_list[i]-num_list[i-1]);
                }
                else if(num_list[i-1]<num_list[i]) {
                    answer[num_list.length] = (num_list[i] * 2);
                }else answer[i]=num_list[i];


            }
        }
        return answer;

    }
}
public class d {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans = {4,2,6,1,7,6};
        String str = "banana";
//        System.out.println(solution.solution(2,91));
//        System.out.println(Arrays.toString(solution.solution(ans,2)));
//        System.out.println(solution.solution(str,"ana"));
        double ans2 = 1.42;
        int aa = (int)ans2;
//        System.out.println((int)ans2);
        int[] num_list = {5,2,1,7,5};
        int[] answer = new int[(num_list.length+1)];
        for(int i=0; i<num_list.length; i++){
            System.out.println(i + "  " + (num_list.length-1));
            if(i==(num_list.length-1)){
                if(num_list[i-1]<num_list[i]){
                    answer[num_list.length]=(num_list[i]-num_list[i-1]);
                }
                else if(num_list[i-1]>=num_list[i]) {
                    answer[num_list.length] = (num_list[i] * 2);
                }

            }
            answer[i]=num_list[i];
        }
        System.out.println(Arrays.toString(answer));
    }
}
