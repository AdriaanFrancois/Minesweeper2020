import java.util.Scanner;

public class main {
    Scanner scan = new Scanner(System.in);

    public static void main(String[] arg){
        Bord bord = new Bord(5,5);
        bord.addBombs(5);
        bord.calcValues();
        bord.printField();
    }

    public int askRows(){
        System.out.println("how many rows?");
        return scan.nextInt();
    }

    public int askCol(){
        System.out.println("how many columns?");
        return scan.nextInt();
    }


}
