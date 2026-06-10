package java_.G_card;



public class cardImpl implements cardService{
    final int CARD_NUM = 52;
    card[] cards = new card[CARD_NUM];

    public cardImpl() {
        int i = 0;

        for (int k = card.KIND_MAX; k > 0; k-- ) {
            for (int n = 0; n < card.NUM_MAX; n++ ) {
                cards[i++] = new card(k, n + 1);
            }
        }
    }
    @Override
    public card pick(){
        int index = (int)(Math.random() * CARD_NUM); // 덱에서 카드 하나를 선택한다.
        return pick(index);
    }
    @Override
    public card pick(int index){
        return cards[index];
    }
    @Override
    public void shuffle(){
        for ( int i = 0; i < cards.length; i++ ) {
            int index = (int)(Math.random() * CARD_NUM);

            card temp = cards[i];
            cards[i] = cards[index];
            cards[index] = temp;
        }
    }
}
