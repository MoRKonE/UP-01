import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(2 * n + 1) - n;
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }

        System.out.println("Исходная матрица:");
        printMatrix(matrix);
        System.out.println("Максимальный элемент: " + max);

        ArrayList<Integer> rowsToRemove = new ArrayList<>();
        ArrayList<Integer> colsToRemove = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == max) {
                    if (!rowsToRemove.contains(i)) rowsToRemove.add(i);
                    if (!colsToRemove.contains(j)) colsToRemove.add(j);
                }
            }
        }

        int newSize = n - rowsToRemove.size();
        int[][] newMatrix = new int[newSize][newSize];
        int newRow = 0, newCol;
        for (int i = 0; i < n; i++) {
            if (rowsToRemove.contains(i)) continue;
            newCol = 0;
            for (int j = 0; j < n; j++) {
                if (colsToRemove.contains(j)) continue;
                newMatrix[newRow][newCol] = matrix[i][j];
                newCol++;
            }
            newRow++;
        }

        System.out.println("Результирующая матрица:");
        printMatrix(newMatrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int elem : row) {
                System.out.printf("%4d", elem);
            }
            System.out.println();
        }
    }
}
