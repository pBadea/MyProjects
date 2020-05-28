//package Tests;
//
//import main.Controller.Controller;
//import main.Model.Tile;
//import org.junit.Before;
//import org.junit.Test;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
////import org.junit.Assert;
//public class Tests {
//    private Controller controller;
//    private List <Tile> tiles;
//    private JLabel testLabel;
//
//    @Before
//    public void setUpIconForLabel(){
//        testLabel = new JLabel();
//        testLabel.setIcon(new Icon() {
//            @Override
//            public void paintIcon(Component c, Graphics g, int x, int y) {
//            }
//            @Override
//            public int getIconWidth() {
//                return 0;
//            }
//            @Override
//            public int getIconHeight() {
//                return 0;
//            }
//        });
//    }
//
//    @Before
//    public  void intitialiseEverything(){
//        controller = new Controller();
//        tiles = new ArrayList<Tile>();
//        int tileCounter = 0;
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                tileCounter++;
//                tiles.add(new Tile(tileCounter,i,j,false,testLabel));
//            }
//        }
//        controller.setTiles(tiles);
//    }
//    @Test
//    public void testTilesAreInOrder(){
//        assertEquals(true,controller.tilesAreInOrder());
//        }
//    @Test
//    public void testSwitchTwoTiles(){
//        controller.switchTwoTiles(0,0,0,1);
//        assertEquals(false,controller.tilesAreInOrder());
//        assertEquals(2,controller.getTiles().get(0).getTileNumber());
//        assertEquals(1,controller.getTiles().get(1).getTileNumber());
//    }
//    @Test
//    public void testTilesAreAdjacent(){
//        assertEquals(true,controller.tilseAreAdjacent(0,0,0,1));
//        assertEquals(true,controller.tilseAreAdjacent(0,0,1,0));
//        assertEquals(false,controller.tilseAreAdjacent(0,0,1,1));
//        assertEquals(false,controller.tilseAreAdjacent(0,0,2,1));
//    }
//
//    @Test
//    public void testALL(){
//        this.testTilesAreInOrder();
//        this.testSwitchTwoTiles();
//        this.testTilesAreAdjacent();
//    }
//}
