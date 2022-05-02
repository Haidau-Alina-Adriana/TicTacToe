package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {
    final com.company.MainFrame frame;
    private int x, y;
    private int rows, cols;
    private int boardWidth, boardHeight;
    private int cellWidth, cellHeight;
    private int padX, padY;
    private final int canvasWidth = 800, canvasHeight = 600;
    private BufferedImage image;
    private Graphics2D offscreen;

    private List<Square> squares;
//    private Map<Stone, Player> positions;
//    private Board board;
//    private Stone previousStone;
//    private boolean endOfGame;
//    Player player;
//    private Game game;
//    int turn;
//    List<Player> players = new ArrayList<>();
//    private MouseAdapter mouse;


    public DrawingPanel(com.company.MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0, 0, canvasWidth, canvasHeight);
    }

    private void init() {
        this.padX = 30;
        this.padY = 30;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                repaint();
            }
        });
    }

    public void playWithoutAccount() {
        frame.configPanel.modifyHelpMessage("You are playing with the computer. Select difficulty: ");
        JButton randomMoves = new JButton("Random Moves");
        JButton alternateMoves = new JButton("Alternate Moves");
        JButton bestPossibleMoves = new JButton("Best Possible Moves");

        frame.canvas.setLayout(null);
        randomMoves.setBounds(250, 150, 300, 40);
        alternateMoves.setBounds(250, 250, 300, 40);
        bestPossibleMoves.setBounds(250, 350, 300, 40);
        randomMoves.addActionListener(e -> {
            frame.canvas.remove(randomMoves);
            frame.canvas.remove(alternateMoves);
            frame.canvas.remove(bestPossibleMoves);
            repaint();
            selectGridSize();
        });
        alternateMoves.addActionListener(e -> {
            frame.canvas.remove(randomMoves);
            frame.canvas.remove(alternateMoves);
            frame.canvas.remove(bestPossibleMoves);
            repaint();
            selectGridSize();
        });
        bestPossibleMoves.addActionListener(e -> {
            frame.canvas.remove(randomMoves);
            frame.canvas.remove(alternateMoves);
            frame.canvas.remove(bestPossibleMoves);
            repaint();
            selectGridSize();
        });
        frame.canvas.add(randomMoves);
        frame.canvas.add(alternateMoves);
        frame.canvas.add(bestPossibleMoves);
        frame.canvas.setSize(canvasWidth, canvasHeight - 1);
    }

    public void selectGridSize() {
        frame.configPanel.modifyHelpMessage("Select size of the board: ");
        frame.canvas.setLayout(null);
        JSpinner width = new JSpinner(new SpinnerNumberModel(3, 3, 10, 1));
        JSpinner height = new JSpinner(new SpinnerNumberModel(3, 3, 10, 1));
        JButton nextButton = new JButton("Next");
        JLabel numberOfRows = new JLabel("Number of rows:");
        JLabel numberOfCols = new JLabel("Number of cols:");
        width.setBounds(400, 150, 150, 40);
        height.setBounds(400, 250, 150, 40);
        nextButton.setBounds(250, 350, 300, 40);
        numberOfRows.setBounds(250, 250, 100, 40);
        numberOfCols.setBounds(250, 150, 100, 40);
        nextButton.addActionListener(e -> {
            rows = (int) height.getValue() + 1;
            cols = (int) width.getValue() + 1;
            frame.canvas.remove(width);
            frame.canvas.remove(height);
            frame.canvas.remove(nextButton);
            frame.canvas.remove(numberOfCols);
            frame.canvas.remove(numberOfRows);
            repaint();
            selectColor();
        });
        frame.canvas.add(numberOfRows);
        frame.canvas.add(numberOfCols);
        frame.canvas.add(height);
        frame.canvas.add(width);
        frame.canvas.add(nextButton);
    }

    public void selectColor() {
        frame.configPanel.modifyHelpMessage("Select color: ");
        frame.canvas.setLayout(null);

        JButton nextButton = new JButton("Next");
        JButton changeColorButton = new JButton("Change color");
        JLabel labelChosenColor = new JLabel("Current color: ");
        JButton chosenColor = new JButton("");
        labelChosenColor.setBounds(250, 150, 150, 40);
        chosenColor.setBounds(400, 150, 150, 40);
        chosenColor.setBackground(Color.red);
        changeColorButton.setBounds(250, 250, 300, 40);
        nextButton.setBounds(250, 350, 300, 40);
        nextButton.addActionListener(e -> {
            frame.canvas.remove(labelChosenColor);
            frame.canvas.remove(changeColorButton);
            frame.canvas.remove(nextButton);
            frame.canvas.remove(chosenColor);
            repaint();
            reinitializeCanvas();
        });
        changeColorButton.addActionListener(e -> {
            Color color = JColorChooser.showDialog(frame.canvas, "Select a Color", Color.white);
            chosenColor.setBackground(color);

        });
        frame.canvas.add(labelChosenColor);
        frame.canvas.add(changeColorButton);
        frame.canvas.add(nextButton);
        frame.canvas.add(chosenColor);
    }

    public void login() {
        frame.configPanel.modifyHelpMessage("You are playing with the other users. Login first: ");
        JButton loginButton = new JButton("Login");
        JLabel labelName = new JLabel("Username: ");
        JTextField username = new JTextField();
        JLabel labelPassword = new JLabel("Password: ");
        JTextField password = new JTextField();
        frame.canvas.setLayout(null);

        labelName.setBounds(250, 150, 150, 40);
        username.setBounds(350, 150, 200, 40);
        labelPassword.setBounds(250, 250, 150, 40);
        password.setBounds(350, 250, 200, 40);
        loginButton.setBounds(250, 350, 300, 40);

        loginButton.addActionListener(e -> {
            if(checkCredentials(username.getText(), password.getText())){
                frame.canvas.remove(loginButton);
                frame.canvas.remove(labelName);
                frame.canvas.remove(username);
                frame.canvas.remove(labelPassword);
                frame.canvas.remove(password);
                repaint();
                selectGridSize();
            }
        });
        frame.canvas.add(labelName);
        frame.canvas.add(username);
        frame.canvas.add(labelPassword);
        frame.canvas.add(password);
        frame.canvas.add(loginButton);
    }

    public boolean checkCredentials(String username, String password){
        System.out.println(username);
        System.out.println(password);
        return true;
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

    public void reinitializeCanvas() {

        init();
        createOffscreenImage();
        repaint();
        frame.configPanel.modifyHelpMessage("Choose a box: ");
        paintGrid();
        getStonesCoordinates();
        // frame.canvas.removeMouseListener(mouse);
    }

    private void paintGrid() {
        offscreen.setColor(Color.BLACK);
        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            offscreen.drawLine(x1, y1, x2, y1);
        }

        for (int col = 0; col < cols; col++) {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int y2 = padY + boardHeight;
            offscreen.drawLine(x1, y1, x1, y2);
        }
    }

    private void getStonesCoordinates() {
        squares = new ArrayList<>();
        int counter = 0;
        for (int row = 0; row < rows - 1; row++) {
            for (int col = 0; col < cols - 1; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                squares.add(new Square(counter, x, y));
                counter++;
            }
        }

//        for(Square s : squares){
//            System.out.println(s.getIndex());
//        }
    }
    public List<Square> getSquares(){
        return squares;
    }
//
//    private Map<Stone, List<Stone>> paintAndCreateSticks() {
//        Random rand = new Random();
//        int numberOfSticks = -1;
//        while (numberOfSticks < (rows * cols)) {
//            numberOfSticks = rand.nextInt(rows * (cols - 1) + cols * (rows - 1) / 2);
//        }
//
//        offscreen.setColor(Color.BLACK);
//        offscreen.setStroke(new BasicStroke(7.0f));
//        Map<Stone, List<Stone>> neighbours = new TreeMap<>();
//
//        for (Stone s : stones) {
//            neighbours.put(s, new ArrayList<>());
//        }
//
//        for (int i = 0, n = numberOfSticks; i < n; i++) {
//            while (true) {
//                int firstRandStone = rand.nextInt(stones.size());
//                int secondRandStone = rand.nextInt(stones.size());
//                if (firstRandStone == secondRandStone) {
//                    continue;
//                }
//                if (secondRandStone != (firstRandStone - 1) && secondRandStone != (firstRandStone + 1)
//                        && secondRandStone != (firstRandStone - cols) && secondRandStone != (firstRandStone + cols)) {
//                    continue;
//                }
//                if (((firstRandStone + 1) % cols == 0 && secondRandStone == firstRandStone + 1) ||
//                        (firstRandStone % cols == 0 && secondRandStone == firstRandStone - 1)) {
//                    continue;
//                }
//                if (neighbours.get(stones.get(firstRandStone)).contains(stones.get(secondRandStone))) {
//                    continue;
//                }
//                neighbours.get(stones.get(firstRandStone)).add(stones.get(secondRandStone));
//                neighbours.get(stones.get(secondRandStone)).add(stones.get(firstRandStone));
//                offscreen.drawLine(stones.get(firstRandStone).getX(), stones.get(firstRandStone).getY(),
//                        stones.get(secondRandStone).getX(), stones.get(secondRandStone).getY());
//                break;
//            }
//        }
//
//        Set<Integer> marginUp = new HashSet<>();
//        Set<Integer> marginDown = new HashSet<>();
//
//        for (int j = 0; j < cols; j++) {
//            marginUp.add(j);
//        }
//        for (int j = 0; j < cols; j++) {
//            marginDown.add(stones.get((rows - 1) * cols + j).getIndex());
//        }
//
//        for (Map.Entry<Stone, List<Stone>> it : neighbours.entrySet()) {
//            Stone end1 = it.getKey();
//
//            if (end1.getIndex() == 0 || end1.getIndex() == cols - 1 ||
//                    end1.getIndex() == ((rows * cols - 1) - (cols - 1)) || end1.getIndex() == (rows * cols) - 1) {
//            } else if (end1.getIndex() % cols == 0) {
//
//                neighbours.get(end1).add(stones.get(end1.getIndex() + 1));
//                neighbours.get(stones.get(end1.getIndex() + 1)).add(end1);
//
//                neighbours.get(end1).add(stones.get(end1.getIndex() + cols));
//                neighbours.get(stones.get(end1.getIndex() + cols)).add(end1);
//
//                neighbours.get(end1).add(stones.get(end1.getIndex() - cols));
//                neighbours.get(stones.get(end1.getIndex() - cols)).add(end1);
//
//                offscreen.drawLine(end1.getX(), end1.getY(),
//                        stones.get(end1.getIndex() + 1).getX(), stones.get(end1.getIndex() + 1).getY());
//
//                offscreen.drawLine(end1.getX(), end1.getY(),
//                        stones.get(end1.getIndex() + cols).getX(), stones.get(end1.getIndex() + cols).getY());
//
//                offscreen.drawLine(end1.getX(), end1.getY(),
//                        stones.get(end1.getIndex() - cols).getX(), stones.get(end1.getIndex() - cols).getY());
//            } else if ((end1.getIndex() + 1) % cols == 0) {
//                neighbours.get(end1).add(stones.get(end1.getIndex() - 1));
//                neighbours.get(stones.get(end1.getIndex() - 1)).add(end1);
//
//                neighbours.get(end1).add(stones.get(end1.getIndex() + cols));
//                neighbours.get(stones.get(end1.getIndex() + cols)).add(end1);
//
//                neighbours.get(end1).add(stones.get(end1.getIndex() - cols));
//                neighbours.get(stones.get(end1.getIndex() - cols)).add(end1);
//
//                offscreen.drawLine(end1.getX(), end1.getY(),
//                        stones.get(end1.getIndex() - 1).getX(), stones.get(end1.getIndex() - 1).getY());
//
//                offscreen.drawLine(end1.getX(), end1.getY(),
//                        stones.get(end1.getIndex() + cols).getX(), stones.get(end1.getIndex() + cols).getY());
//
//                offscreen.drawLine(end1.getX(), end1.getY(),
//                        stones.get(end1.getIndex() - cols).getX(), stones.get(end1.getIndex() - cols).getY());
//
//            } else if (marginUp.contains(end1.getIndex())) {
//                neighbours.get(end1).add(stones.get(end1.getIndex() + cols));
//                neighbours.get(stones.get(end1.getIndex() + cols)).add(end1);
//
//                offscreen.drawLine(end1.getX(), end1.getY(),
//                        stones.get(end1.getIndex() + cols).getX(), stones.get(end1.getIndex() + cols).getY());
//
//            } else if (marginDown.contains(it.getKey().getIndex())) {
//                neighbours.get(end1).add(stones.get(end1.getIndex() - cols));
//                neighbours.get(stones.get(end1.getIndex() - cols)).add(end1);
//
//                offscreen.drawLine(end1.getX(), end1.getY(),
//                        stones.get(end1.getIndex() - cols).getX(), stones.get(end1.getIndex() - cols).getY());
//            } else if (it.getValue().size() == 1) {
//
//                neighbours.get(it.getKey()).add(stones.get(it.getKey().getIndex() - cols));
//                neighbours.get(stones.get(it.getKey().getIndex() - cols)).add(it.getKey());
//
//                offscreen.drawLine(it.getKey().getX(), it.getKey().getY(),
//                        stones.get(it.getKey().getIndex() - cols).getX(), stones.get(it.getKey().getIndex() - cols).getY());
//
//                neighbours.get(it.getValue().get(0)).add(stones.get(it.getKey().getIndex() + cols));
//                neighbours.get(stones.get(it.getKey().getIndex() + cols)).add(it.getValue().get(0));
//
//                offscreen.drawLine(it.getKey().getX(), it.getKey().getY(),
//                        stones.get(it.getKey().getIndex() + cols).getX(), stones.get(it.getKey().getIndex() + cols).getY());
//
//            }
//        }
//        return neighbours;
//    }
//
//    public void paintStone() {
//        offscreen.setStroke(new BasicStroke(5.0f));
//        mouse = new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
//                x = e.getX();
//                y = e.getY();
//                if (checkStone(x, y)) {
//                    repaint();
//                }
//            }
//        };
//        this.addMouseListener(mouse);
//    }
//
//    public boolean isAvailableStone(Stone stone) {
//        if (positions.get(stone) != null) {
//            return false;
//        }
//        return true;
//    }
//
//    public boolean isAdjacentStone(Stone stone) {
//        if (previousStone == null) {
//            return true;
//        }
//        return board.getEdges().get(previousStone).contains(stone);
//    }
//
//    public boolean checkStone(int x, int y) {
//        offscreen.setColor(player.getColor());
//        boolean foundStone = false;
//        for (Stone stone : board.getEdges().keySet()) {
//            if (endOfGame) {
//                break;
//            }
//            if (x > stone.getX() - stoneSize / 2 && x < stone.getX() + stoneSize / 2
//                    && y > stone.getY() - stoneSize / 2 && y < stone.getY() + stoneSize / 2) {
//                if (isAvailableStone(stone)) {
//                    if (isAdjacentStone(stone)) {
//                        offscreen.fillOval(stone.getX() - stoneSize / 2, stone.getY() - stoneSize / 2, stoneSize, stoneSize);
//                        positions.put(stone, player);
//                        previousStone = stone;
//                        if (turn == 0) {
//                            turn = 1;
//                        } else {
//                            turn = 0;
//                        }
//                        player = game.getPlayers().get(turn);
//                        return true;
//                    } else {
//                        endOfGame = true;
//                        foundStone = true;
//                        frame.canvas.removeMouseListener(mouse);
//                        System.out.println("Not a valid move! You have lost!\n");
//                        if (turn == 0) {
//                            System.out.println("Winner is: " + game.getPlayers().get(1).getName());
//                        } else {
//                            System.out.println("Winner is: " + game.getPlayers().get(0).getName());
//                        }
//                        break;
//                    }
//                } else {
//                    foundStone = true;
//                    System.out.println("Stone already selected!\n");
//                    break;
//                }
//            }
//        }
//        if (!foundStone && !endOfGame) {
//            System.out.println("Not a stone!\n");
//        } else if (endOfGame) {
//            System.out.println("Start new game!\n");
//        }
//        return false;
//    }
//
//
//    public BufferedImage getCurrentState() {
//        return image;
//    }

//    public Game getCurrentGame() {
//        Game game = new Game();
//        game.setBoard(board);
//        game.setPlayers(players);
//        game.setPositions(positions);
//        return game;
//    }
}