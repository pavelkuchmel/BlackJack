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
        /*for (Integer bj : deck){
            System.out.println(bj);
        }*/
        Dealer dealer = new Dealer();
        Player player = new Player();
        Player player1 = new Player();
        for (;;){
            System.out.println("Диллер раздает карты: ");
            int a = deck.get(rdm.nextInt(52));
            int b = deck.get(rdm.nextInt(52));
            System.out.print("Игрок 1 получет: "+a+" и "+b);


        }
        /*int a = deck.get(rdm.nextInt(52));
        int b = deck.get(rdm.nextInt(52));
        dealer.addDeck(a, b);
        player.addDeck(8, 52);
        player1.addDeck(17,51);
        dealer.showOneCard();
        System.out.println();
        player.showCard();
        System.out.println();
        player1.showCard();*/
    }
}