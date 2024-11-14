import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task8 {

    private static FixedStringArray arr1;
    private static FixedStringArray arr2;
    private static JTextArea outputArea;

    public static void main(String[] args) {
        // Создаем окно
        JFrame frame = new JFrame("Работа с массивами строк");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Панель ввода данных
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        // Поля для ввода размера массивов
        JTextField sizeField1 = new JTextField(5);
        JTextField sizeField2 = new JTextField(5);
        inputPanel.add(new JLabel("Размер первого массива:"));
        inputPanel.add(sizeField1);
        inputPanel.add(new JLabel("Размер второго массива:"));
        inputPanel.add(sizeField2);

        // Кнопка создания массивов
        JButton createButton = new JButton("Создать массивы");
        inputPanel.add(createButton);

        // Вывод результатов
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Добавляем элементы на окно
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Обработчик события для кнопки создания массивов
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Получаем размеры массивов из полей ввода
                    int size1 = Integer.parseInt(sizeField1.getText());
                    int size2 = Integer.parseInt(sizeField2.getText());

                    // Создаем массивы
                    arr1 = new FixedStringArray(size1);
                    arr2 = new FixedStringArray(size2);

                    // Очищаем поле вывода
                    outputArea.setText("");

                    // Выводим инструкции для пользователя
                    outputArea.append("Введите элементы первого массива:\n");
                    for (int i = 0; i < size1; i++) {
                        String input = JOptionPane.showInputDialog("Введите элемент для первого массива, индекс " + i + ":");
                        arr1.setElement(i, input);
                    }

                    outputArea.append("\nВведите элементы второго массива:\n");
                    for (int i = 0; i < size2; i++) {
                        String input = JOptionPane.showInputDialog("Введите элемент для второго массива, индекс " + i + ":");
                        arr2.setElement(i, input);
                    }

                    // Выводим данные о массивах
                    outputArea.append("\nПервый массив:\n");
                    arr1.printArray(outputArea);

                    outputArea.append("\nВторой массив:\n");
                    arr2.printArray(outputArea);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ошибка ввода! Введите корректные размеры массивов.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Произошла ошибка: " + ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Панель для операций
        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new GridLayout(2, 1));

        JButton concatButton = new JButton("Конкатенация массивов");
        JButton mergeButton = new JButton("Слияние массивов без дубликатов");

        // Добавляем кнопки на панель
        operationPanel.add(concatButton);
        operationPanel.add(mergeButton);

        // Обработчик события для конкатенации
        concatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arr1 == null || arr2 == null) {
                    JOptionPane.showMessageDialog(frame, "Сначала создайте массивы!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                FixedStringArray concatenated = FixedStringArray.concatenate(arr1, arr2);
                outputArea.append("\nРезультат конкатенации:\n");
                concatenated.printArray(outputArea);
            }
        });

        // Обработчик события для слияния
        mergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arr1 == null || arr2 == null) {
                    JOptionPane.showMessageDialog(frame, "Сначала создайте массивы!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                FixedStringArray merged = FixedStringArray.merge(arr1, arr2);
                outputArea.append("\nРезультат слияния без дубликатов:\n");
                merged.printArray(outputArea);
            }
        });

        // Добавляем панель операций на окно
        frame.add(operationPanel, BorderLayout.SOUTH);

        // Отображаем окно
        frame.setVisible(true);
    }
}
