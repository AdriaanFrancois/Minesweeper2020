public class Cell {

    private int row;
    private int column;
    private boolean visible =true;
    private boolean isBomb;
    private boolean flagged;
    private int value;

    public Cell (int r, int c){
        row = r;
        column = c;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public void setValue(int v){
        value = v;
    }

    public int getValue(){
        return value;
    }
}
