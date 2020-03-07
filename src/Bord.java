import java.util.Random;

public class Bord {

    private int rows;
    private int columns;
    private int nrofBombs;
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
                    if (cell.isBomb() && !cell.isFlagged()){
                        System.out.print("[B]");
                    }
                    if (!cell.isFlagged() && !cell.isBomb()){
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

    public void revealAround(int x, int y){
        bord[x][y].setVisible(true);
        if (bord[x][y].getValue()==0) {
            for (int i = x - 1; i < x + 2; i++) {
                for (int j = y - 1; j < y + 2; j++) {
                    if (i >= 0 && i < rows && j >= 0 && j < columns) {
                        if (!bord[i][j].isVisible() && !bord[i][j].isBomb()&& bord[i][j].getValue()==0) {
                            bord[i][j].setVisible(true);
                            revealAround(i, j);
                        }
                        if (!bord[i][j].isBomb()) {
                            bord[i][j].setVisible(true);
                        }
                    }
                }
            }
        }
    }

    public void firstClick(int r,int c){
        while(true) {
            if (bord[r][c].isBomb()) {
                bord[r][c].setBomb(false);
                addBombs(1);
            }
            else {
                calcValues();
                revealAround(r,c);
                }
                printField();
                break;
            }
        }

    public boolean leftClick(int r, int c) {
        if (bord[r][c].isBomb()) {
            System.out.println("You died better luck next time");
            for (Cell[] cl : bord) {
                for (Cell cell : cl) {
                    cell.setVisible(true);
                }
            }
            printField();
            return false;
        }
        else{
            revealAround(r,c);
            printField();
            return true;
        }
    }

    public void rightClick(int r, int c){
        if (bord[r][c].isFlagged()){
            bord[r][c].setFlagged(false);
            bord[r][c].setVisible(false);
            printField();
        }
        else{
            bord[r][c].setFlagged(true);
            bord[r][c].setVisible(true);
            printField();
        }
    }

    //winconditie
    public boolean winCheck(){
        int counter = 0;
        for (Cell[] c : bord){
            for (Cell cell :c){
                if (cell.isBomb() && cell.isFlagged()){
                    counter++;
                }
                if (cell.isFlagged() && !cell.isBomb()){
                    counter--;
                }
            }
        }
        if (counter == nrofBombs){
            return true;
        }
        else {
            return false;
        }
    }

    //om totaal aantal bommen in te lezen
    public void setNrOfBombs(int x){
        nrofBombs = x;
    }

}