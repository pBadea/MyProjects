package main.Model;
import javax.swing.*;

public class Tile {
    private  int tileNumber;
    private int x;
    private int y;
    private boolean isTheEmptyTile;
    private JLabel label;


    public int getTileNumber() {
        return tileNumber;
    }

    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTheEmptyTile(boolean theEmptyTile) {
        isTheEmptyTile = theEmptyTile;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public int getY() {
        return y;
    }

    public boolean isTheEmptyTile() {
        return isTheEmptyTile;
    }

    public JLabel getLabel() {
        return label;
    }

    public Tile(int tileNumber, int x, int y, boolean isTheEmptyTile, JLabel label) {
        this.tileNumber = tileNumber;
        this.x = x;
        this.y = y;
        this.isTheEmptyTile = isTheEmptyTile;
        this.label = label;
    }
    public Tile(){}
}
