import java.util.ArrayList;
import java.util.Random;

public class Bord {

    private int rows;
    private int columns;
    private Cell[][] bord;

    public Bord (int rs, int cs){
        rows = rs;
        columns = cs;
        bord = fillBord(rs,cs);
    }

    public Cell[][] fillBord (int r, int c){
        Cell[][] b = new Cell[r][c];
        for (int x = 0; x < r ; x++){
            for (int y = 0; y < c ; y++){
                Cell cell = new Cell(x,y);
                b [x][y] = cell;
            }
        }
        return b;
    }

    public void calcValues(){
        int counter;
        for (int x=0; x<rows;x++){
            for (int y=0; y<columns;y++){
                counter = 0;
                for (int i = x-1 ; i<x+2 ; i++){
                    for (int j = y-1 ; j<y+2 ; j++){
                        if (i>=0 && i<rows && j>=0 && j<columns){
                            if (bord[i][j].isBomb()){
                                counter++;
                            }
                        }
                    }
                }
                bord[x][y].setValue(counter);
            }
        }
    }

    public void printField() {
        for (Cell[] c : bord) {
            for (Cell cell : c) {
                if (!cell.isVisible()) {
                    System.out.print("[" + " " + "]");
                } else {
                    if (cell.isFlagged()) {
                        System.out.print("[F]");
                    }
                    if (cell.isBomb()){
                        System.out.print("[B]");
                    }
                    else {
                        System.out.print("[" + cell.getValue() + "]");
                    }
                }
            }
            System.out.println();
        }
    }

    public void addBombs(int nrBombs){
        for (int b = 0; b<nrBombs; b++){
            Random rand = new Random();
            int posx = rand.nextInt(rows);
            int posy = rand.nextInt(columns);
            if (!bord[posx][posy].isBomb()){
                bord[posx][posy].setBomb(true);
            }
            else{
                b--;
            }
        }
    }

    public void firstClick(int r,int c){
        while(true) {
            if (bord[r][c].isBomb()) {
                bord[r][c].setBomb(false);
                addBombs(1);
            } else {
                bord[r][c].setVisible(true);
                calcValues();
                printField();
                break;
            }
        }
    }

    public void leftClick(int r, int c) {
        if (bord[r][c].isBomb()) {
            System.out.println("You died better luck next time");
            for (Cell[] cl : bord) {
                for (Cell cell : cl) {
                    cell.setVisible(true);
                }
            }
            printField();
        }
        else{
            bord[r][c].setVisible(true);
            printField();
        }
    }

    public void rightClick(int r, int c){
        if (bord[r][c].isFlagged()){
            bord[r][c].setFlagged(false);
        }
        bord[r][c].setFlagged(true);
    }

}