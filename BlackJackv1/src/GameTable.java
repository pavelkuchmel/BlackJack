import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameTable {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rdm = new Random();
        ArrayList<Integer> deck = new ArrayList<Integer>(52);
        ArrayList<Integer> usedCard = new ArrayList<Integer>();
        /*int numCard = 2;
        for(int i = 0; i < 52; i++){         //заполнение массива картами
            if (i%4==0&&i!=0&&i<36)numCard++;
            if (i>=48)numCard=11;
            deck.add(i, numCard);
        }*/
        Dealer dealer = new Dealer();
        Player player = new Player();
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        System.out.print("Enter quantity players: ");
        int num = in.nextInt();
        switch (num){
            case 1:
                System.out.print("Enter player name: ");
                player.setName(in.next());
                break;
            case 2:
                System.out.print("Enter player 1 name: ");
                player.setName(in.next());
                System.out.print("Enter player 2 name: ");
                player1.setName(in.next());
                break;
            case 3:
                System.out.print("Enter player 1 name: ");
                player.setName(in.next());
                System.out.print("Enter player 2 name: ");
                player1.setName(in.next());
                System.out.print("Enter player 3 name: ");
                player2.setName(in.next());
                break;
            case 4:
                System.out.print("Enter player 1 name: ");
                player.setName(in.next());
                System.out.print("Enter player 2 name: ");
                player1.setName(in.next());
                System.out.print("Enter player 3 name: ");
                player2.setName(in.next());
                System.out.print("Enter player 4 name: ");
                player3.setName(in.next());
                break;
        }
        fillDeck(deck);
        shaffleDeck(deck);
        //showDeck(deck);
        dealer.takeCard(takeRdmCard(deck), takeRdmCard(deck));
        switch (num){
            case 4:
                player3.takeCard(takeRdmCard(deck), takeRdmCard(deck));
            case 3:
                player2.takeCard(takeRdmCard(deck), takeRdmCard(deck));
            case 2:
                player1.takeCard(takeRdmCard(deck), takeRdmCard(deck));
            case 1:
                player.takeCard(takeRdmCard(deck), takeRdmCard(deck));
                break;
        }
        showCard(dealer, player);
        countPoints(dealer, player, deck);
        /*System.out.print(player.getName()+" take more? Enter y/or other: ");
        char more = in.next().charAt(0);
        if (more=='y'){
            player.takeCard(takeRdmCard(deck));
            showCard(dealer, player);
            if (player.showCard()<21){
                System.out.print(player.getName()+" take more? Enter y/or other: ");
                more = in.next().charAt(0);
                if (more=='y'){
                    player.takeCard(takeRdmCard(deck));
                    showCard(dealer, player);
                }
                else {
                    showCard(dealer, player, 1);
                    if (showCard(dealer,player, 1)<=17){
                        dealer.takeCard(takeRdmCard(deck));
                    }
                    else {

                    }
                }
            }
        }
        else {
            showCard(dealer, player, 1);
        }*/

    }
    static void countPoints (Dealer dealerName, Player playerName, ArrayList<Integer> arrayName){
        if (playerName.intSumCard()==21&&dealerName.intAllCard()>=17){
            showCard(dealerName, playerName, 1);
            System.out.println("Game over!\n"+playerName.getName()+" win");
        }
        else if () {

        }
    }
    static int takeRdmCard(ArrayList<Integer> arrayName/*, ArrayList<Integer> arrayIndex*/){
        Random rdm = new Random();
        int cardIndex = rdm.nextInt(arrayName.size());
        int card = arrayName.get(cardIndex);
        arrayName.remove(cardIndex);
        //System.out.println("cardIndex = "+cardIndex);
        //System.out.println("arrayName.size() = "+arrayName.size());
        return card;
    }
    /*static void showCard(Dealer dealerName, Player playerName, ArrayList<Integer> arrayName){
        System.out.println("\n----------------------");
        System.out.print("Dealer: ");
        dealerName.showAllCard();
        System.out.print(playerName.getName()+": ");
        playerName.showCard();
        System.out.println("\n----------------------");
        if (dealerName.showAllCard()<=17){
            dealerName.takeCard(takeRdmCard(arrayName));
        }
        showCard(dealerName, playerName, arrayName);
    }*/
    static int showCard(Dealer dealerName, Player playerName, int dealerShowAllCard){
        System.out.println("\n----------------------");
        System.out.print(dealerName.getName()+": ");
        dealerName.showAllCard();
        System.out.print(playerName.getName()+": ");
        playerName.showCard();
        System.out.println("\n----------------------");
        return dealerName.intAllCard();
    }
    static void showCard(Dealer dealerName, Player playerName){
        System.out.println("----------------------");
        System.out.print("Dealer: ");
        dealerName.showFirstCard();
        System.out.print(playerName.getName()+": ");
        playerName.showCard();
        System.out.println("\n----------------------");
    }
    static void fillDeck(ArrayList<Integer> arrayName){
        int numCard = 2;
        for(int i = 0; i < 52; i++) {
            if (i % 4 == 0 && i != 0 && i < 36) numCard++;
            if (i >= 48) numCard = 11;
            arrayName.add(i, numCard);
        }
    }
    static ArrayList<Integer> showDeck(ArrayList<Integer> arrayName){
        for (int i = 1; i <= arrayName.size(); i++){
            System.out.print(arrayName.get(i-1)+" ");
            if (i%4==0) System.out.println();
        }
        return arrayName;
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
        arrayName.set(index1, arrayName.get(index2));
        arrayName.set(index2, temp);
    }
}