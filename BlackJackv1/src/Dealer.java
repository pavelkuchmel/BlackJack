import java.util.ArrayList;

public class Dealer {
    private String name = "Dealer";
    public String getName(){
        return name;
    }
    static ArrayList<Integer> deck = new ArrayList<Integer>();
    public void takeCard(int a){
        deck.add(a);
    }
    public void takeCard(int a, int b){
        deck.add(a);
        deck.add(b);
    }
    public void showFirstCard(){
        System.out.println(deck.get(0));
        //return deck.get(0);
    }
    public void showAllCard(){
        int sum = 0;
        for (int i = 0; i<deck.size();i++){
            System.out.print(deck.get(i)+" ");
            sum += deck.get(i);
        }
        System.out.println("Sum: "+sum);
        int exit = sum;
    }
    public int intAllCard(){
        int sum = 0;
        for (int i = 0; i<deck.size();i++){
            sum += deck.get(i);
        }
        return sum;
    }
}
