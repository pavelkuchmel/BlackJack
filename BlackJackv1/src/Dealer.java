import java.util.ArrayList;

public class Dealer {
    static ArrayList<Integer> deck = new ArrayList<Integer>();
    public void addDeck(int a, int b){
        deck.add(a);
        deck.add(b);
    }
    public void showOneCard(){
        System.out.println(deck.get(0));
    }
    public void showTwoCart(){
        System.out.println(deck.get(0));
        System.out.println(deck.get(1));
    }
}
