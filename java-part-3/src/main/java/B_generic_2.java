
// 정수와 실수만 받을 수 있게 제한을 둠 - T extends Number
public class B_generic_2<T extends Number> {

    // 덧셈
    public T add(T num1, T num2) {
        // instanceof는 num1이 Integer에 속하는지 boolean을 반환
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = num1.intValue() + num2.intValue();
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof Double && num2 instanceof Double) {
            double result = num1.intValue() + num2.intValue();
            return (T) Double.valueOf(result);
        } // 예외 - 이 기능은 지원하지 않는다
        throw new UnsupportedOperationException("지원되지 않는 타입입니다.");
    }

    // 뺼셈
    public T subtract(T num1, T num2){
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = num1.intValue() - num2.intValue();
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof Double && num2 instanceof Double) {
            double result = num1.intValue() - num2.intValue();
            return (T) Double.valueOf(result);
        } // 예외 - 이 기능은 지원하지 않는다
        throw new UnsupportedOperationException("지원되지 않는 타입입니다.");
    }


    // 곱셈
    public T multiply(T num1, T num2){
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = num1.intValue() * num2.intValue();
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof Double && num2 instanceof Double) {
            double result = num1.intValue() * num2.intValue();
            return (T) Double.valueOf(result);
        } // 예외 - 이 기능은 지원하지 않는다
        throw new UnsupportedOperationException("지원되지 않는 타입입니다.");
    }

    // 나눗셈
    public T divide(T num1, T num2){
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = num1.intValue() / num2.intValue();
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof Double && num2 instanceof Double) {
            double result = num1.intValue() / num2.intValue();
            return (T) Double.valueOf(result);
        } // 예외 - 이 기능은 지원하지 않는다
        throw new UnsupportedOperationException("지원되지 않는 타입입니다.");
    }

    public static void main(String[] args) {
        B_generic_2<Integer> intCalc = new B_generic_2<>();
        System.out.println("Integer add : " + intCalc.add(10, 20));

        B_generic_2<Integer> intCalculator = new B_generic_2<>();
        System.out.println("Integer Addition: " + intCalculator.add(10, 20));

        System.out.println("Integer Subtraction: " + intCalculator.subtract(20, 10));
        System.out.println("Integer Multiplication: " + intCalculator.multiply(10, 20));
        System.out.println("Integer Division: " + intCalculator.divide(20, 10));

        B_generic_2<Double> doubleCalculator = new B_generic_2<>();
        System.out.println("Double Addition: " + doubleCalculator.add(10.5, 20.3));
        System.out.println("Double Subtraction: " + doubleCalculator.subtract(20.5, 10.2));
        System.out.println("Double Multiplication: " + doubleCalculator.multiply(10.0, 20.0));
        System.out.println("Double Division: " + doubleCalculator.divide(20.0, 10.0));
    }
}
