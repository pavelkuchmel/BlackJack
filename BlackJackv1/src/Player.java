import java.util.ArrayList;

public class Player {
        ArrayList<Integer> deck = new ArrayList<Integer>();
        public void addDeck(int a, int b){
                deck.add(a);
                deck.add(b);
        }
        public void showCard(){
                System.out.println(deck.get(0));
                System.out.println(deck.get(1));
        }

}
