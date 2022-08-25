import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class GameTable {
    public static void main(String[] args) {
        Random rdm = new Random();
        ArrayList<Integer> deck = new ArrayList<Integer>(52);
        int numCard = 2;
        for(int i = 0; i < 52; i++){
            if (i%4==0&&i!=0&&i<36)numCard++;
            if (i>=48)numCard=11;
            deck.add(i, numCard);
        }
        Dealer dealer = new Dealer();
        Player player = new Player();
        fillDeck(deck);
        showDeck(deck);
        shaffleDeck(deck);
        showDeck(deck);

    }
    static void fillDeck(ArrayList<Integer> arrayName){
        int numCard = 2;
        for(int i = 0; i < 52; i++) {
            if (i % 4 == 0 && i != 0 && i < 36) numCard++;
            if (i >= 48) numCard = 11;
            arrayName.add(i, numCard);
        }
    }
    static void showDeck(ArrayList<Integer> arrayName){
        for (int i = 1; i <= arrayName.size(); i++){
            System.out.print(arrayName.get(i-1)+" ");
            if (i%4==0) System.out.println();
        }
    }
    static void shaffleDeck(ArrayList<Integer> arrayName){
        Random rdm = new Random();
        for (int j = 0; j < 3; j++){
            for (int i = 0; i < arrayName.size(); i++) {
                int rdmIndex = rdm.nextInt(arrayName.size());
                swapPos(arrayName, i, rdmIndex);
            }
        }
    }
    static void swapPos(ArrayList<Integer> arrayName, int index1, int index2){
        int temp = arrayName.get(index1);
        arrayName.set(index1, index2);
        arrayName.set(index2, temp);
    }
}