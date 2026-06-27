```java
import javax.swing.*;  
  
public class test {  
    static void main(String[] args) {  
        // 스윙 프로그램은 이벤트 스레드에서 안전하게 실행해야 합니다.  
        SwingUtilities.invokeLater(new Runnable() {  
            @Override  
            public void run() {  
                // 1. 프레임(윈도우 창) 생성  
                JFrame frame = new JFrame("나의 첫 스윙 프로젝트");  
  
                // 2. 창의 크기 설정 (가로 400px, 세로 300px)  
                frame.setSize(400, 300);  
  
                // 3. 창을 화면 중앙에 배치  
                frame.setLocationRelativeTo(null);  
  
                // 4. 창의 닫기 버튼(X)을 눌렀을 때 프로그램 종료 설정  
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
                // 5. 화면에 창을 보이게 설정  
                frame.setVisible(true);  
            }  
        });  
    }  
}
```

