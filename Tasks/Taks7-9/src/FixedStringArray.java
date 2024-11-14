import javax.swing.*;
import java.util.HashSet;

public class FixedStringArray {
    private String[] array;
    private final int length;

    public FixedStringArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер массива должен быть положительным числом");
        }
        this.length = size;
        this.array = new String[size];
    }

    public String getElement(int index) {
        checkIndex(index);
        return array[index];
    }

    public void setElement(int index, String value) {
        checkIndex(index);
        array[index] = value;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " выходит за пределы массива размера " + length);
        }
    }

    public static FixedStringArray concatenate(FixedStringArray arr1, FixedStringArray arr2) {
        FixedStringArray result = new FixedStringArray(arr1.length + arr2.length);

        for (int i = 0; i < arr1.length; i++) {
            result.array[i] = arr1.array[i];
        }

        for (int i = 0; i < arr2.length; i++) {
            result.array[arr1.length + i] = arr2.array[i];
        }

        return result;
    }

    public static FixedStringArray merge(FixedStringArray arr1, FixedStringArray arr2) {
        HashSet<String> uniqueStrings = new HashSet<>();

        for (String str : arr1.array) {
            if (str != null) {
                uniqueStrings.add(str);
            }
        }

        for (String str : arr2.array) {
            if (str != null) {
                uniqueStrings.add(str);
            }
        }

        FixedStringArray result = new FixedStringArray(uniqueStrings.size());
        int index = 0;
        for (String str : uniqueStrings) {
            result.array[index++] = str;
        }

        return result;
    }

    public void printArray(JTextArea outputArea) {
        for (int i = 0; i < length; i++) {
            outputArea.append("[" + i + "]: " + (array[i] != null ? array[i] : "null") + "\n");
        }
    }

    public int getLength() {
        return length;
    }
}
