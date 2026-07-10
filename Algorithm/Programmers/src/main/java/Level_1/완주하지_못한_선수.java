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
- participant.length가 최대 100,000이라는 조건을 보고,
  이중 for문으로 직접 비교하면 최악의 경우 O(N^2)이 된다는 점을 인식한 것은 좋았다.
- 빠른 탐색을 위해 해시 기반 자료구조를 떠올린 방향도 맞았다.
- 동명이인이 존재하므로 Set은 개수 정보를 보존할 수 없어 부적합하다고 판단한 것도 좋았다.
- HashMap<String, Integer>를 사용해
  key = 선수 이름
  value = 해당 이름의 참가자 수
  형태로 관리한 방향은 적절했다.

[보완할 점]
1. containsKey()는 이 문제에서 필수는 아니다.
   - completion에 있는 이름은 문제 조건상 반드시 participant에도 존재한다.
   - 따라서 바로 map.get(name) - 1로 감소시켜도 된다.

2. value < 1이면 바로 return 하는 구조는 흐름이 복잡해질 수 있다.
   - 모든 completion을 순회하며 value를 감소시킨 뒤,
   - 마지막에 value가 1인 key를 찾는 방식이 더 단순하고 명확하다.
*/