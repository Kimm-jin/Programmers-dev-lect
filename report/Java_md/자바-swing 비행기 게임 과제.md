# 비행기 게임 1945 만들기 (Swing + 멀티스레드)

> Java Swing으로 종스크롤 슈팅 게임을 만들면서 **멀티스레드**를 배웁니다. 이 과제의 핵심 원칙은 단 하나입니다:
> 
> ## **"움직이는 것 하나 = 스레드 하나"**
> 
> 총알 한 발도 스레드, 적기 한 대도 스레드입니다. 그래서 화면에는 수십 개의 스레드가 동시에 돌아가고, 학생들은 동시성과 동기화 문제를 **눈으로** 마주치게 됩니다. **아래 Step을 순서대로 따라가면, 마지막 Step에서 게임이 완성됩니다.**

---

## 1. 무엇을 만드나요?

- 내 비행기를 키보드로 좌우(또는 상하좌우) 이동
- 스페이스바로 총알 발사 (위로 날아감)
- 위에서 적기가 계속 내려옴
- 총알이 적기에 맞으면 둘 다 사라지고 점수 +1
- 적기가 내 비행기에 닿으면 게임 오버

```
 ┌─────────────────┐
 │      ▼   ▼       │  ← 적기(내려옴), 각자 스레드
 │   ▼             │
 │        ▲        │  ← 총알(올라감), 각자 스레드
 │      ▲          │
 │                 │
 │        ■        │  ← 내 비행기 (키 입력으로 이동)
 └─────────────────┘
        SCORE: 12
```

---

## 2. 요구사항 정리 (MVP 기능 명세)

|기능|설명|관련 스레드 개념|
|---|---|---|
|비행기 이동|방향키로 내 비행기를 움직인다|(키 이벤트 — EDT에서 처리)|
|총알 발사|스페이스바로 총알 생성, 위로 이동|총알마다 `Thread` 1개|
|적기 등장|적기가 일정 간격으로 생겨 아래로 이동|적기마다 `Thread` 1개 + 생성기 `Thread`|
|충돌 처리|총알·적기 겹치면 둘 다 제거, 점수 +1|공유 리스트 접근 → **동기화**|
|점수/게임오버|점수 표시, 충돌 시 종료|스레드 **안전한 종료**|

> 위 표 오른쪽 열이 이 과제의 진짜 학습 목표입니다. 게임은 그 개념을 담는 그릇이에요.

---

## 3. 핵심 개념

### (1) 객체마다 스레드 — `run()` 반복 패턴

움직이는 객체는 `Thread`를 상속하고, `run()` 안에서 **"조금 움직이고 → 다시 그리고 → 잠깐 자는"** 것을 반복합니다.

```java
public void run() {
    while (alive) {        // 살아있는 동안
        y -= speed;        // 1) 위치를 조금 바꾸고
        panel.repaint();   // 2) 화면을 다시 그리라고 요청하고
        Thread.sleep(30);  // 3) 잠깐 멈춘다 (이게 속도/프레임 역할)
    }
}
```

`Thread.sleep(밀리초)` 값이 작을수록 빠르게 움직입니다.

### (2) Swing 그리기 — `paintComponent`

`JPanel`을 상속해 `paintComponent(Graphics g)`를 오버라이드하고, 그 안에서 비행기·총알·적기를 그립니다. 직접 호출하지 않고 **`repaint()`** 를 부르면 Swing이 알아서 다시 그려줍니다.

### (3) ⭐ 동기화 — 이 과제의 가장 중요한 부분

총알 목록(`List<Bullet>`)과 적기 목록(`List<Enemy>`)은 **여러 스레드가 동시에** 건드립니다.

- 총알/적기 스레드: 자기 자신을 리스트에서 추가·삭제
- 그리기(EDT): 리스트를 처음부터 끝까지 돌면서 그림

이 둘이 동시에 일어나면 `ConcurrentModificationException`(목록을 읽는 중에 누가 바꿈)이 터집니다. 그래서 리스트를 만지는 곳마다 **`synchronized`** 로 묶어야 합니다.

```java
synchronized (bullets) {
    for (Bullet b : bullets) { /* 그리기 */ }
}
```

> **알아둘 점**: `repaint()` 자체는 여러 스레드에서 불러도 안전합니다(그리기 요청만 예약). 위험한 건 _공유 리스트를 동시에 수정/순회_하는 부분이에요.

### (4) 스레드 멈추기

`thread.stop()`은 위험해서 쓰지 않습니다. 대신 `boolean alive` 같은 **깃발 변수**를 `false`로 바꿔서 `run()`의 `while`이 스스로 끝나게 합니다.

---

## 4. 파일 구조

|파일|역할|
|---|---|
|`Main.java`|`main` 메서드. 게임 창을 띄운다|
|`GamePanel.java`|`JPanel` 상속. 화면 그리기 + 객체 목록 보관 + 키 입력 + 충돌/점수|
|`Player.java`|내 비행기 (위치 정보. 키 입력으로 움직임)|
|`Bullet.java`|`Thread` 상속. 위로 이동하는 총알|
|`Enemy.java`|`Thread` 상속. 아래로 이동하는 적기|
|`EnemySpawner.java`|`Thread` 상속. 일정 간격으로 적기를 생성|

> 인터페이스로 묶고 싶다면 `MovableObject` 같은 공통 인터페이스를 만들어 `Bullet`/`Enemy`가 구현하게 해도 좋습니다. (도전 과제)

---

## 5. Step by Step

각 Step에 **목표 / 할 일 / 힌트 / 확인 방법**이 있습니다. 한 Step씩 실행을 확인하며 진행하세요.

---

### Step 1. 게임 창 띄우기 (`Main.java`, `GamePanel.java`)

**목표**: 일정 크기의 검은(또는 배경) 창이 뜨게 만든다.

**할 일**

- `GamePanel`은 `JPanel`을 상속, 선호 크기를 정한다(예: 400 x 600).
- `paintComponent`에서 배경을 칠한다.
- `Main`에서 `JFrame`을 만들고 `GamePanel`을 붙여 화면에 띄운다.

**힌트**

```java
// GamePanel.java
public class GamePanel extends JPanel {
    public GamePanel() {
        setPreferredSize(new Dimension(400, 600));
        setBackground(Color.BLACK);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 이후 Step에서 여기 그림을 추가
    }
}
// Main.java
JFrame frame = new JFrame("1945");
frame.add(new GamePanel());
frame.pack();
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);
```

**확인**: 검은 창이 뜨면 성공.

---

### Step 2. 내 비행기 그리기 (`Player.java`)

**목표**: 화면 아래쪽에 내 비행기를 그린다.

**할 일**

- `Player`에 `x, y, width, height` 필드.
- 초기 위치를 화면 아래 가운데로.
- `GamePanel.paintComponent`에서 플레이어를 사각형(또는 이미지)으로 그린다.

**힌트**

```java
// paintComponent 안
g.setColor(Color.GREEN);
g.fillRect(player.x, player.y, player.width, player.height);
```

**확인**: 창 아래쪽에 비행기(사각형)가 보이면 성공.

---

### Step 3. 키보드로 비행기 이동 (`GamePanel`)

**목표**: 방향키로 비행기를 움직인다.

**할 일**

- `GamePanel`에 `KeyListener`를 단다. (`setFocusable(true)` 필수!)
- 좌/우 키로 `player.x`를 바꾸고 `repaint()`.
- 화면 밖으로 나가지 않게 범위를 제한한다.

**힌트**

```java
setFocusable(true);
addKeyListener(new KeyAdapter() {
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)  player.x -= 15;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.x += 15;
        repaint();
    }
});
```

**확인**: 방향키로 비행기가 좌우로 움직이면 성공.

> 키를 누르고 있을 때 부드럽게 움직이게 하려면 플레이어도 스레드로 만들 수 있습니다(도전 과제). MVP는 키 입력마다 한 칸씩 움직여도 충분합니다.

---

### Step 4. 총알 발사 — 첫 스레드 등장 (`Bullet.java`)

**목표**: 스페이스바를 누르면 총알이 생겨 위로 날아간다. **총알 하나하나가 스레드.**

**할 일**

1. `Bullet extends Thread`, 필드 `x, y, boolean alive`.
2. `run()`에 "위로 이동 → `repaint()` → `sleep`" 반복. 화면 위로 나가면 `alive=false`.
3. `GamePanel`에 `List<Bullet> bullets`를 두고, 추가/제거 메서드를 **`synchronized`** 로.
4. 스페이스바를 누르면 총알을 만들어 리스트에 넣고 `start()`.
5. `paintComponent`에서 `bullets`를 (동기화하며) 모두 그린다.

**힌트**

```java
// Bullet.java
public class Bullet extends Thread {
    int x, y;
    boolean alive = true;
    GamePanel panel;
    public Bullet(int x, int y, GamePanel panel) { this.x=x; this.y=y; this.panel=panel; }
    public void run() {
        while (alive) {
            y -= 10;
            if (y < 0) alive = false;
            panel.repaint();
            try { Thread.sleep(30); } catch (InterruptedException e) {}
        }
        panel.removeBullet(this); // 끝나면 목록에서 자기 제거
    }
}
// GamePanel.java
private List<Bullet> bullets = new ArrayList<>();
public void addBullet(Bullet b)    { synchronized (bullets) { bullets.add(b); } b.start(); }
public void removeBullet(Bullet b) { synchronized (bullets) { bullets.remove(b); } }
// paintComponent 안
synchronized (bullets) {
    for (Bullet b : bullets) g.fillRect(b.x, b.y, 5, 12);
}
// 스페이스바 처리 (keyPressed)
if (e.getKeyCode() == KeyEvent.VK_SPACE) addBullet(new Bullet(player.x, player.y, this));
```

**확인**: 스페이스바를 누를 때마다 총알이 위로 날아가면 성공. 연사도 잘 되는지 확인!

---

### Step 5. 적기 등장 (`Enemy.java`, `EnemySpawner.java`)

**목표**: 적기가 위에서 일정 간격으로 생겨 아래로 내려온다. **적기도, 생성기도 스레드.**

**할 일**

1. `Enemy extends Thread`: `run()`에서 아래로 이동(`y += speed`), 바닥에 닿으면 `alive=false`.
2. `EnemySpawner extends Thread`: 게임 중인 동안 `sleep` 후 적기를 만들어 추가하는 것을 반복.
3. `GamePanel`에 `List<Enemy> enemies` + 동기화된 추가/제거 메서드.
4. `paintComponent`에서 적기들을 그린다.
5. 게임 시작 시 `EnemySpawner`를 `start()`.

**힌트**

```java
// EnemySpawner.java
public void run() {
    while (panel.isPlaying()) {
        int x = (int)(Math.random() * (panel.getWidth() - 20)); // 랜덤 위치
        panel.addEnemy(new Enemy(x, 0, panel));
        try { Thread.sleep(1000); } catch (InterruptedException e) {} // 1초마다
    }
}
```

적기 그리기 색은 빨강 등으로 구분하면 보기 좋습니다.

**확인**: 적기가 위에서 계속 내려오면 성공. 동시에 여러 스레드가 도는 걸 체감해 보세요.

---

### Step 6. ⭐ 충돌 처리 — 동기화의 진가 (`GamePanel`)

**목표**: 총알이 적기에 맞으면 둘 다 사라진다. 여기서 **경쟁 상태**를 직접 겪고 해결한다.

**할 일**

1. 충돌 판정 = 두 사각형이 겹치는지 검사 (`Rectangle.intersects` 활용).
2. 매 순간 모든 총알 × 모든 적기를 비교해 겹치면 둘 다 `alive=false` 처리하고 목록에서 제거.
3. 이 검사를 **어디서** 할지 정한다 — 예: 적기 `run()`의 매 틱마다 총알들과 비교. (또는 별도 검사 루프)
4. 목록을 순회·수정하므로 **반드시 `synchronized`** 로 감싼다. 안 그러면 `ConcurrentModificationException`이 터진다 — 이게 학습 포인트!

**힌트**

```java
// 사각형 겹침 판정
Rectangle r1 = new Rectangle(bullet.x, bullet.y, 5, 12);
Rectangle r2 = new Rectangle(enemy.x, enemy.y, 20, 20);
if (r1.intersects(r2)) { /* 명중! */ }

// 목록 동시 순회는 위험하므로 동기화 + 복사본 사용 권장
List<Bullet> snapshot;
synchronized (bullets) { snapshot = new ArrayList<>(bullets); }
for (Bullet b : snapshot) { /* 비교 */ }
```

**확인**: 총알이 적기에 닿으면 둘 다 사라지면 성공. 동기화를 일부러 빼보고 예외가 나는 것도 한 번 경험해 보면 학습 효과가 큽니다.

---

### Step 7. 점수 & 게임 오버 (`GamePanel`)

**목표**: 격추 시 점수가 오르고, 적기가 내 비행기에 닿으면 게임이 끝난다.

**할 일**

1. `int score` 필드. 충돌(격추) 시 `score++`.
2. `paintComponent`에서 점수를 글자로 그린다(`g.drawString`).
3. 적기 ↔ 플레이어 충돌 시 `playing=false`로 바꿔 모든 스레드의 `while`이 멈추게 한다.
4. "GAME OVER"와 최종 점수를 화면에 표시.

**힌트**

```java
// 점수 표시
g.setColor(Color.WHITE);
g.drawString("SCORE: " + score, 10, 20);

// 게임 오버 깃발 — 스레드들이 이 값을 보고 스스로 종료
private volatile boolean playing = true;
public boolean isPlaying() { return playing; }
```

> `playing`에 **`volatile`** 을 붙이면 여러 스레드가 이 깃발의 최신값을 정확히 보게 됩니다. (가시성 — 멀티스레드의 또 다른 핵심 개념)

**확인**: 격추하면 점수가 오르고, 적기에 부딪히면 게임이 멈추면 성공.

---

### Step 8. 마무리 다듬기 (완성!)

**점검 항목**

- [ ] 게임 오버 시 모든 스레드(총알·적기·생성기)가 깔끔히 멈추는가?
- [ ] 목록을 만지는 모든 곳에 `synchronized`가 있는가? (예외가 안 나는가?)
- [ ] 화면 밖으로 나간 총알·적기는 목록에서 제거되는가? (안 그러면 스레드가 계속 쌓임)
- [ ] 연사·다수의 적기 상황에서도 끊기거나 멈추지 않는가?
- [ ] 깜빡임이 심하면 `super.paintComponent(g)` 호출과 더블버퍼링을 확인했는가?

여기까지 통과하면 **멀티스레드 1945 게임 완성입니다!** 🎉

---

## 6. 멀티스레드 학습 체크 (이 과제에서 반드시 경험할 것)

- [ ] `Thread` 상속 + `run()` 오버라이드 + `start()` 호출
- [ ] `Thread.sleep()`으로 움직임 속도/프레임 제어
- [ ] **여러 스레드가 동시에** 도는 것을 화면으로 확인 (총알·적기 수십 개)
- [ ] 공유 리스트 접근에서 `synchronized`로 경쟁 상태 해결
- [ ] `ConcurrentModificationException`을 만나고 고쳐보기
- [ ] `boolean`/`volatile` 깃발로 스레드를 **안전하게 종료**하기
- [ ] (보너스) Swing EDT와 `repaint()`의 관계 이해

---

## 7. 최종 완성 체크리스트

- [ ] `Main.java` — 창 띄우기
- [ ] `GamePanel.java` — 그리기 + 목록(동기화) + 키 입력 + 충돌/점수
- [ ] `Player.java` — 내 비행기, 키로 이동
- [ ] `Bullet.java` (`Thread`) — 위로 이동
- [ ] `Enemy.java` (`Thread`) — 아래로 이동
- [ ] `EnemySpawner.java` (`Thread`) — 적기 주기 생성
- [ ] 발사 → 적기 등장 → 충돌·점수 → 게임 오버까지 동작

---

## 8. (선택) 도전 과제

1. **생명(목숨)**: 충돌해도 바로 끝나지 않고 생명 3개를 깎기
2. **적의 총알**: 적기도 아래로 총알을 쏘게 만들기 (스레드 더 늘리기)
3. **이미지 적용**: 사각형 대신 비행기 `.png` 이미지로 그리기 (`ImageIcon`)
4. **공통 인터페이스**: `MovableObject` 인터페이스로 `Bullet`/`Enemy` 묶기
5. **스레드 풀**: 객체마다 스레드 대신 `ExecutorService`로 관리해 보기 (효율 비교)
6. **보스 적기 / 스테이지 / 아이템** 추가

---

### 강사용 메모

- "객체마다 스레드"는 학습 효과가 크지만, 객체가 수백 개로 늘면 스레드도 그만큼 생겨 무거워집니다. Step 8과 도전 과제 5에서 "그래서 실무에선 게임 루프 1개나 스레드 풀을 쓴다"로 자연스럽게 연결해 주면 좋은 마무리가 됩니다.
- Step 6에서 동기화를 **일부러 빼고** 실행해 예외를 직접 보여준 뒤 고치게 하면, `synchronized`의 필요성이 가장 강하게 와닿습니다.