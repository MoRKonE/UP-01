import java.io.*;
import java.util.Scanner;

class MathOperationException extends Exception {
    public MathOperationException(String message) {
        super(message);
    }
}

class Number {
    protected int value;

    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

class Fraction extends Number {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) throws MathOperationException {
        super(numerator);
        if (denominator == 0) {
            throw new MathOperationException("Знаменатель не может быть равен нулю");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    private void simplify() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public void print(PrintWriter writer) {
        writer.println(this);
    }

    public Fraction add(Fraction other) throws MathOperationException {
        long newNumeratorLong = (long) this.numerator * other.denominator + (long) other.numerator * this.denominator;
        long newDenominatorLong = (long) this.denominator * other.denominator;
        if (newNumeratorLong > Integer.MAX_VALUE || newNumeratorLong < Integer.MIN_VALUE ||
                newDenominatorLong > Integer.MAX_VALUE || newDenominatorLong < Integer.MIN_VALUE) {
            throw new MathOperationException("Переполнение при сложении");
        }
        return new Fraction((int) newNumeratorLong, (int) newDenominatorLong);
    }

    public Fraction subtract(Fraction other) throws MathOperationException {
        long newNumeratorLong = (long) this.numerator * other.denominator - (long) other.numerator * this.denominator;
        long newDenominatorLong = (long) this.denominator * other.denominator;
        if (newNumeratorLong > Integer.MAX_VALUE || newNumeratorLong < Integer.MIN_VALUE ||
                newDenominatorLong > Integer.MAX_VALUE || newDenominatorLong < Integer.MIN_VALUE) {
            throw new MathOperationException("Переполнение при вычитании");
        }
        return new Fraction((int) newNumeratorLong, (int) newDenominatorLong);
    }

    public Fraction multiply(Fraction other) throws MathOperationException {
        long newNumeratorLong = (long) this.numerator * other.numerator;
        long newDenominatorLong = (long) this.denominator * other.denominator;
        if (newNumeratorLong > Integer.MAX_VALUE || newNumeratorLong < Integer.MIN_VALUE ||
                newDenominatorLong > Integer.MAX_VALUE || newDenominatorLong < Integer.MIN_VALUE) {
            throw new MathOperationException("Переполнение при умножении");
        }
        return new Fraction((int) newNumeratorLong, (int) newDenominatorLong);
    }

    public Fraction divide(Fraction other) throws MathOperationException {
        if (other.numerator == 0) {
            throw new MathOperationException("Деление на ноль");
        }
        long newNumeratorLong = (long) this.numerator * other.denominator;
        long newDenominatorLong = (long) this.denominator * other.numerator;
        if (newNumeratorLong > Integer.MAX_VALUE || newNumeratorLong < Integer.MIN_VALUE ||
                newDenominatorLong > Integer.MAX_VALUE || newDenominatorLong < Integer.MIN_VALUE) {
            throw new MathOperationException("Переполнение при делении");
        }
        return new Fraction((int) newNumeratorLong, (int) newDenominatorLong);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}

public class Task6 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {

            System.out.println("Введите числитель и знаменатель первой дроби:");
            Fraction f1 = readFraction(scanner);

            System.out.println("Введите числитель и знаменатель второй дроби:");
            Fraction f2 = readFraction(scanner);

            writer.print("f1: ");
            f1.print(writer);
            writer.print("f2: ");
            f2.print(writer);

            try {
                writer.println("f1 + f2 = " + f1.add(f2));
                writer.println("f1 - f2 = " + f1.subtract(f2));
                writer.println("f1 * f2 = " + f1.multiply(f2));
                writer.println("f1 / f2 = " + f1.divide(f2));
            } catch (MathOperationException e) {
                writer.println("Ошибка при выполнении математической операции: " + e.getMessage());
            }

            System.out.println("Результаты записаны в файл output.txt");

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Недостаточно памяти для выполнения операции: " + e.getMessage());
        }
    }

    private static Fraction readFraction(Scanner scanner) {
        while (true) {
            try {
                int numerator = scanner.nextInt();
                int denominator = scanner.nextInt();
                return new Fraction(numerator, denominator);
            } catch (MathOperationException e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте снова.");
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ошибка: Введите целые числа. Попробуйте снова.");
                scanner.nextLine(); // Очистка буфера сканера
            }
        }
    }
}
