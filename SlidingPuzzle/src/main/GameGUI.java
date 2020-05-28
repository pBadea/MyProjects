package main;

import main.Model.MyTimerThread;
import main.Model.*;
import main.Controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.JComponent;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameGUI {
    private MyTimerThread myTimerThread = new MyTimerThread();
    private BufferedImage sourceImage;
    private Image image;
    private Image imageChosenByUser;
    private JButton shuffleTilesButton;
    private JButton backButton;
    private JPanel gamePanel;
    private JPanel tilesPanel;
    private int numberOfRows;
    private MainMenu mainMenu;
    private List<Tile> tiles = new ArrayList<Tile>();
    private Tile invizibleTile;
    private Controller controller;
    private boolean playerHasWon = false;
    private Thread timer;

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public GameGUI() {
        $$$setupUI$$$();


    }

    public GameGUI(MainMenu mainMenu, int numberOfRows) {
        $$$setupUI$$$();
        this.mainMenu = mainMenu;
        this.numberOfRows = numberOfRows;
        gamePanel.add(tilesPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        setUpPuzzle();


//        mainMenu.getMainFrame().addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                int key = e.getKeyCode();
//                int x = invizibleTile.getX();
//                int y = invizibleTile.getY();
//                System.out.println(e.getKeyChar());
//                if (key == KeyEvent.VK_LEFT) {
//                    System.out.println("LEFT PRESSED");
//                    switchTwoTiles(x, y, x - 1, y);
//
//                }
//
//                if (key == KeyEvent.VK_RIGHT) {
//                    System.out.println("RIGHT PRESSED");
//                }
//
//                if (key == KeyEvent.VK_UP) {
//                    System.out.println("UP PRESSED");
//                    switchTwoTiles(x, y, x, y - 1);
//                    invizibleTile.setVisible(true);
//                    invizibleTile = findATile(x, y - 1);
//                    invizibleTile.setVisible(false);
//                }
//
//                if (key == KeyEvent.VK_DOWN) {
//                    System.out.println("DOWN PRESSED");
//                }
//            }
//        });
    }

    public void setUpTimer() {
        timer = new Thread() {
            public void run() {
                while (true) {
                    try {
                        if (playerHasWon)
                            break;
                        Thread.sleep(10 * 1000);
                        showDefeatFrame();
//                        mainMenu.getMainFrame().setVisible(false);
                        break;
//                        System.out.println("Neatza, m-am trezit");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
    }

    public void showDefeatFrame() {
        JFrame defeatFrame = new JFrame("Defeat");
//        defeatFrame.pack();
        JLabel defeatText = new JLabel();
        defeatText.setText("Looks like you are out of time,but no worries I'll let you finish this round;)");
        defeatFrame.add(defeatText);
//        JButton okButton = new JButton("Try again");
//        okButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                controller.shuffle();
//                timer.start();
//                tilesPanel.setVisible(true);
//                defeatFrame.setVisible(false);
//            }
//        });
//        defeatFrame.add(okButton);
        defeatFrame.setSize(600, 100);
        defeatFrame.setVisible(true);
    }


    public void showVictoryScreen() {
        JFrame victoryFrame = new JFrame("Victory");
        victoryFrame.pack();
        JLabel victoryText = new JLabel();
        victoryText.setText("Congrats, you won !!!");

        JButton okButton = new JButton("Cool");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                victoryFrame.setVisible(false);
            }
        });
        victoryFrame.add(victoryText);

//        victoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        victoryFrame.setSize(300, 100);
        victoryFrame.setVisible(true);
    }

    public void setUpPuzzle() {

//        if user didn't choose a file
        if (mainMenu.getImage() == null)
            try {
                sourceImage = ImageIO.read(new File("resources/movie_doggo.jpg"));

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this.gamePanel, "Could not load image", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        else
//            img = new Image(mainMenu.getImage().toURI().toString(),500,500,true,true,true)
        {
            try {
                imageChosenByUser = ImageIO.read(mainMenu.getImage());
            } catch (IOException e) {
                e.printStackTrace();
            }
            sourceImage = (BufferedImage) imageChosenByUser;
        }
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        int tileCount = 0;
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                tileCount++;
                image = gamePanel.createImage(new FilteredImageSource(sourceImage.getSource(),
                        new CropImageFilter(j * width / numberOfRows, i * height / numberOfRows,
                                (width / numberOfRows), height / numberOfRows)));
                if (numberOfRows > 3)
                    image = image.getScaledInstance(width / (numberOfRows * 3), height / (numberOfRows * 3), Image.SCALE_DEFAULT);
                else
                    image = image.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
                JLabel label = new JLabel(new ImageIcon(image));
                int finalI = i, finalJ = j;
                Tile tile = new Tile(tileCount, finalI, finalJ, false, label);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (controller.tilseAreAdjacent(tile.getX(), tile.getY(), invizibleTile.getX(), invizibleTile.getY())) {
                            controller.switchTwoTiles(tile.getX(), tile.getY(), invizibleTile.getX(), invizibleTile.getY());
                            label.setVisible(false);
                            invizibleTile.setTheEmptyTile(false);
                            JLabel oldInvizibleLable = invizibleTile.getLabel();
                            oldInvizibleLable.setVisible(true);
                            invizibleTile = tile;
                            if (controller.tilesAreInOrder()) {
                                showVictoryScreen();
                                invizibleTile.getLabel().setVisible(true);
                                playerHasWon = true;
                            }
                        }
                    }
                });
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                final GridBagConstraints c = new GridBagConstraints();
                c.gridx = j;
                c.gridy = i;
                if (i == numberOfRows - 1 && j == numberOfRows - 1) {
                    label.setVisible(false);
                    tile.setTheEmptyTile(true);
                    invizibleTile = tile;
                }
                tiles.add(tile);
                tilesPanel.add(label, c);
            }
        }
        controller.setTiles(tiles);
        tilesPanel.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        controller = new Controller();
        shuffleTilesButton = new JButton();
        shuffleTilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                myTimerThread.run();
                setUpTimer();

                if (playerHasWon) {
                    playerHasWon = false;
                    invizibleTile.getLabel().setVisible(false);
                }
                controller.shuffle();
                controller.shuffle();
                timer.start();
//                controller.shuffle();
//                controller.shuffle();
//                controller.shuffle();
//                controller.shuffle();
//                controller.shuffle();
//                controller.shuffle();
//                tiles = controller.getTiles();
//                controller.switchTwoTiles(tile.getX(), tile.getY(), invizibleTile.getX(), invizibleTile.getY());
//                label.setVisible(false);
//                invizibleTile.setTheEmptyTile(false);
//                JLabel oldInvizibleLable = invizibleTile.getLabel();
//                oldInvizibleLable.setVisible(true);
//                invizibleTile = tile;
            }
        });
        backButton = new JButton();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setVisible(false);
                mainMenu.startMainMenu();
            }
        });
        tilesPanel = new JPanel();
        tilesPanel.setLayout(new GridBagLayout());
        tilesPanel.setBackground(Color.BLUE);
        tilesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null));

    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        gamePanel = new JPanel();
        gamePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        shuffleTilesButton.setText("Shuffle tiles");
        gamePanel.add(shuffleTilesButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backButton.setText("Back");
        gamePanel.add(backButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gamePanel.add(tilesPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tilesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return gamePanel;
    }

}
