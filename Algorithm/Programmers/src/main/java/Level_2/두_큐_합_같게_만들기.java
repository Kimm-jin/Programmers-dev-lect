package org.example.programmers.Lever_2;

import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        long sum1=0, sum2=0, target;
        int qLimit = (queue1.length)*3;
        int ans = 0;

        for(int i=0; i<queue1.length; i++){
            sum1+=queue1[i];
            sum2+=queue2[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }
        if((sum1+sum2)%2==1)return -1;
        target=(sum1+sum2)/2;

        for(int i=0; i<qLimit; i++){
            if(sum1==target&&sum2==target){
                return ans;
            }

            int x;
            if(sum1<target){
                x= q2.pop();
                q1.add(x);
                sum2-=x;
                sum1+=x;
                ans++;
            }else if(sum2<target){
                x= q1.pop();
                q2.add(x);
                sum1-=x;
                sum2+=x;
                ans++;
            }
        }

        return -1;
    }
}


// 둘중 하나의 큐를 골라 pop, 다른큐에 insert 하여 각 큐의 원소의 합이 같도록 만든다.
// 필요한 작업으 ㅣ최소횟수.
// pop과 insert까지 했을때 1회
// 두 큐에 담긴 합/2 가 target

// q1,q2 length <= 300,000 -> 최대 n log n정도로 끝내야할듯?
// 원소값 <= 10^9 int는 21억이니 long써야할듯

// 큐의 전체 원소를 보려면 다 꺼내야함 -> 각 큐의 합을 처음에 저장해두고 시작
// 큐합의 target을 넘지않는다면? -> q1 < target 이면 q2에서 pop, q1에 insert
// q1.length = q2.length

// 상태값
// 전체합 long sum1, long sum2
// long tartget = sum1+sum2/2;
// 순회하며 pop,insert해줘야 할 값을 저장할 변수 x
// target을 구하지 못할경우 -> 각 큐의 length*2정도만큼만 순회

/* ---------------- 피드백 ------------------------*/
/*

[좋았던 점]
- 문제 조건에서 원소 합이 int 범위를 넘을 수 있음을 보고 sum1, sum2를 long으로 잡은 점은 좋았다.
- 큐의 합을 매번 다시 계산하지 않고, pop/add 할 때 sum을 갱신하는 방향은 맞았다.
- sum1 < target이면 q2에서 q1으로 가져와야 한다는 방향도 맞았다.

[틀린 원인]
1. 전체합이 홀수인 경우를 처음에 처리하지 않았다.
   - total % 2 == 1이면 두 큐의 합을 같게 만들 수 없으므로 바로 -1.

2. 반복 제한을 queue1.length * 2로 잡아서 부족했다.
   - 원소들이 q1 -> q2 -> q1 형태로 순환할 수 있으므로 *2는 가능한 케이스도 놓칠 수 있다.
   - 안전하게 queue1.length * 4 정도로 잡는다.(실제로 2로 잡았다 TC1만 틀림)

3. 성공 조건을 else-if 마지막에 넣어 구조가 복잡했다.
   - 매 반복 시작 시 sum1 == target이면 바로 return ans 하는 구조가 더 단순하다.

 */