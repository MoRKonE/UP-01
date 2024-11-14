import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст:");
        String text = scanner.nextLine();
        scanner.close();

        String[] words = text.split("\\s+");
        int minLength = Integer.MAX_VALUE;
        int maxLength = 0;

        // Находим минимальную и максимальную длину слов
        for (String word : words) {
            int length = word.length();
            if (length < minLength) minLength = length;
            if (length > maxLength) maxLength = length;
        }

        List<String> shortestWords = new ArrayList<>();
        List<String> longestWords = new ArrayList<>();

        // Собираем слова минимальной и максимальной длины
        for (String word : words) {
            if (word.length() == minLength) shortestWords.add(word);
            if (word.length() == maxLength) longestWords.add(word);
        }

        System.out.println("Слова минимальной длины (" + minLength + " символов):");
        System.out.println(String.join(", ", shortestWords));

        System.out.println("Слова максимальной длины (" + maxLength + " символов):");
        System.out.println(String.join(", ", longestWords));
    }
}
