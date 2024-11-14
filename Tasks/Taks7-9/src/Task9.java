import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Task9 {
    private JFrame frame;
    private JTextField[][] cells;
    private JLabel[] rowLabels;
    private JLabel[] colLabels;
    private JLabel currentNumberLabel;
    private int[] rowScores;
    private int[] colScores;
    private int[] deck;
    private int currentCardIndex;
    private int currentNumber;
    private boolean isPlayerTurn;

    public Task9() {
        frame = new JFrame("Математико");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(5, 5));
        cells = new JTextField[5][5];
        rowScores = new int[5];
        colScores = new int[5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new JTextField();
                cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                cells[i][j].setEditable(false);
                int finalI = i;
                int finalJ = j;
                cells[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (isPlayerTurn) {
                            placeNumber(finalI, finalJ);
                        }
                    }
                });
                gridPanel.add(cells[i][j]);
            }
        }

        JPanel scorePanel = new JPanel(new GridLayout(6, 1));
        rowLabels = new JLabel[5];
        colLabels = new JLabel[5];
        currentNumberLabel = new JLabel("Следующий номер: ", SwingConstants.CENTER);

        scorePanel.add(currentNumberLabel);
        for (int i = 0; i < 5; i++) {
            rowLabels[i] = new JLabel("(0)", SwingConstants.CENTER);
            scorePanel.add(rowLabels[i]);
        }
        for (int i = 0; i < 5; i++) {
            colLabels[i] = new JLabel("(0)", SwingConstants.CENTER);
            scorePanel.add(colLabels[i]);
        }

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(scorePanel, BorderLayout.EAST);

        initializeDeck();
        drawNextCard();

        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    private void initializeDeck() {
        deck = new int[52];
        int index = 0;
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                deck[index++] = i;
            }
        }
        shuffleDeck();
        currentCardIndex = 0;
        isPlayerTurn = true;
    }

    private void shuffleDeck() {
        Random rand = new Random();
        for (int i = 0; i < deck.length; i++) {
            int randomIndex = rand.nextInt(deck.length);
            int temp = deck[i];
            deck[i] = deck[randomIndex];
            deck[randomIndex] = temp;
        }
    }

    private void drawNextCard() {
        if (currentCardIndex < deck.length) {
            currentNumber = deck[currentCardIndex++];
            currentNumberLabel.setText("Следующий номер: " + currentNumber);
            enableCells(isPlayerTurn);
        } else {
            JOptionPane.showMessageDialog(frame, "Все карты разыграны.");
            calculateFinalScores();
        }
    }

    private void drawNextComputerCard() {
        if (currentCardIndex < deck.length) {
            currentNumber = deck[currentCardIndex++];
        } else {
            JOptionPane.showMessageDialog(frame, "Все карты разыграны.");
            calculateFinalScores();
        }
    }

    private void placeNumber(int row, int col) {
        if (cells[row][col].getText().isEmpty()) {
            cells[row][col].setText(String.valueOf(currentNumber));
            rowScores[row] += currentNumber;
            colScores[col] += currentNumber;
            rowLabels[row].setText("(" + rowScores[row] + ")");
            colLabels[col].setText("(" + colScores[col] + ")");
            enableCells(false);
            if (isGridFull()) {
                calculateFinalScores();
            } else {
                switchTurns();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Эта ячейка уже заполнена. Выберите другую ячейку.");
        }
    }

    private void switchTurns() {
        isPlayerTurn = !isPlayerTurn;
        if (!isPlayerTurn) {
            System.out.println("Очередь компьютера");
            computerMove();
        } else {
            drawNextCard();
        }
    }

    private void computerMove() {
        drawNextComputerCard();
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(5);
            col = rand.nextInt(5);
        } while (!cells[row][col].getText().isEmpty());

        placeNumber(row, col);

        if (!isGridFull()) {
            drawNextCard();
        } else {
            calculateFinalScores();
        }
    }

    private void enableCells(boolean enable) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                cells[i][j].setEditable(enable);
            }
        }
    }

    private boolean isGridFull() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cells[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void calculateFinalScores() {
        int totalScore = 0;

        for (int i = 0; i < 5; i++) {
            totalScore += calculateScoreForLine(cells[i], false);
        }

        for (int j = 0; j < 5; j++) {
            JTextField[] col = new JTextField[5];
            for (int i = 0; i < 5; i++) {
                col[i] = cells[i][j];
            }
            totalScore += calculateScoreForLine(col, false);
        }

        JTextField[] diag1 = new JTextField[5];
        JTextField[] diag2 = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            diag1[i] = cells[i][i];
            diag2[i] = cells[i][4 - i];
        }
        totalScore += calculateScoreForLine(diag1, true);
        totalScore += calculateScoreForLine(diag2, true);

        JOptionPane.showMessageDialog(frame, "Итоговый счет: " + totalScore);
    }

    private int calculateScoreForLine(JTextField[] line, boolean isDiagonal) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (JTextField cell : line) {
            int num = Integer.parseInt(cell.getText());
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        int score = 0;

        int ones = frequency.getOrDefault(1,0);
        int thirteens = frequency.getOrDefault(13,0);

        if (ones == 4) {
            score += isDiagonal ? 210 : 200;
        }

        if (ones == 3 && thirteens == 2) {
            score += isDiagonal ? 110 : 100;
        }

        for (int freq : frequency.values()) {
            switch (freq) {
                case 2:
                    score += isDiagonal ? 20 : 10;
                    break;
                case 3:
                    score += isDiagonal ? 50 : 40;
                    break;
                case 4:
                    score += isDiagonal ? 170 : 160;
                    break;
            }
        }

        if (frequency.containsKey(1) && frequency.containsKey(13) &&
                frequency.containsKey(12) && frequency.containsKey(11) &&
                frequency.containsKey(10)) {
            score += isDiagonal ? 160 : 150;
        }

        int pairs = 0;
        for (int freq : frequency.values()) {
            if (freq == 2) pairs++;
        }
        if (pairs == 2) {  // Две пары одинаковых чисел
            score = isDiagonal ? 30 : 20;
        }

        return score;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task9::new);
    }
}
