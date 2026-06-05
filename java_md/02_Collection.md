# 📦 Java Collection Framework Master Guide

자바 코딩 테스트와 실무 개발의 뼈대가 되는 컬렉션 3대장(List, Set, Map)의 핵심 메모리 메커니즘, 필수 메서드, 실전 예시 코드를 집대성한 요약집입니다.

---

## 1. 📐 List (가변 배열 및 2차원 리스트)

### 📌 핵심 개념
* **본질**: 내부적으로 일반 배열을 품고, 공간이 부족하면 자동으로 크기를 약 1.5배 확장하여 데이터를 복사(이사)하는 **동적 배열**이다.
* **특징**: 데이터의 **순서(인덱스)**가 완벽히 보장되며, 동일한 데이터의 **중복 저장**을 전면 허용한다.
* **주의점**: 2차원 리스트 구현 시, 선언 직후에는 행이 0개인 완전히 텅 빈 상태이다. 따라서 필요한 행만큼 **힙(Heap) 영역에 수동으로 공간(주소)을 할당**해줘야 하며, 열은 리스트 특성상 가로로 알아서 늘어난다.

### 🛠️ 필수 메서드
* `list.add(Value);` : 리스트의 맨 뒤에 데이터를 추가한다. (C++의 push_back 역할)
* `list.get(Index);` : 지정한 방 번호(인덱스)에 들어있는 데이터를 조회하여 추출한다.
* `list.set(Index, NewValue);` : 지정한 방 번호의 기존 데이터를 새로운 데이터로 수정(치환)한다.
* `list.remove(Index 또는 Value);` : 인덱스 번호로 지우거나, 특정 텍스트를 자동으로 검색하여 직접 삭제한다. (삭제 시 뒤의 데이터들이 앞으로 자동 당겨짐)
* `list.size();` : 리스트에 담긴 실제 데이터의 총 개수를 반환한다.

### 💻 실전 예시 코드
```java
import java.util.ArrayList;
import java.util.List;

public class ListMaster {
    public static void main(String[] args) {
        // 1. 2차원 리스트 선언 (행과 열이 모두 0인 빈 껍데기 상자)
        List<List<Integer>> matrix = new ArrayList<>();

        // [황금 규칙] 필요한 행의 크기(3행)만큼 빈 리스트 주소를 수동으로 할당!
        for (int i = 0; i < 3; i++) {
            matrix.add(new ArrayList<>()); 
        }

        // 2. 값 추가 (get으로 행 상자를 먼저 꺼낸 후, 그 상자에 값을 add한다)
        matrix.get(0).add(10);
        matrix.get(0).add(20);
        matrix.get(1).add(30);

        // 3. 값 추출
        int value = matrix.get(0).get(1); // 0행 1열의 값 추출 (20)
        System.out.println("2차원 리스트 전체 구조: " + matrix);

        // 4. 값 수정 및 삭제
        matrix.get(0).set(1, 99); // 0행 1열의 값을 99로 수정
        matrix.get(0).remove(0);   // 0행 0열의 데이터를 삭제 (열 삭제)
        matrix.remove(1);          // 1행(줄) 자체를 우주에서 통째로 날려버림 (행 삭제)
    }
}
```

---

## 2. 🔮 Set (중복 제거 주머니)

### 📌 핵심 개념
* **본질**: 똑같은 데이터가 들어오는 것을 본능적으로 거부하는 **중복 제거 전용 보관함**이다.
* **특징**: 데이터가 들어간 순서를 기억하지 못하며, **인덱스(방 번호)라는 개념이 아예 존재하지 않는다.**
* **성능**: `HashSet`을 사용하면 내부적으로 **해시(Hash) 치트키를 써서 O(1)이라는 상상을 초월하는 속도로 값을 참조/검색**하므로 코딩 테스트 필수 무기이다. 수학적인 합집합, 교집합, 차집합 연산도 지원한다.

### 🛠️ 필수 메서드
* `set.add(Value);` : 주머니에 데이터를 추가한다. (성공 시 true, 중복이라 실패 시 false를 대입/반환함)
* `set.contains(Value);` : 주머니에 이 데이터가 들어있는지 해시 알고리즘으로 초고속 존재 유무 확인을 한다.
* `set.remove(Value);` : 인덱스가 없으므로 오직 **실제 값**을 던져서만 삭제한다. (성공 시 true, 실패 시 false 반환)
* `for (String item : set)` : 인덱스가 없기 때문에 주머니 전체를 뒤질 때는 **향상된 for문(for-each)**을 필수로 쓴다.

### 💻 실전 예시 코드
```java
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class SetMaster {
    public static void main(String[] args) {
        // 1. 선언 및 초기화
        Set<String> set = new HashSet<>();

        // 2. 값 추가 및 자판기 리턴값(boolean) 활용 조건문 처리
        System.out.println(set.add("사과")); // true (처음 넣음)
        System.out.println(set.add("사과")); // false (중복이라 알아서 무시됨)

        // 3. 존재 유무 초고속 확인 (O(1))
        if (set.contains("사과")) {
            System.out.println("주머니에 사과가 존재합니다.");
        }

        // 4. 향상된 for문으로 전체 순회 (순서는 뒤죽박죽 나옴)
        set.add("바나나");
        for (String item : set) {
            System.out.println("꺼낸 물건: " + item);
        }

        // 5. 집합 연산 치트키
        Set<String> targetSet = new HashSet<>(Arrays.asList("바나나", "포도"));
        set.retainAll(targetSet); // 교집합: targetSet과 겹치는 "바나나"만 남기고 다 지움
    }
}
```

---

## 3. 🗺️ Map (Key-Value 사물함)

### 📌 핵심 개념
* **본질**: 인덱스 대신 개발자가 직접 지정한 **Key(이름표)와 Value(실제 데이터)를 한 쌍으로 묶어서 저장**하는 구조이다.
* **규칙**: Key는 중복을 절대 허용하지 않으며(덮어써짐), Value는 얼마든지 중복이 가능하다.
* **제약**: Key와 Value의 자료형은 자유롭게 조합할 수 있으나 원시 자료형(int, char)은 에러가 나므로 **반드시 래퍼 타입(Integer 등) 및 객체 타입**만 써야 한다.
* **고급 활용**: Value 자리에 또 다른 컬렉션(`List`, `Set`)을 넣을 수 있다. 단, 이 경우에도 리스트 때와 똑같이 **힙(Heap) 영역에 해당 컬렉션의 실제 물리 주소(new)를 먼저 할당해 놔야** 비로소 조작을 시작할 수 있다. 
* **메모리 특징**: 단순 Value에 `Integer`를 넣으면 넣는 순간 자바가 힙 영역에 자동으로 방을 만들고 값을 채워주지만, 컬렉션을 넣을 때는 조작할 대상 상자(주소)를 프로그래머가 수동으로 먼저 힙에 만들어 꽂아줘야 한다.

### 🛠️ 필수 메서드
* `map.put(Key, Value);` : Map에 Key와 Value 세트를 추가하거나 수정한다. (add 아님)
* `map.get(Key);` : Key를 던져서 매칭된 Value를 꺼낸다. 없는 Key면 `null`을 반환한다.
* `map.getOrDefault(Key, 기본값);` : Key가 없으면 null 대신 내가 미리 정해둔 **'기본값'을 대신 반환**한다. (코테 빈도수 세기 1위)
* `map.containsKey(Key);` : 해당 Key가 Map에 존재하는지 확인하여 `true / false`를 반환한다.
* `map.keySet();` : Map에 등록된 Key들만 몽땅 모아서 Set 형태로 추출한다. `for-each`문으로 Map 전체를 순회할 때 쓴다.

### 💻 실전 예시 코드
```java
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class MapMaster {
    public static void main(String[] args) {
        // 1. 기본형 Map 실습
        Map<String, Integer> map = new HashMap<>();
        map.put("김철수", 90);
        map.put("김철수", 95); // Key 중복 입력 시 에러 없이 최신 Value로 덮어씀

        // 코테 최강 치트키 getOrDefault
        int unknownScore = map.getOrDefault("홍길동", 0); // 홍길동이 없으므로 기본값 0 반환

        // ==================================================
        // 🚀 고급 활용: Map의 Value에 컬렉션(List) 결합하기
        // ==================================================
        Map<String, List<String>> martMap = new HashMap<>();

        // [황금 규칙] 컬렉션들을 쓸 수 있도록 힙 영역에 물리 주소를 먼저 할당!
        martMap.put("패션", new ArrayList<>());
        martMap.put("가전", new ArrayList<>());

        // 데이터 추가 연쇄 명령 메커니즘
        // 1단계: martMap.get("패션")으로 힙에 준비된 List 상자(주소)를 손에 쥔다.
        // 2단계: .add("티셔츠")로 그 List 상자 안에 데이터를 추가한다.
        martMap.get("패션").add("티셔츠");
        martMap.get("패션").add("청바지");

        // 전체 순회 (keySet으로 Key들만 뽑아서 회전)
        for (String key : martMap.keySet()) {
            System.out.println("카테고리: " + key + ", 상품목록: " + martMap.get(key));
        }
    }
}
```
