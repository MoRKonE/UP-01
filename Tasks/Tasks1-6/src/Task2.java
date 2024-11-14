import java.util.ArrayList;
import java.util.List;

class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imaginary + other.imaginary);
    }

    public Complex subtract(Complex other) {
        return new Complex(this.real - other.real, this.imaginary - other.imaginary);
    }

    public Complex multiply(Complex other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new Complex(newReal, newImaginary);
    }

    public Complex divide(Complex other) {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        double newReal = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
        double newImaginary = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
        return new Complex(newReal, newImaginary);
    }

    @Override
    public String toString() {
        return real + (imaginary >= 0 ? "+" : "") + imaginary + "i";
    }
}

public class Task2 {
    public static Complex sumComplexArray(List<Complex> complexList) {
        return complexList.stream().reduce(new Complex(0, 0), Complex::add);
    }

    public static Complex multiplyComplexArray(List<Complex> complexList) {
        return complexList.stream().reduce(new Complex(1, 0), Complex::multiply);
    }

    public static void main(String[] args) {
        List<Complex> complexList = new ArrayList<>();
        complexList.add(new Complex(1, 2));
        complexList.add(new Complex(3, 4));
        complexList.add(new Complex(-1, 1));
        complexList.add(new Complex(2, -3));

        System.out.println("Список комплексных чисел:");
        complexList.forEach(System.out::println);

        Complex sum = sumComplexArray(complexList);
        System.out.println("Сумма: " + sum);

        Complex product = multiplyComplexArray(complexList);
        System.out.println("Произведение: " + product);
    }
}
