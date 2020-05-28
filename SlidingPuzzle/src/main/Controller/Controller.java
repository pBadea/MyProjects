package main.Controller;

import main.Model.Tile;

import java.lang.Math;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class Controller {
    private List<Tile> tiles = new ArrayList<Tile>();

    public Controller() {
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }


    public void shuffle() {
        int numberOfRows = (int) (Math.sqrt(tiles.size()));
        for (int i = 0; i < numberOfRows - 1; ++i) {
            for (int j = 0; j < numberOfRows - 1; ++j) {
                int k = (int) Math.floor(Math.random() * numberOfRows);
                int l = (int) Math.floor(Math.random() * numberOfRows);
//                if t1 == t2 randomly inc second t2 pos
                if (i == k && j == l) {
                    double x = (int)(Math.random()*((numberOfRows-0)+1))+0;
                    if ((int)x >numberOfRows/2)
                        k++;
                    else
                        l++;
                }
                switchTwoTiles(i, j, k, l);
            }
        }
    }

    public boolean tilseAreAdjacent(int x1, int y1, int x2, int y2) {
        if (x1 == x2 && Math.abs(y1 - y2) == 1)
            return true;
        else if (y1 == y2 && Math.abs(x1 - x2) == 1)
            return true;
        else
            return false;
//        return true;
    }

    public Tile findATile(int x, int y) {
        for (Tile tile :
                tiles) {
            if (tile.getX() == x && tile.getY() == y)
                return tile;
        }
        return null;
    }


    public void switchTwoTiles(int firstTileX, int firstTileY, int secondTileX, int secondTileY) {
        Tile tile1 = null, tile2 = null;
        Icon tempIcon;
        int tempTileNumber;
//      Search for the two tiles
        for (Tile tile :
                tiles) {
            if (firstTileX == tile.getX() && firstTileY == tile.getY())
                tile1 = tile;
            else if (secondTileX == tile.getX() && secondTileY == tile.getY())
                tile2 = tile;
            else if (tile1 != null && tile2 != null)
                break;
        }
        tempIcon = tile1.getLabel().getIcon();
        tempTileNumber = tile1.getTileNumber();
        tile1.getLabel().setIcon(tile2.getLabel().getIcon());
        tile1.setTileNumber(tile2.getTileNumber());
        tile2.getLabel().setIcon(tempIcon);
        tile2.setTileNumber(tempTileNumber);
//        temp = tile1;
//        tile1 = tile2;
//        tile2 = temp;
    }

    public boolean tilesAreInOrder() {
        int tileCount = 0;
        for (Tile tile :
                tiles) {
            tileCount++;
            if (tileCount != tile.getTileNumber())
                return false;
        }
        return true;
    }


}
