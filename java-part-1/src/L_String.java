// * String 클래스의 특징
// * 불변성 (Immutable)
// String 객체가 생성되면 그 객체의 문자열 값은 변경할 수 없다.
// 문자열을 수정하려면 새로운 String 객체를 생성해야 한다.
// * 메모리 효율성
// 같은 값을 가진 String 리터럴은 같은 메모리에서 공유된다.

public class L_String {

    // 1. charAt(int index)
    // 문자열에서 특정 위치에 있는 문자(char)를 반환합니다.
    static void exam1(){
        String str = "hello";
        for (int i = 0; i < str.length() ; i++) {
            char c = str.charAt(i); // 문자열을 charecter형으로
            System.out.println(c);
        }
    }

    // 2. substring(int beginIndex, int endIndex)
    // 문자열의 특정 부분을 추출하여 반환한다.
    public static void exam2(){
        String str = "algorithm";
        String sub = str.substring(0,5); // beginIdex는 포함 endIndex는 포함하지 않음 -> 0 ~ 4
        System.out.println(sub);
    }

    // 3. split(String regex)
    // 주어진 정규 표현식을 기준으로 문자열을 분리하여 배열로 변환합니다.
    static void exam3(){
        String str = "one,two,three";
        String[] packet = str.split(",");
        for(String p : packet){
            System.out.println(p);
        }
    }

    // 4. toCharArray()
    // 문자열을 문자 배열로 반환합니다.
    public static void exam4() {
        String str = "hello";
        char[] chars = str.toCharArray();
        for ( char c : chars ) {
            System.out.println(c);
        }
    }

    // 5.
    public static void exam5(){
        String str1 = "hello1";
        String str2 = "hello2";
        System.out.println(str1.equals(str2));
    }

    // 6. contains(CharSequence s)
    // 문자열이 특정 문자열을 포함하고 있는지 확인합니다.
    // jwt 토큰인증 시에도 씀
    public static void exam6(){
        String str = "hello";
        System.out.println(str.contains("ell"));
    }

    // 7 replace(char oldChar, char newChar) 및 replace(charSequence target, char Sequence replacement)
    // 문자열 내의 특정 문자 또는 문자열을 다른 문자 또는 문자열로 대체합니다.
    public static void exam7(){
        String str = "hello";
        String newStr = str.replace('l', 'L');
        System.out.println(newStr);
    }

    // 8. indexOf(String str) 및 lastIndexOf(String str)
    // 특정 문자열이 처음 또는 마지막으로 나타나는 위치를 반환합니다.
    public static void exam8(){
        String str = "hello";
        int index = str.indexOf("l");
        int lastIndex = str.lastIndexOf("l");
        System.out.println(index); // he 'l'lo
        System.out.println(lastIndex); // hel'l'o
    }

    // 9. StringBuilder 및 StringBuffer
    // StringBuilder와 StringBuffer는 가변(muteable) 문자열을 다루기 위한 클래스입니ㅏㄷ.
    // StringBuilder는 성능이 우수하며, StringBuffer는 스레드 안전(thread-safe)한 버전입니다.
    // 문자열 조작이 필요없으면 String, 필요하면 StringBuilder를 사용하는것이 좋다.
    public static void  exam9(){
        StringBuilder sb = new StringBuilder();
        //str+= "문자열" 의경우 메모리구조가 조금 독특함. 그리고 낭비되는 메모리공간이 생김
        sb.append("hello"); // 실제 sb 메모리에 hello + world가 된것.
        sb.append(" world");
        String res = sb.toString();
        System.out.println(res);
    }

    // 10. reverse()
    // 문자열을 뒤집습니다. String 자체는 reverse() 메서드가 없지만, StringBuilder를 사용하여 문자를 뒤집을 수 있습니다
    public static void exam10() {
        StringBuilder sb = new StringBuilder("hello");
        String result = sb.reverse().toString();
        System.out.println(result);
    }

    // 11. compareTo(String anotherString)
    // 두 문자열을 사전적으로 비교하여, 현재 문자열이 더 작으면 음수 -1, 같으면 0, 더 크면 양수 1을 반환
    // 여기 뭐가 이상하다 다시 해볼것
    public static void exam11(){
        String str1="apple";
        String str2="banana";
        String str3="cat";
        String str4="apple";
        int resultIdx1=str1.compareTo(str2);
        int resultIdx3=str1.compareTo(str3);
        int resultIdx2=str1.compareTo(str4);

        System.out.println(resultIdx1);
        System.out.println(resultIdx2);
        System.out.println(resultIdx3);
    }

    // 12.toLowerCase(), toUpperCase()
    // 문자열을 소문자 또는 대문자로 변환합니다.
    public static void exam12(){
        String str ="hello";
        String lower = str.toLowerCase();
        String upper = str.toUpperCase();
        System.out.println(lower);
        System.out.println(upper);
    }

    public static void main(String[] args) {
        //exam1();
        //exam7();
        exam11();
        exam12();
    }
}
