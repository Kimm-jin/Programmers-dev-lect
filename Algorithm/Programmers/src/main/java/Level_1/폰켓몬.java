package Level_1;
import java.util.*;


class Solution1 {
    public int solution(int[] nums) {
        int answer = 0;
        int arr[] = new int[200001];
        for(int i=0; i<nums.length; i++){
            arr[nums[i]]++;
        }
        for(int i=0; i<arr.length; i++){
            if(arr[i]>=1)answer++;
        }
        return (answer>nums.length/2)?nums.length/2:answer;
    }
}


class Solution2 {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int val : nums){
            set.add(val);
        }
        return Math.min(nums.length/2, set.size());
    }
}


/*
문제
전체 포켓몬 수 = N
가져갈 수 있는 포켓몬 수 = N / 2
같은 번호의 포켓몬은 같은 종류
최대한 많은 종류를 선택해야 함

제한
1 <= N <= 10,000
1 <= nums[i] <= 200,000

풀이 1
포켓몬 번호를 배열의 index로 사용하고,
해당 index의 값을 등장 횟수로 사용

nums를 순회하며 등장 횟수를 기록
카운팅 배열을 순회하며 1 이상인 index의 수를 센다

정답 = 서로 다른 종류 수와 N/2 중 작은 값

풀이 2
Set으로 중복제거한 값 저장
Set.size()와 nums.length중 더 작은값 반환

시간복잡도
O(N + 200000),  O(N)

사용 변수 및 자료구조 1
int[] arr = new int[200001]
int answer

사용 변수 및 자료구조 2
Set<Integer> set = new HashSet<>();
*/
