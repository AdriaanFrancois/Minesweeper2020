import java.util.Scanner;

public class main {

    public static void main(String[] arg){
        Scanner scan = new Scanner(System.in);

        // rijen binnehalen
        System.out.println("how many rows?");
        int rows = scan.nextInt();

        //kolommen binnen halen
        System.out.println("how many columns?");
        int columns = scan.nextInt();

        //bord maken
        Bord bord = new Bord(columns,rows);

        //hoe veel bommen ? & bommen toeveoegen
        System.out.println("how many Bombs? maximum "+(rows*columns/2)+" Bombs");
        int bombs = scan.nextInt();
        while (true){
            if (bombs <= (rows*columns/2)) {
                bord.addBombs(bombs);
                break;
            }
            else{
                System.out.println("Te veel Bommen, gelieve nieuw aantal in te geven");
                bombs = scan.nextInt();
            }
        }

        //berekenen en printen
        bord.calcValues();
        bord.printField();

        //eerste klik
        System.out.println("rij nummer ?");
        int yCoord = scan.nextInt()-1;
        System.out.println("kollom nummer ?");
        int xCoord = scan.nextInt()-1;
        bord.firstClick(yCoord,xCoord);

        //links of rechts klikken
        System.out.println("linker of rechter klik ? (input format: linker/rechter");
        String in = scan.nextLine();
        //coord updaten
        System.out.println("rij nummer ?");
        yCoord = scan.nextInt()-1;
        System.out.println("kollom nummer ?");
        xCoord = scan.nextInt()-1;
        if (in.equals("rechter")){

        }
        if (in.equals("linker")){
            bord.leftClick(yCoord,xCoord);
        }
    }
}
