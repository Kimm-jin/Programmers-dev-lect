package java_.G_card;

public class card {

        public static final int KIND_MAX = 4;
        public static final int NUM_MAX = 13;

        public static final int SPADE = 4;
        public static final int DIAMOND = 3;
        public static final int HEART = 2;
        public static final int CLOVER = 1;

        private int kind;
        private int number;

        public card() {
            this(SPADE, 1);
        }

        public card(int kind, int number) {
            this.kind = kind;
            this.number = number;
        }

        @Override
        public String toString() {
            String[] kinds = { "", "CLOVER", "HEART", "DIAMOND", "SPADE" };
            String numbers = "123456789XJQK";

            return "kind : " + kinds[kind] + ", number : " + numbers.charAt(number);
        }


}
