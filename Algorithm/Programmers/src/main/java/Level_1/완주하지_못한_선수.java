package Level_1;

import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> map = new HashMap<>();
        for(String s : participant){
            map.put(s,map.getOrDefault(s,0)+1);
        }

        for(String s : completion){
            if(map.containsKey(s)){
                if(map.get(s)<1)return s;
                else map.put(s, map.getOrDefault(s,0) -1);
            }
        }

        for(String s : map.keySet()){
            if(map.get(s) == 1)answer=s;
        }

        return answer;
    }
}


/*
문제
마라톤 참가자 중 단 한 명만 완주하지 못했다.
완주하지 못한 선수의 이름을 반환한다.

제한
1 <= participant.length <= 100,000
completion.length = participant.length - 1
동명이인이 존재할 수 있다.

풀이
participant와 completion을 이중 for문으로 직접 비교하면
최악의 경우 O(N^2)이므로 N이 100,000일 때 비효율적이다.

동명이인을 처리해야 하므로 Set은 사용할 수 없고,
HashMap<String, Integer>를 사용해
key = 선수 이름
value = 해당 이름의 참가자 수
형태로 저장한다.

1. participant를 순회하며 이름별 등장 횟수를 저장한다.
   map.put(name, map.getOrDefault(name, 0) + 1)

2. completion을 순회하며 완주한 선수의 개수를 1씩 감소시킨다.
   map.put(name, map.get(name) - 1)

3. map의 key를 순회하며 value가 1인 key를 찾는다.
   해당 key가 완주하지 못한 선수이다.

시간복잡도
평균 O(N)

공간복잡도
O(N)

상태값
Map<String, Integer> map


[좋았던 점]
- N 최대 100,000을 보고 O(N^2) 풀이를 피해야 한다고 판단한 점이 좋았다.
- 동명이인 때문에 Set이 아닌 HashMap을 사용해 이름별 개수를 관리한 접근이 적절했다.

[보완할 점]
- completion의 이름은 항상 participant에 존재하므로 containsKey()는 필요 없다.
- completion 전체를 순회해 개수를 감소시킨 뒤, 마지막에 value가 1인 key를 찾는 구조가 더 단순하다.
*/