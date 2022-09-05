import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameTable {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rdm = new Random();
        ArrayList<Integer> deck = new ArrayList<Integer>(52);
        //ArrayList<Integer> usedCard = new ArrayList<Integer>();
        //ArrayList<Double> bets = new ArrayList<Double>(4);
        double betBank[][] = new double[2][4];
            for (int j = 0;j < 4; j++){
                betBank[1][j] = 10;
            }
        Dealer dealer = new Dealer();
        Player player = new Player();
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        demo();
        System.out.print("\n\n\n\n\n\n\n\nEnter quantity players: ");
        int numPlayers = in.nextInt();
        switch (numPlayers){
            case 1:
                System.out.print("Enter Player name: ");
                player.setName(in.next());
                break;
            case 2:
                System.out.print("Enter Player 1 name: ");
                player.setName(in.next());
                System.out.print("Enter Player 2 name: ");
                player1.setName(in.next());
                break;
            case 3:
                System.out.print("Enter Player 1 name: ");
                player.setName(in.next());
                System.out.print("Enter Player 2 name: ");
                player1.setName(in.next());
                System.out.print("Enter Player 3 name: ");
                player2.setName(in.next());
                break;
            case 4:
                System.out.print("Enter Player 1 name: ");
                player.setName(in.next());
                System.out.print("Enter Player 2 name: ");
                player1.setName(in.next());
                System.out.print("Enter Player 3 name: ");
                player2.setName(in.next());
                System.out.print("Enter Player 4 name: ");
                player3.setName(in.next());
                break;
        }
        for (int i = 0;;i++){
            deck.clear();
            player.deck.clear();
            dealer.deck.clear();
            fillDeck(deck);
            shaffleDeck(deck);
            System.out.println("\n\n\n\n\n\n\n\nPlayers place a bet.");
            for (int s = 1;s<=numPlayers;s++){
                double bet;
                switch (s){
                    case 1:
                        do {
                            System.out.print(player.getName()+": ");
                            bet = in.nextDouble();
                        }while (bet>betBank[1][0]);
                        betBank[0][0] = bet;
                        break;
                    case 2:
                        do {
                            System.out.print(player1.getName()+": ");
                            bet = in.nextDouble();
                        }while (bet>betBank[1][1]);
                        betBank[0][1] = bet;
                        break;
                    case 3:
                        do {
                            System.out.print(player.getName()+": ");
                            bet = in.nextDouble();
                        }while (bet>betBank[1][2]);
                        betBank[0][2] = bet;
                        break;
                    case 4:
                        do {
                            System.out.print(player.getName()+": ");
                            bet = in.nextDouble();
                        }while (bet>betBank[1][3]);
                        betBank[0][3] = bet;
                        break;
                }
            }

            System.out.print("\n\n\n\n\n\n\n\n");
            timer("Dealer deals card");
            System.out.print("\n\n\n\n\n\n\n\n");
            dealer.takeCard(takeRdmCard(deck), takeRdmCard(deck));
            switch (numPlayers){
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
            countPoints(dealer, player, deck, betBank);
            System.out.print("Show bank? y/or other: ");
            char bank = in.next().charAt(0);
            if (bank == 'y' ) {
                bank(betBank, player, player1, player2, player3, numPlayers);
            }
            System.out.print("Continue game? y/or other: ");
            if (bank != 'y' ) {
                System.out.println("\n\n\n\n\n\n\n\n    ");
                timer("Bye");
                break;
            }
            timer();
        }
    }
    static void countPoints (Dealer dealerName, Player playerName, ArrayList<Integer> arrayName, double[][]betBank) {
        Scanner in = new Scanner(System.in);
        if (playerName.intSumCard()==21){
            showCard(dealerName, playerName);
            System.out.print(playerName.getName()+" has Black Jack! ");
            //timer(" Dealer open second card");
            //System.out.println("\n\n\n\n\n\n\n\n");
            if (dealerName.deck.get(0)==10||dealerName.deck.get(0)==11){
                System.out.println("Dealer first card "+dealerName.deck.get(0)+".");
                timer("Dealer open second card");
                timer(5);
                System.out.println("\n\n\n\n\n\n\n\n");
                if (dealerName.intSumCard()==21){
                    showCard(dealerName, playerName, 1);
                    System.out.println("The dealer also has a Black Jack. \nBets return.");
                }
                else {
                    showCard(dealerName, playerName, 1);
                    System.out.println("Dealer has less. \n"+playerName.getName()+" won!");
                    betBank[1][0] += betBank[0][0]*0.5;
                    betBank[0][0] = 0;
                }
            }
            else {
                //System.out.println("\n\n\n\n\n\n\n\n");
                //showCard(dealerName, playerName, 1);
                System.out.println("Dealer less. \n"+playerName.getName()+" won!");
                betBank[1][0] += betBank[0][0]*1.5;
                betBank[0][0] = 0;
            }
        }
        else {
            showCard(dealerName, playerName);
            more(dealerName, playerName, arrayName, betBank);
        }
    }
    static void more (Dealer dealerName, Player playerName, ArrayList<Integer> arrayName, double[][]betBank){
        Scanner in = new Scanner(System.in);
        System.out.print("Dealer: ");
        timer(playerName.getName()+", more? ");
        System.out.print("y/or other: ");
        char more = in.next().charAt(0);
        if (more == 'y'){
            timer("Dealer giving card");
            playerName.takeCard(takeRdmCard(arrayName));
            System.out.println("\n\n\n\n\n\n\n\n");
            if (playerName.intSumCard() == 21) {
                showCard(dealerName, playerName);
                System.out.println(playerName.getName()+" has 21 points.");
                timer("Dealer open all cards");
                System.out.println("\n\n\n\n\n\n\n\n");
                showCard(dealerName, playerName, 1);
                dealerMore(dealerName, playerName, arrayName, betBank);
            }
            else {
                if (playerName.intSumCard() > 21){
                    showCard(dealerName, playerName);
                    System.out.println(playerName.getName()+" has more 21 points. "+playerName.getName()+" lose.");
                    timer("Dealer open all cards");
                    System.out.println("\n\n\n\n\n\n\n\n");
                    showCard(dealerName, playerName, 1);
                    System.out.print("Dealer: ");
                    timer(playerName.getName()+" loses his bet");
                    betBank[0][0]=0;
                    //dealerMore(dealerName, playerName, arrayName, betBank);
                }
                else {
                    showCard(dealerName, playerName);
                    more(dealerName, playerName, arrayName, betBank);
                }
            }
        }
        else {
            dealerMore(dealerName, playerName, arrayName, betBank);
        }
    }
    static void dealerMore(Dealer dealerName, Player playerName, ArrayList<Integer> arrayName, double[][]betBank){
        if (dealerName.intSumCard()<17){
            timer("Dealer take more");
            dealerName.takeCard(takeRdmCard(arrayName));
            System.out.println("\n\n\n\n\n\n\n\n");
            showCard(dealerName, playerName, 1);
            dealerMore(dealerName, playerName, arrayName, betBank);
        }
        else {
            if (dealerName.intSumCard() == playerName.intSumCard()){
                System.out.println("Nobody won. Bets return");
                timer();
            }
            else {
                if (playerName.intSumCard() > dealerName.intSumCard()){
                    System.out.println(playerName.getName()+" has more. "+playerName.getName()+" won.");
                    betBank[1][0] = betBank[0][0]*0.5;
                    betBank[0][0] = 0;
                }
                else {
                    System.out.println("Dealer has more. "+playerName.getName()+" lose.");
                    betBank[0][0] = 0;
                }
            }
        }
    }
    static int takeRdmCard(ArrayList<Integer> arrayName/*, ArrayList<Integer> arrayIndex*/){
        Random rdm = new Random();
        int cardIndex = rdm.nextInt(arrayName.size());
        int card = arrayName.get(cardIndex);
        arrayName.remove(cardIndex);
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
        arrayName.set(index1, arrayName.get(index2));
        arrayName.set(index2, temp);
    }
    static void timer(){
        for (int i = 0;i<10;i++){
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
    static void timer(String text){
        ArrayList<Character> moveString = new ArrayList<>();
        int i = 0;
        for (;;i++){
            try {
                moveString.add(text.charAt(i));
            }catch (StringIndexOutOfBoundsException e) {
                break;
            }
        }
        for (int j = 0;j<i;j++){
            System.out.print(moveString.get(j));
            try {TimeUnit.MILLISECONDS.sleep(250);}catch (InterruptedException e){}
        }
        for (int l = 0;l<3;l++){
            System.out.print(".");
            try {TimeUnit.MILLISECONDS.sleep(1000);}catch (InterruptedException e){}
        }
        //System.out.println();
    }
    static void demo (){
        ArrayList<Character> moveString = new ArrayList<>();
        String text = "Black Jack\nMade by Pavel K.";
        int i = 0;
        for (;;i++){
            try {
                moveString.add(text.charAt(i));
            }catch (StringIndexOutOfBoundsException e) {
                break;
            }
        }
        System.out.println("\n\n");
        System.out.print("   ");
        for (int j = 0;j<i;j++){
            System.out.print(moveString.get(j));
            try {TimeUnit.MILLISECONDS.sleep(225);}catch (InterruptedException e){}
        }
        System.out.println("\n\n");
        try {TimeUnit.MILLISECONDS.sleep(1000);}catch (InterruptedException e){}
    }
    static void bank (double[][]betBank, Player player, Player player1, Player player2, Player player3, int numPlayers){
        System.out.println("Amount in the bank:");
        for (int i = 1;i <= numPlayers;i++){
            switch (i){
                case 1:
                    System.out.println(player.getName()+": "+betBank[1][0]);
                    break;
                case 2:
                    System.out.println(player1.getName()+": "+betBank[1][1]);
                    break;
                case 3:
                    System.out.println(player2.getName()+": "+betBank[1][2]);
                    break;
                case 4:
                    System.out.println(player3.getName()+": "+betBank[1][3]);
                    break;
            }
        }
    }
    static void bank (double[][]betBank, Player player, Player player1){
        System.out.println("Amoint in the bank:");
        System.out.println(player.getName()+": "+betBank[1][0]);
        System.out.println(player1.getName()+": "+betBank[1][1]);

    }
    static void bank (double[][]betBank, Player player, Player player1, Player player2){
        System.out.println("Amoint in the bank:");
        System.out.println(player.getName()+": "+betBank[1][0]);
        System.out.println(player1.getName()+": "+betBank[1][1]);
        System.out.println(player2.getName()+": "+betBank[1][2]);
    }
    /*static void bank (double[][]betBank, Player player, Player player1, Player player2, Player player3){
        System.out.println("Amoint in the bank:");
        System.out.println(player.getName()+": "+betBank[1][0]);
        System.out.println(player1.getName()+": "+betBank[1][1]);
        System.out.println(player2.getName()+": "+betBank[1][2]);
        System.out.println(player3.getName()+": "+betBank[1][3]);
    }*/
}