# 텍스트 RPG 전투 만들기 (객체지향 · 생성자)

> 용사와 몬스터가 턴제로 싸우는 콘솔 게임입니다. 캐릭터를 만들 때마다 **생성자**가 호출되고, 객체끼리 메서드로 상호작용하면서 **객체지향(클래스·객체·캡슐화)** 과 **생성자(오버로딩·this·체이닝)** 를 자연스럽게 익힙니다. **아래 Step을 순서대로 따라가면, 마지막 Step에서 게임이 완성됩니다.**

---

## 1. 무엇을 만드나요?

용사가 몬스터들과 차례로 싸웁니다. 서로 번갈아 공격하고, HP가 0이 되면 쓰러집니다.

```
=== 전투 시작! 용사 vs 슬라임 ===
용사 (HP: 100)
슬라임 (HP: 30)

용사의 공격! 슬라임에게 25 피해
슬라임 (HP: 5)
슬라임의 공격! 용사에게 5 피해
용사 (HP: 95)
용사의 공격! 슬라임에게 25 피해
슬라임을 쓰러뜨렸다!

=== 다음 상대: 고블린 ===
...
```

---

## 2. 학습 목표 (이 게임으로 배우는 것)

|개념|어디서 배우나|
|---|---|
|클래스와 객체|캐릭터 설계도(클래스)로 용사·몬스터(객체)를 찍어낸다|
|생성자|캐릭터를 `new` 할 때 이름·HP·공격력을 초기화한다|
|`this` 키워드|생성자에서 필드와 매개변수를 구분한다|
|캡슐화|필드는 `private`, 행동은 메서드로 제공한다|
|생성자 오버로딩 + 체이닝|기본 몬스터와 커스텀 몬스터를 다른 생성자로 만든다|
|객체 여러 개 관리|몬스터 여러 마리를 배열로 다룬다|

---

## 3. 핵심 개념

### (1) 클래스 = 설계도, 객체 = 찍어낸 것

`Character`라는 설계도(클래스)를 한 번 만들면, 그 설계도로 용사도 슬라임도 드래곤도 만들 수 있습니다. 각각이 **객체**입니다.

### (2) 생성자 = 객체를 만들 때 초기값 채우기

```java
public Character(String name, int hp, int power) {
    this.name = name;     // this.필드 = 매개변수
    this.hp = hp;
    this.power = power;
}
// 사용
Character hero = new Character("용사", 100, 25);
```

`new Character(...)`를 부르면 **생성자가 자동 호출**되어 그 객체의 이름·HP·공격력이 정해집니다.

### (3) `this` — 누구의 필드인지 가리키기

매개변수와 필드 이름이 같을 때, `this.name`은 "이 객체의 필드", 그냥 `name`은 "매개변수"를 뜻합니다.

### (4) 캡슐화 — 필드는 숨기고, 행동은 메서드로

HP를 아무나 직접 바꾸지 못하게 `private`으로 숨기고, `takeDamage()` 같은 메서드를 통해서만 바꾸도록 합니다.

---

## 4. 파일 구조

|파일|역할|
|---|---|
|`Character.java`|캐릭터 설계도. 필드(이름/HP/공격력) + 생성자 + 행동 메서드|
|`Main.java`|용사·몬스터 객체를 만들고 전투를 진행|

> Step 7(도전)에서 `Character`를 부모로 두고 `Hero`/`Monster`로 나누는 상속까지 확장할 수 있습니다.

---

## 5. Step by Step

각 Step에 **목표 / 할 일 / 힌트 / 확인 방법**이 있습니다.

---

### Step 1. 캐릭터 설계도와 생성자 (`Character.java`)

**목표**: 이름·HP·공격력을 가진 캐릭터 클래스를 만들고, 생성자로 객체를 하나 만들어 본다.

**할 일**

1. 필드 `name`, `hp`, `power` (우선 `private`).
2. 세 값을 받는 생성자 작성 (`this` 사용).
3. 상태를 출력하는 `showStatus()` 메서드.
4. `Main`에서 객체 하나 만들어 상태 출력.

**힌트**

```java
public class Character {
    private String name;
    private int hp;
    private int power;

    public Character(String name, int hp, int power) {
        this.name = name;
        this.hp = hp;
        this.power = power;
    }

    public void showStatus() {
        System.out.println(name + " (HP: " + hp + ")");
    }
}
// Main
Character hero = new Character("용사", 100, 25);
hero.showStatus();   // 용사 (HP: 100)
```

**확인**: "용사 (HP: 100)"이 출력되면 성공. 생성자가 잘 호출된 것!

---

### Step 2. 캐릭터의 행동 메서드 (`Character.java`)

**목표**: 공격하고, 피해를 받고, 살아있는지 판단하는 메서드를 추가한다. (캡슐화)

**할 일**

1. `isAlive()` — HP가 0보다 큰지 반환.
2. `takeDamage(int dmg)` — HP를 깎되 0 밑으로는 안 내려가게.
3. `attack(Character target)` — 상대에게 자기 공격력만큼 피해를 준다.

**힌트**

```java
public boolean isAlive() {
    return hp > 0;
}
public void takeDamage(int dmg) {
    hp -= dmg;
    if (hp < 0) hp = 0;
}
public void attack(Character target) {
    System.out.println(name + "의 공격! " + target.name + "에게 " + power + " 피해");
    target.takeDamage(power);
}
```

> `target.name`처럼 **같은 클래스 안에서는 다른 객체의 private 필드에도 접근**할 수 있습니다.

**확인**: 컴파일 에러가 없으면 OK. (다음 Step에서 실제로 써봅니다)

---

### Step 3. 한 대씩 주고받기 (`Main.java`)

**목표**: 용사와 몬스터 두 객체를 만들어 서로 한 번씩 공격해 본다.

**할 일**

1. 용사와 몬스터 객체를 각각 생성.
2. 용사가 몬스터를 공격, 몬스터 상태 출력.
3. 몬스터가 용사를 공격, 용사 상태 출력.

**힌트**

```java
Character hero = new Character("용사", 100, 25);
Character monster = new Character("슬라임", 30, 5);

hero.attack(monster);
monster.showStatus();   // 슬라임 (HP: 5)
monster.attack(hero);
hero.showStatus();      // 용사 (HP: 95)
```

**확인**: 공격 메시지와 줄어든 HP가 보이면 성공.

---

### Step 4. 턴제 전투 반복 (`Main.java`)

**목표**: 둘 중 하나가 쓰러질 때까지 번갈아 공격한다.

**할 일**

1. `while`로 둘 다 살아있는 동안 반복.
2. 용사 공격 → 몬스터가 죽었으면 종료.
3. 살아있으면 몬스터가 반격.
4. 끝나면 누가 이겼는지 출력.

**힌트**

```java
while (hero.isAlive() && monster.isAlive()) {
    hero.attack(monster);
    monster.showStatus();
    if (!monster.isAlive()) {
        System.out.println(">> 몬스터를 쓰러뜨렸다!");
        break;
    }
    monster.attack(hero);
    hero.showStatus();
}
```

**확인**: 전투가 자동으로 끝까지 진행되고 승패가 나면 성공.

---

### Step 5. 생성자 오버로딩 + 체이닝 (`Character.java`)

**목표**: 매번 HP·공격력을 다 적지 않아도, 이름만으로 "기본 몬스터"를 만들 수 있게 한다.

**할 일**

1. 이름만 받는 생성자를 추가한다.
2. 그 안에서 `this(...)`로 **원래 생성자를 호출**해 기본값(예: HP 30, 공격력 5)을 채운다.

**힌트**

```java
// 이름만 받는 생성자 — 기본 스탯을 가진 몬스터
public Character(String name) {
    this(name, 30, 5);   // 같은 클래스의 다른 생성자 호출 (생성자 체이닝)
}
// 사용 비교
Character slime  = new Character("슬라임");          // 기본 생성자
Character goblin = new Character("고블린", 50, 8);   // 커스텀 생성자
```

> **핵심**: 같은 이름의 생성자를 매개변수만 다르게 여러 개 두는 것이 **오버로딩**, 한 생성자가 `this(...)`로 다른 생성자를 부르는 것이 **생성자 체이닝**입니다.

**확인**: `new Character("슬라임")`만으로 HP 30짜리 몬스터가 만들어지면 성공.

---

### Step 6. 몬스터 여러 마리와 연속 전투 (`Main.java`)

**목표**: 여러 몬스터를 배열로 만들어, 용사가 차례로 상대하게 한다.

**할 일**

1. 몬스터들을 배열로 생성 (기본·커스텀 생성자 섞어서).
2. 용사가 살아있는 동안, 배열의 몬스터와 차례로 전투.
3. 모든 몬스터를 이기면 클리어, 도중에 죽으면 게임 오버.

**힌트**

```java
Character hero = new Character("용사", 100, 25);
Character[] monsters = {
    new Character("슬라임"),              // 기본 생성자
    new Character("고블린", 50, 8),       // 커스텀
    new Character("드래곤", 120, 20)      // 보스
};

for (Character m : monsters) {
    System.out.println("\n=== 다음 상대: ===");
    m.showStatus();
    // (Step 4의 전투 반복을 여기서 hero vs m 으로 실행)
    if (!hero.isAlive()) {
        System.out.println("게임 오버...");
        break;
    }
}
```

**확인**: 용사가 슬라임 → 고블린 → 드래곤을 차례로 상대하면 성공.

---

### Step 7. 마무리 다듬기 (완성!)

**점검 항목**

- [ ] 필드가 `private`이고, 외부에선 메서드로만 다루는가? (캡슐화)
- [ ] 생성자 두 종류(이름만 / 전체)가 모두 동작하는가?
- [ ] HP가 음수로 내려가지 않는가?
- [ ] 모든 몬스터를 이기면 "클리어", 지면 "게임 오버"가 뜨는가?

여기까지 통과하면 **RPG 전투 게임 완성입니다!** 🎉

---

## 6. 객체지향 · 생성자 학습 체크

- [ ] 클래스 1개로 여러 객체(용사·몬스터들)를 생성
- [ ] 생성자로 객체 초기화, `new` 시 자동 호출 이해
- [ ] `this`로 필드와 매개변수 구분
- [ ] `private` 필드 + 메서드로 캡슐화
- [ ] 생성자 **오버로딩** (이름만 / 전체)
- [ ] `this(...)`로 생성자 **체이닝**
- [ ] 객체를 배열로 모아 관리

---

## 7. 최종 완성 체크리스트

- [ ] `Character.java` — 필드·생성자(2개)·메서드(attack/takeDamage/isAlive/showStatus)
- [ ] `Main.java` — 용사 + 몬스터 배열 생성, 연속 전투
- [ ] 생성자 오버로딩·체이닝 사용
- [ ] 전투가 끝까지 진행되고 클리어/게임오버 처리

---

## 8. (선택) 도전 과제 — 상속·다형성으로 확장

1. **상속**: `Character`를 부모로 두고 `Hero`, `Monster`로 나누기. 자식 생성자에서 `super(...)`로 부모 생성자 호출.
2. **다형성**: `Character` 타입 배열에 `Hero`와 `Monster`를 함께 담아 다루기.
3. **메서드 오버라이딩**: 몬스터 종류마다 `attack()`을 다르게(드래곤은 화염 공격 등) 재정의.
4. **스킬/방어력**: 방어력 필드를 추가해 받는 피해를 줄이기.
5. **플레이어 선택**: 매 턴 "공격 / 방어 / 회복"을 입력으로 고르게 하기.