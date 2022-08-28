import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameTable {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rdm = new Random();
        ArrayList<Integer> deck = new ArrayList<Integer>(52);
        ArrayList<Integer> usedCard = new ArrayList<Integer>();
        ArrayList<Double> bets = new ArrayList<Double>(4);
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
        int numPlayers = in.nextInt();
        switch (numPlayers){
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
        for (int i = 0;;i++){
            if (i==0) {
                fillDeck(deck);
                shaffleDeck(deck);
            }
            System.out.println("Players place a bet.");
            System.out.print(player.getName()+": ");
            bets.add(in.nextDouble());
            dealer.takeCard(10, 6);
            player.takeCard(10, 11);
            showCard(dealer, player);
            countPoints(dealer, player, deck, bets);
            bank(bets, player);
        }
        //fillDeck(deck);
        //System.out.println("Players place a bet.");
        //System.out.print(player.getName()+": ");
        //bets.add(in.nextDouble());
        //shaffleDeck(deck);
        //showDeck(deck);
        //dealer.takeCard(takeRdmCard(deck), takeRdmCard(deck));
        /*switch (numPlayers){
            case 4:
                player3.takeCard(takeRdmCard(deck), takeRdmCard(deck));
            case 3:
                player2.takeCard(takeRdmCard(deck), takeRdmCard(deck));
            case 2:
                player1.takeCard(takeRdmCard(deck), takeRdmCard(deck));
            case 1:
                player.takeCard(takeRdmCard(deck), takeRdmCard(deck));
                break;
        }*/
        //player.takeCard(10, 11);
        //dealer.takeCard(10, 8);
        //showCard(dealer, player);
        //countPoints(dealer, player, deck, bets);
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
        /*do {
            System.out.println("Dealer take card.");
            timer();
            dealerName.takeCard(takeRdmCard(arrayName));
            showCard(dealerName, playerName, 1);
            timer();
        }while (dealerName.intAllCard()<17);*/
    }
    static void countPoints (Dealer dealerName, Player playerName, ArrayList<Integer> arrayName, ArrayList<Double> arrayBet){
        if (playerName.intSumCard()==21&&dealerName.deck.get(0)==10||dealerName.deck.get(0)==11){
            System.out.println(playerName.getName()+" has a Black Jack!");
            System.out.println("Dealer open the second card");
            showCard(dealerName, playerName, 1);
            timer();
            if (dealerName.intSumCard()<21) {
                System.out.println("Dealer has less. " + playerName.getName() + " won!");
                arrayBet.set(0, arrayBet.get(0)*1.5);
                timer();
            }
            else if (dealerName.intSumCard()==21){
                System.out.println("Dealer has Black Jack. The bet is returned.");
                timer();
            }
        }
        else if (playerName.intSumCard()==21&&dealerName.deck.get(0)<10){
            System.out.println(playerName.getName() + " has Black Jack. \nDealer has less."+playerName.getName() + " won!");
        }
        if ()

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
        return dealerName.intSumCard();
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
    static void timer(){
        for (int i = 0;i<6;i++){
            System.out.print(".");
            try {TimeUnit.MILLISECONDS.sleep(1000);}catch (InterruptedException e){}
        }
        System.out.println();
    }
    static void timer(int second){
        for (int i = 0;i<second;i++){
            System.out.print(".");
            try {TimeUnit.MILLISECONDS.sleep(1000);}catch (InterruptedException e){}
        }
        System.out.println();
    }
    static void bank (ArrayList<Double> arrayBet, Player player){
        System.out.println("Amoint in the bank:");
        System.out.println(player.getName()+": "+arrayBet.get(0));
    }
    static void bank (ArrayList<Double> arrayBet, Player player, Player player1){
        System.out.println("Amoint in the bank:");
        System.out.println(player.getName()+": "+arrayBet.get(0));
        System.out.println(player1.getName()+": "+arrayBet.get(1));

    }
    static void bank (ArrayList<Double> arrayBet, Player player, Player player1, Player player2){
        System.out.println("Amoint in the bank:");
        System.out.println(player.getName()+": "+arrayBet.get(0));
        System.out.println(player1.getName()+": "+arrayBet.get(1));
        System.out.println(player2.getName()+": "+arrayBet.get(2));
    }
    static void bank (ArrayList<Double> arrayBet, Player player, Player player1, Player player2, Player player3){
        System.out.println("Amoint in the bank:");
        System.out.println(player.getName()+": "+arrayBet.get(0));
        System.out.println(player1.getName()+": "+arrayBet.get(1));
        System.out.println(player2.getName()+": "+arrayBet.get(2));
        System.out.println(player3.getName()+": "+arrayBet.get(3));
    }
}